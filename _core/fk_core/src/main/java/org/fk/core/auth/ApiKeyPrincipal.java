package org.fk.core.auth;

import java.security.Principal;

public class ApiKeyPrincipal implements Principal {
    private final String apiKey;

    public ApiKeyPrincipal(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getName() {
        return apiKey;
    }
}