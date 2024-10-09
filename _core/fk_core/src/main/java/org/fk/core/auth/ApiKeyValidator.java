package org.fk.core.auth;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiKeyValidator {

    public boolean isValid(String apiKey) {
        // Implement your logic to validate the API key (e.g., check against a database or config)
        return "valid-api-key".equals(apiKey); // Dummy validation logic
    }
}