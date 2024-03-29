package org.fk.core.liquibase;

import liquibase.Scope;
import liquibase.UpdateSummaryEnum;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep;
import liquibase.command.core.helpers.ShowSummaryArgument;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.DirectoryResourceAccessor;
import liquibase.resource.ResourceAccessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FkLiquibase {

    private final String changeLogFilename;

    public FkLiquibase(String liquibaseFolder, String changeLogFilename) {
        this.changeLogFilename = changeLogFilename;
    }

    public void executeUpdate(Connection connection) throws Exception {
        final ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader());
        final liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Map<String, Object> scopedSettings = new LinkedHashMap<>();
        scopedSettings.put(Scope.Attr.resourceAccessor.name(), resourceAccessor);
        Scope.child(scopedSettings, () -> {
            final CommandScope updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME);
            updateCommand.addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database);
            updateCommand.addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, changeLogFilename);
            updateCommand.addArgumentValue(ShowSummaryArgument.SHOW_SUMMARY, UpdateSummaryEnum.SUMMARY);
            updateCommand.execute();
        });
    }

}
