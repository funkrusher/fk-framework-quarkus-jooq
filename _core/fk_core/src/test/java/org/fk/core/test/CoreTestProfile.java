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

        map.put("quarkus.datasource.active", "false");
        map.put("quarkus.datasource.coreTestDatabase.db-kind", "mariadb");
        map.put("quarkus.datasource.coreTestDatabase.jdbc.url", "jdbc:mariadb://${mariadb.testcontainer.host}:${mariadb.testcontainer.port}/${mariadb.testcontainer.database}?useCursorFetch=true&rewriteBatchedStatements=true&allowMultiQueries=true");
        map.put("quarkus.datasource.coreTestDatabase.username", "${mariadb.testcontainer.username}");
        map.put("quarkus.datasource.coreTestDatabase.password", "${mariadb.testcontainer.password}");

        map.put("quarkus.log.category.\"org.fk\".level", "DEBUG");
        map.put("quarkus.log.category.\"org.jooq.tools.LoggerListener\".level", "DEBUG");

        return map;
    }
}
