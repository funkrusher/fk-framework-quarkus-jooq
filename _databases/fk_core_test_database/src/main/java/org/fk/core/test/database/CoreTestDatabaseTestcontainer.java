package org.fk.core.test.database;

import org.fk.provider.liquibase.FkLiquibase;
import org.fk.provider.testcontainers.FkMariaDb;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.fk.provider.liquibase.FkLiquibase.FkLiquibaseChangesetContext.DDL_CONTEXT;

public class CoreTestDatabaseTestcontainer implements AutoCloseable {

    private final FkMariaDb fkMariaDb;

    public CoreTestDatabaseTestcontainer() throws Exception {
        this.fkMariaDb = new FkMariaDb("coreTestDatabase");

        // create database if it does not exist
        try (Connection connection = fkMariaDb.createConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS coreTestDatabase;");
        }
        // execute liquibase-update
        try (Connection connection = fkMariaDb.createConnection()) {
            FkLiquibase fkLiquibase = new FkLiquibase("coreTestDatabase/liquibase/changelog.xml", DDL_CONTEXT);
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
