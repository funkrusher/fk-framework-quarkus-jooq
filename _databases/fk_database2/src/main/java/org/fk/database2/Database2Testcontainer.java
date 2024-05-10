package org.fk.database2;

import org.fk.core.liquibase.FkLiquibase;
import org.fk.core.testcontainers.FkPostgres;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.fk.core.liquibase.FkLiquibase.FkLiquibaseChangesetContext.DDL_CONTEXT;

public class Database2Testcontainer implements AutoCloseable {

    private final FkPostgres fkPostgres;

    public Database2Testcontainer() throws Exception {
        this.fkPostgres = new FkPostgres();

        // create database if it does not exist
        try (Connection connection = fkPostgres.createConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS testlibrary;");
        }
        // execute liquibase-update
        try (Connection connection = fkPostgres.createConnection()) {
            FkLiquibase fkLiquibase = new FkLiquibase("database2/liquibase/changelog.xml", DDL_CONTEXT);
            fkLiquibase.executeUpdate(connection);
        }
    }

    public FkPostgres getFkPostgres() {
        return fkPostgres;
    }

    @Override
    public void close() throws Exception {
        this.fkPostgres.close();
    }

    public Map<String, String> getLifecycleManagerStartResult() {
        // this data is passend into the 'application.conf' of the tests to be used by the framework,
        // for connecting with our test-database during execution of the tests / loading of the test-application.conf
        final Map<String, String> startResult = new HashMap<>();
        startResult.put("quarkus.datasource.url", fkPostgres.getContainer().getJdbcUrl());
        startResult.put("postgres.testcontainer.host", "localhost");
        startResult.put("postgres.testcontainer.port", String.valueOf(fkPostgres.getContainer().getFirstMappedPort()));
        startResult.put("postgres.testcontainer.username", String.valueOf(fkPostgres.getContainer().getUsername()));
        startResult.put("postgres.testcontainer.password", String.valueOf(fkPostgres.getContainer().getPassword()));
        startResult.put("postgres.testcontainer.database", String.valueOf(fkPostgres.getContainer().getDatabaseName()));
        return startResult;
    }
}
