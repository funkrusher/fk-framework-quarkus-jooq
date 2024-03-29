package org.fk.backend.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import liquibase.Scope;
import liquibase.UpdateSummaryEnum;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep;
import liquibase.command.core.helpers.ShowSummaryArgument;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.awaitility.Awaitility;
import org.fk.product.init.ProductInit;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TestDbLifecycleManager
 * <p>
 * - starts mariadb-testcontainer
 * - apply flyway migration
 * - provides mariadb-testcontainer configuration to datasource-configuration of application in application.properties.
 * </p>
 */
public class TestDbLifecycleManager implements QuarkusTestResourceLifecycleManager {

    private MariaDBContainer<?> container;

    @Override
    public Map<String, String> start() {
        container = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.7.8"))
                .withDatabaseName("testshop")
                .withUsername("tester")
                .withPassword("test123");

        container.start();
        waitUntilContainerStarted();

        final Map<String, String> jdbcUrl = new HashMap<>();
        jdbcUrl.put("quarkus.datasource.url", container.getJdbcUrl());
        jdbcUrl.put("mariadb.testcontainer.host", "localhost");
        jdbcUrl.put("mariadb.testcontainer.port", String.valueOf(container.getFirstMappedPort()));
        jdbcUrl.put("mariadb.testcontainer.username", String.valueOf(container.getUsername()));
        jdbcUrl.put("mariadb.testcontainer.password", String.valueOf(container.getPassword()));
        jdbcUrl.put("mariadb.testcontainer.database", String.valueOf(container.getDatabaseName()));

        createDatabase(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        try {
            migrateDatabase(container.getJdbcUrl(), container.getUsername(), container.getPassword());

            // populate the db with the basis-data for each product, and set them initialized!
            DSLContext dsl = DSL.using(this.container.getJdbcUrl(), this.container.getUsername(), this.container.getPassword());
            ProductInit productInit = new ProductInit();
            productInit.init(dsl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jdbcUrl;
    }

    @Override
    public void stop() {
        container.stop();
    }

    private void createDatabase(String jdbcUrl, String username, String password) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS testshop");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void migrateDatabase(String jdbcUrl, String username, String password) throws Exception {
        // execute liquibase update
        final Connection connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        final ResourceAccessor resourceAccessor = new DirectoryResourceAccessor(new File("src/main/resources/liquibase"));
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

        Map<String, Object> scopedSettings = new LinkedHashMap<>();
        scopedSettings.put(Scope.Attr.resourceAccessor.name(), resourceAccessor);
        Scope.child(scopedSettings, () -> {
            final CommandScope updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME);
            updateCommand.addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database);
            updateCommand.addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, "changelog.xml");
            updateCommand.addArgumentValue(ShowSummaryArgument.SHOW_SUMMARY, UpdateSummaryEnum.SUMMARY);
            updateCommand.execute();
        });

        connection.close();
    }

    private void waitUntilContainerStarted() {
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(1))
                .until(container::isRunning);
    }

    @Override
    public void inject(Object testInstance) {

    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(new TestDbUtil(container), new TestInjector.AnnotatedAndMatchesType(InjectTestDbUtil.class, TestDbUtil.class));
    }
}