package org.fk.core.liquibase;

import liquibase.Scope;
import liquibase.UpdateSummaryEnum;
import liquibase.command.CommandScope;
import liquibase.command.core.RollbackCommandStep;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.UpdateSqlCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep;
import liquibase.command.core.helpers.ShowSummaryArgument;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.fk.core.exception.MappingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * FkLiquibase
 * - can be called via gradle to Update or Rollback Migrations.
 * - can be used within the Tests to prepare the Test-Database with Migrations.
 * - is expecting the changeSets to be in the contexts "ddl", "dml".
 *   - can be used to only execute "ddl" statements and mark "dml" statements as executed without executing them.
 */
public class FkLiquibase {
    private static final String QUARKUS_DATASOURCE = "quarkus.datasource.";
    private final String changeLogFilename;
    private final List<FkLiquibaseChangesetContext> contextList;

    public FkLiquibase(String changeLogFilename, FkLiquibaseChangesetContext... contexts) {
        this.changeLogFilename = changeLogFilename;
        this.contextList = asList(contexts);
    }

    /**
     * Execute Liquibase Update (Forward-Migrate)
     *
     * @param connection connection
     * @throws Exception internal-error during execution
     */
    public void executeUpdate(Connection connection) throws Exception {
        try (final ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader())) {
            final liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Map<String, Object> scopedSettings = new LinkedHashMap<>();
            scopedSettings.put(Scope.Attr.resourceAccessor.name(), resourceAccessor);
            Scope.child(scopedSettings, () -> {
                final CommandScope updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME);
                updateCommand.addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database);
                updateCommand.addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, changeLogFilename);
                updateCommand.addArgumentValue(ShowSummaryArgument.SHOW_SUMMARY, UpdateSummaryEnum.SUMMARY);
                if (!contextList.isEmpty()) {
                    updateCommand.addArgumentValue(UpdateSqlCommandStep.CONTEXTS_ARG,
                            String.join(",", contextList.stream().map(FkLiquibaseChangesetContext::getValue).toList()));
                }
                updateCommand.execute();
            });
        }
    }

    /**
     * Execute Liquibase Rollback (Backward-Migrate) to a given Tag.
     *
     * @param connection connection
     * @param rollbackTag tag to rollback-to.
     * @throws Exception internal-error during execution
     */
    public void executeRollback(Connection connection, String rollbackTag) throws Exception {
        try (final ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader())) {
            final liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Map<String, Object> scopedSettings = new LinkedHashMap<>();
            scopedSettings.put(Scope.Attr.resourceAccessor.name(), resourceAccessor);
            Scope.child(scopedSettings, () -> {
                final CommandScope rollbackCommand = new CommandScope(RollbackCommandStep.COMMAND_NAME);
                rollbackCommand.addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database);
                rollbackCommand.addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, changeLogFilename);
                rollbackCommand.addArgumentValue(ShowSummaryArgument.SHOW_SUMMARY, UpdateSummaryEnum.SUMMARY);
                rollbackCommand.addArgumentValue(RollbackCommandStep.TAG_ARG, rollbackTag);
                if (!contextList.isEmpty()) {
                    rollbackCommand.addArgumentValue(UpdateSqlCommandStep.CONTEXTS_ARG,
                            String.join(",", contextList.stream().map(FkLiquibaseChangesetContext::getValue).toList()));
                }
                rollbackCommand.execute();
            });
        }
    }

    /**
     * Main-Execute, can be used from Gradle Build-Files.
     *
     * @param args args
     * @throws Exception internal-error
     */
    public static void main(String[] args) throws Exception {
        final Map<String, List<String>> params = parseArgs(args);

        // resolve our arguments.
        final String databaseId = params.get("databaseId").getFirst();
        final String changeLogFilename = params.get("changeLogFilename").getFirst();
        final List<FkLiquibaseChangesetContext> contexts = params.getOrDefault("contexts", new ArrayList<>())
                .stream().map(FkLiquibaseChangesetContext::fromValue).toList();
        final List<String> modeList = params.get("mode");

        // resolve our database-connection to the target-database.
        final Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new MappingException(e);
        }
        final String jdbcUrl = properties.getProperty(QUARKUS_DATASOURCE + databaseId + ".jdbc.url");
        final String username = properties.getProperty(QUARKUS_DATASOURCE + databaseId + ".username");
        final String password = properties.getProperty(QUARKUS_DATASOURCE + databaseId + ".password");

        // execute the desired command (update, rollback)
        final FkLiquibase fkLiquibase = new FkLiquibase(changeLogFilename, contexts.toArray(new FkLiquibaseChangesetContext[0]));
        if (modeList.getFirst().equals("update")) {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                fkLiquibase.executeUpdate(connection);
            }
        } else if (modeList.getFirst().equals("rollback")) {
            final String rollbackTag = modeList.get(1);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                fkLiquibase.executeRollback(connection, rollbackTag);
            }
        }
    }

    /**
     * Helper-function to parse the given args to a better usable format,
     * where one arg can be given multiple options, like this:
     * --arg1 opt1 opt2 --arg2 opt3 opt4
     *
     * @param args args
     * @return parsed args with options
     */
    private static Map<String, List<String>> parseArgs(String[] args) {
        final Map<String, List<String>> params = new HashMap<>();
        List<String> options = null;
        for (final String a : args) {
            if (!a.isEmpty()) {
                if (a.charAt(0) == '-') {
                    if (a.length() < 2) {
                        throw new MappingException("Error at argument " + a);
                    }
                    options = new ArrayList<>();
                    params.put(a.substring(2), options);
                } else if (options != null) {
                    options.add(a);
                } else {
                    throw new MappingException("Illegal parameter usage");
                }
            }
        }
        return params;
    }

    /**
     * We want our changeSets to be defined for one of two contexts (ddl, dml),
     * so we can decide to only execute the "ddl" changeSets in specific situations,
     * and mark the "dml" only as executed without really executing them.
     */
    public enum FkLiquibaseChangesetContext {
        DDL_CONTEXT("ddl"),
        DML_CONTEXT("dml");

        private final String value;

        FkLiquibaseChangesetContext(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public static FkLiquibaseChangesetContext fromValue(String value) {
            if (value == null) {
                return null;
            }
            for (FkLiquibaseChangesetContext type : FkLiquibaseChangesetContext.values()) {
                if (type.value.equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid changeset context value: " + value);
        }
    }
}