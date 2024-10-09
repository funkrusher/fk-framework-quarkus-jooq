package org.fk.core.auth;

import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import io.vertx.ext.web.RoutingContext;
import io.quarkus.vertx.http.runtime.security.ChallengeData;

@ApplicationScoped
public class ApiKeyAuthenticationMechanism implements HttpAuthenticationMechanism {

    private static final Logger LOGGER = Logger.getLogger(ApiKeyAuthenticationMechanism.class);
    private static final String API_KEY_HEADER = "X-API-KEY";

    @Inject
    ApiKeyValidator apiKeyValidator;

    @Override
    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {
        // Extract API key from the header
        String apiKey = context.request().getHeader(API_KEY_HEADER);

        if (apiKey != null && apiKeyValidator.isValid(apiKey)) {
            // Create a new SecurityIdentity for the API key
            QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder();
            builder.setPrincipal(new ApiKeyPrincipal(apiKey));
            builder.addRole("api-user");
            // Return the created identity
            return Uni.createFrom().item(builder.build());
        }

        // If no API key is found, return null to allow fallback to other mechanisms (e.g., OIDC)
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
        // Optionally, you can return a challenge (e.g., return 401 if API key is missing or invalid)
        return Uni.createFrom().nullItem();  // No challenge for this example
    }}