package org.fk.core.test;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.HashMap;
import java.util.Map;

public class CoreTestProfile implements QuarkusTestProfile {

    public Map<String, String> getConfigOverrides() {
        Map<String, String> map = new HashMap<>();

        // speed up our unittests, by disabling dev-services and features that are not needed here.
        map.put("quarkus.devservices.enabled", "false");
        map.put("quarkus.oidc.enabled", "false");
        map.put("quarkus.liquibase.enabled", "false");
        map.put("quarkus.scheduler.enabled", "false");

        map.put("quarkus.log.category.\"org.fk\".level", "DEBUG");
        map.put("quarkus.log.category.\"org.jooq.tools.LoggerListener\".level", "DEBUG");

        return map;
    }
}
