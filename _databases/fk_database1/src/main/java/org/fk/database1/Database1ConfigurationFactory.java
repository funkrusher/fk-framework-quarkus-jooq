package org.fk.database1;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;

import java.sql.Connection;

import io.quarkus.agroal.DataSource;

@ApplicationScoped
public class Database1ConfigurationFactory {

    public static final Logger LOGGER = Logger.getLogger(Database1ConfigurationFactory.class);

    // pleas use AgroalDataSource and not DataSource to avoid Build-Time Error.
    @Inject
    @DataSource("database1")
    AgroalDataSource dataSource;

    private SQLDialect sqlDialect = null;

    private Configuration configuration;

    public Configuration getConfiguration() {
        if (configuration == null) {
            if (sqlDialect == null) {
                try (final Connection connection = dataSource.getConnection()) {
                    sqlDialect = DSL.using(connection).configuration().dialect();
                } catch (Exception e) {
                    LOGGER.error("unable to resolve SQLDialect from database!", e);
                    throw new RuntimeException(e);
                }
            }
            configuration = new DefaultConfiguration()
                    .set(dataSource)
                    .set(sqlDialect)
                    .set(new Settings()
                            .withExecuteLogging(true)
                            .withRenderFormatted(true)
                            .withRenderCatalog(false)
                            .withRenderSchema(false)
                            .withMaxRows(Integer.MAX_VALUE)
                            .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                            .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED)
                    );
            configuration.set(new DefaultExecuteListenerProvider(new FkExecuteListener()));
        }
        return configuration;
    }
}
