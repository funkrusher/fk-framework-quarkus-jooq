package org.fk.core.liquibase;

import liquibase.Contexts;
import liquibase.Scope;
import liquibase.UpdateSummaryEnum;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.UpdateSqlCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep;
import liquibase.command.core.helpers.ShowSummaryArgument;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import java.sql.Connection;
import java.util.*;

import static org.fk.core.liquibase.FkLiquibase.FkLiquibaseChangesetContext.DDL_CONTEXT;
import static org.fk.core.liquibase.FkLiquibase.FkLiquibaseChangesetContext.DML_CONTEXT;

public class FkLiquibase {
    private final String changeLogFilename;
    private final List<String> contextList = new ArrayList<>();

    public enum FkLiquibaseChangesetContext {
        DDL_CONTEXT,
        DML_CONTEXT
    }

    public FkLiquibase(String changeLogFilename, FkLiquibaseChangesetContext... contexts) {
        this.changeLogFilename = changeLogFilename;
        for (FkLiquibaseChangesetContext context : contexts) {
            if (context == DDL_CONTEXT) {
                contextList.add("ddl");
            } else if (context == DML_CONTEXT) {
                contextList.add("dml");
            }
        }
    }

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
                    updateCommand.addArgumentValue(UpdateSqlCommandStep.CONTEXTS_ARG, String.join(",", contextList));
                }
                updateCommand.execute();
            });
        }
    }

}
