package org.fk.database;

import org.fk.core.liquibase.FkLiquibase;
import org.fk.core.testcontainers.FkMariaDb;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseTestcontainer implements AutoCloseable {

    private final FkMariaDb fkMariaDb;

    public DatabaseTestcontainer() throws Exception {
        this.fkMariaDb = new FkMariaDb();

        // create database if it does not exist
        try (Connection connection = fkMariaDb.createConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS testshop;");
        }
        // execute liquibase-update
        try (Connection connection = fkMariaDb.createConnection()) {
            FkLiquibase fkLiquibase = new FkLiquibase(
                    "database/liquibase/changelog.xml", "database/liquibase/changelog.xml");
            fkLiquibase.executeUpdate(connection);
        }
    }

    public FkMariaDb getFkMariadb() {
        return fkMariaDb;
    }

    @Override
    public void close() throws Exception {
        this.fkMariaDb.close();
    }

    public Map<String, String> getLifecycleManagerStartResult() {
        // this data is passend into the 'application.conf' of the tests to be used by the framework,
        // for connecting with our test-database during execution of the tests / loading of the test-application.conf
        final Map<String, String> startResult = new HashMap<>();
        startResult.put("quarkus.datasource.url", fkMariaDb.getContainer().getJdbcUrl());
        startResult.put("mariadb.testcontainer.host", "localhost");
        startResult.put("mariadb.testcontainer.port", String.valueOf(fkMariaDb.getContainer().getFirstMappedPort()));
        startResult.put("mariadb.testcontainer.username", String.valueOf(fkMariaDb.getContainer().getUsername()));
        startResult.put("mariadb.testcontainer.password", String.valueOf(fkMariaDb.getContainer().getPassword()));
        startResult.put("mariadb.testcontainer.database", String.valueOf(fkMariaDb.getContainer().getDatabaseName()));
        return startResult;
    }
}
