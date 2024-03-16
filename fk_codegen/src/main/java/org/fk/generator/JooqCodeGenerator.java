package org.fk.generator;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.UpdateSummaryEnum;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep;
import liquibase.command.core.helpers.ShowSummaryArgument;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.time.Duration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JooqCodeGenerator {

    public static void main(String[] args) throws Exception {
        // Start the MariaDB test container
        MariaDBContainer container = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.6.1"))
                .withDatabaseName("testshop")
                .withUsername("tester")
                .withPassword("test123");
        container.start();
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(1))
                .until(container::isRunning);

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory is: " + currentDirectory);

        try {
            // execute liquibase update
            final Connection connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
            final ResourceAccessor resourceAccessor = new DirectoryResourceAccessor(new File("../fk_backend/src/main/resources/liquibase"));
            final liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

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

            // Generate JOOQ code programmatically
            Configuration configuration = new Configuration()
                    .withJdbc(new Jdbc()
                            .withDriver("org.mariadb.jdbc.Driver")
                            .withUrl(container.getJdbcUrl())
                            .withUser(container.getUsername())
                            .withPassword(container.getPassword()))
                    .withGenerator(new Generator()
                            .withName("org.fk.generator.MyGenerator")
                            .withGenerate(new Generate()
                                    .withInterfaces(true)
                                    .withSerializableInterfaces(true)
                                    .withDaos(true)
                                    .withValidationAnnotations(true)
                                    .withPojosEqualsAndHashCode(true)
                            )
                            .withStrategy(new Strategy()
                                    .withName("org.fk.generator.MyGeneratorStrategy")
                            )
                            .withDatabase(new Database()
                                    .withName("org.jooq.meta.mariadb.MariaDBDatabase")
                                    .withIncludes("testshop.*")
                                    .withExcludes("")
                                    .withForcedTypes(new ForcedType()
                                            .withName("BOOLEAN")
                                            .withIncludeTypes("(?i:TINYINT\\(1\\))"))
                            )
                            .withTarget(new Target()
                                    .withPackageName("org.fk.generated")
                                    .withDirectory("src/main/generated")));

            GenerationTool.generate(configuration);

        } finally {
            // Stop the MariaDB test container
            container.stop();
        }
    }
}