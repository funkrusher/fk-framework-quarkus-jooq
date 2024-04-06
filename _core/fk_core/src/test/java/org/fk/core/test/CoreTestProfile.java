package org.fk.core.test;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.HashMap;
import java.util.Map;

public class CoreTestProfile implements QuarkusTestProfile {

    public Map<String, String> getConfigOverrides() {
        Map<String, String> map = new HashMap<>();

        map.put("quarkus.liquibase.migrate-at-start", "false");

        // devservices slow down our unittest, but we need some of them (OIDC)
        map.put("quarkus.devservices.enabled", "true");

        map.put("quarkus.log.category.\"org.fk\".level", "DEBUG");
        map.put("quarkus.log.category.\"org.jooq.tools.LoggerListener\".level", "DEBUG");

        return map;
    }
}
