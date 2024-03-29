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
import org.fk.core.liquibase.FkLiquibase;
import org.fk.core.testcontainers.FkMariaDb;
import org.fk.database.DatabaseTestcontainer;
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

    DatabaseTestcontainer databaseTestcontainer;

    @Override
    public Map<String, String> start() {
        try {
            this.databaseTestcontainer = new DatabaseTestcontainer();
            MariaDBContainer<?> container = databaseTestcontainer.getFkMariadb().getContainer();

            // populate the db with the basis-data for each product, and set them initialized!
            DSLContext dsl = DSL.using(container.getJdbcUrl(), container.getUsername(), container.getPassword());
            ProductInit productInit = new ProductInit();
            productInit.init(dsl);

            final Map<String, String> jdbcUrl = new HashMap<>();
            jdbcUrl.put("quarkus.datasource.url", container.getJdbcUrl());
            jdbcUrl.put("mariadb.testcontainer.host", "localhost");
            jdbcUrl.put("mariadb.testcontainer.port", String.valueOf(container.getFirstMappedPort()));
            jdbcUrl.put("mariadb.testcontainer.username", String.valueOf(container.getUsername()));
            jdbcUrl.put("mariadb.testcontainer.password", String.valueOf(container.getPassword()));
            jdbcUrl.put("mariadb.testcontainer.database", String.valueOf(container.getDatabaseName()));
            return jdbcUrl;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        try {
            databaseTestcontainer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inject(Object testInstance) {
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(
                new TestDbUtil(databaseTestcontainer.getFkMariadb().getContainer()),
                new TestInjector.AnnotatedAndMatchesType(InjectTestDbUtil.class, TestDbUtil.class));
    }
}