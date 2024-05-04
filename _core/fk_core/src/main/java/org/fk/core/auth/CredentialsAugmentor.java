package org.fk.core.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.exception.NotFoundException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CredentialsAugmentor implements SecurityIdentityAugmentor {

    public static final Logger LOGGER = Logger.getLogger(CredentialsAugmentor.class);
    public static final String FK_CLAIM = "custom:fk";

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }
        try {
            // we only support JWTCallerPrincipal at this point (authentication)
            final QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder(identity);
            final JWTCallerPrincipal jwtPrincipal = (JWTCallerPrincipal) identity.getPrincipal();

            String fkClaimStr = jwtPrincipal.getClaim(FK_CLAIM);
            FkClaim fkClaim = objectMapper.readValue(fkClaimStr, FkClaim.class);
            builder.addCredential(new TenantCredential(fkClaim.getClientId()));
            for (String role : fkClaim.getRoles()) {
                builder.addRole(role);
            }
            return context.runBlocking(builder::build);

        } catch (Exception e) {
            // security by obscurity, we should return a 404 page-not-found.
            LOGGER.debug("Unable to process fk_claim!", e);
            throw new NotFoundException();
        }
    }
}