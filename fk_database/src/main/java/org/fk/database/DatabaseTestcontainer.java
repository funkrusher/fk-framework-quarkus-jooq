package org.fk.database;

import org.fk.core.liquibase.FkLiquibase;
import org.fk.core.testcontainers.FkMariaDb;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseTestcontainer implements AutoCloseable {

    private final FkMariaDb fkMariaDb;

    public DatabaseTestcontainer() throws Exception {
        this.fkMariaDb = new FkMariaDb();

        // create database if it does not exist
        try (Connection connection = fkMariaDb.createConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS testshop");
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
}
