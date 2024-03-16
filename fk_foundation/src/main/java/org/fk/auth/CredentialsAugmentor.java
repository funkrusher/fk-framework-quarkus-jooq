package org.fk.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.util.cognito.FkClaim;

import java.security.Principal;

@ApplicationScoped
public class CredentialsAugmentor implements SecurityIdentityAugmentor {

    private static final String FK_CLAIM = "custom:fk";

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        } else {
            QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder(identity);
            Principal principal = identity.getPrincipal();
            if (principal instanceof JWTCallerPrincipal) {
                JWTCallerPrincipal jwtPrincipal = (JWTCallerPrincipal) principal;
                String fkClaimStr = jwtPrincipal.getClaim(FK_CLAIM);
                if (fkClaimStr != null) {
                    try {
                        FkClaim fkClaim = objectMapper.readValue(fkClaimStr, FkClaim.class);
                        if (fkClaim.getClientId() != null) {
                            builder.addCredential(new TenantCredential(fkClaim.getClientId()));
                        }
                        if (fkClaim.getRoles() != null) {
                            for (String role : fkClaim.getRoles()) {
                                builder.addRole(role);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return context.runBlocking(builder::build);
        }
    }

}