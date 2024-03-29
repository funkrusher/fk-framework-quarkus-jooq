package org.fk.product.test;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.HashMap;
import java.util.Map;

public class ProductTestProfile implements QuarkusTestProfile {

    public Map<String, String> getConfigOverrides() {
        Map<String, String> map = new HashMap<>();

        map.put("quarkus.datasource.db-kind", "mariadb");
        map.put("quarkus.datasource.jdbc.url", "jdbc:mariadb://${mariadb.testcontainer.host}:${mariadb.testcontainer.port}/${mariadb.testcontainer.database}?useCursorFetch=true&rewriteBatchedStatements=true&allowMultiQueries=true");
        map.put("quarkus.datasource.username", "${mariadb.testcontainer.username}");
        map.put("quarkus.datasource.password", "${mariadb.testcontainer.password}");

        map.put("quarkus.log.category.\"org.fk\".level", "DEBUG");
        map.put("quarkus.log.category.\"org.jooq.tools.LoggerListener\".level", "DEBUG");

        return map;
    }
}
