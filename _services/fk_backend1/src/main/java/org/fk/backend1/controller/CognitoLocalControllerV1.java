package org.fk.backend1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.RefreshToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.RuntimeDelegate;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.fk.core.auth.FkClaim;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.core.auth.MasterTenantOnly;
import org.fk.product.manager.CognitoLocalManager;
import org.jboss.logging.Logger;
import software.amazon.awssdk.services.cognitoidentityprovider.model.NotAuthorizedException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserNotFoundException;

import java.util.Map;
import java.util.Set;

import static org.fk.core.auth.FkRoles.ADMIN;


@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "access_token",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")}
)
@Path("/api/v1/cognitoLocal")
public class CognitoLocalControllerV1 {

    private static final Logger LOGGER = Logger.getLogger(CognitoLocalControllerV1.class);

    @Inject
    CognitoLocalManager cognitoLocalService;

    /**
     * Injection point for the ID Token issued by the OpenID Connect Provider
     */
    @Inject
    @IdToken
    JsonWebToken idToken;

    /**
     * Injection point for the Access Token issued by the OpenID Connect Provider
     */
    @Inject
    JsonWebToken accessToken;

    /**
     * Injection point for the Refresh Token issued by the OpenID Connect Provider
     */
    @Inject
    RefreshToken refreshToken;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    FkSecurityIdentity tenantAuthorizationPolicy;

    @POST
    @Path("/signup")
    @Produces(MediaType.TEXT_PLAIN)
    public String signup(@FormParam("clientId") Integer clientId,
                         @FormParam("email") String email,
                         @FormParam("password") String password,
                         @FormParam("firstname") String firstname,
                         @FormParam("lastname") String lastname,
                         @FormParam("roleId") String roleId) {
        String userSub = cognitoLocalService.signup(clientId, email, password, firstname, lastname, roleId);
        return "User created successfully: " + userSub;
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signin(@FormParam("email") String email,
                           @FormParam("password") String password) {
        try {
            Map<String, String> jwtTokens = cognitoLocalService.signin(email, password);
            return Response.ok(jwtTokens.get("id_token")).cookie(
                    RuntimeDelegate.getInstance().createHeaderDelegate(NewCookie.class).fromString("access_token=" + jwtTokens.get("access_token")),
                    RuntimeDelegate.getInstance().createHeaderDelegate(NewCookie.class).fromString("refresh_token=" + jwtTokens.get("refresh_token")),
                    RuntimeDelegate.getInstance().createHeaderDelegate(NewCookie.class).fromString("id_token=" + jwtTokens.get("id_token"))
            ).build();
        } catch (NotAuthorizedException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/find")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> find(@FormParam("email") String email) throws UserNotFoundException {
        return cognitoLocalService.find(email);
    }


    @GET
    @Path("/protected-by-quarkus")
    @SecurityRequirement(name = "access_token")
    @RolesAllowed(ADMIN)
    @MasterTenantOnly
    @Produces(MediaType.TEXT_PLAIN)
    public String protectedResource(@Context SecurityContext securityContext) {
        // note: the id_token must be given here!

        boolean check = securityContext.isUserInRole(ADMIN);
        if (!check) {
            return "no admin! this should never happen";
        }
        boolean hasAccessToTenant = tenantAuthorizationPolicy.hasClientAccess(1);
        if (!hasAccessToTenant) {
            return "no access for tenant-id 1! this should never happen";
        }

        String name = accessToken.getName();
        String issuer = accessToken.getIssuer();
        String rawToken = accessToken.getRawToken();

        Set<String> claimnames = accessToken.getClaimNames();
        for (String claimname : claimnames) {
            String claimValue = accessToken.getClaim(claimname).toString();
            LOGGER.info(claimname + ": " + claimValue);
        }

        String fkClaimStr = accessToken.getClaim("custom:fk");
        if (fkClaimStr != null) {
            try {
                FkClaim fkClaim = objectMapper.readValue(fkClaimStr, FkClaim.class);
                LOGGER.info("fkClaim: " + fkClaim);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("name: " + name);
        LOGGER.info("issuer: " + issuer);
        LOGGER.info("rawToken: " + rawToken);
        return "This is a protected resource";
    }

}