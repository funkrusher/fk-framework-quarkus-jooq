package org.fk.database1;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.exception.MappingException;
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
                    throw new MappingException(e);
                }
            }
            configuration = new DefaultConfiguration()
                    .set(dataSource)
                    .set(sqlDialect)
                    .set(new Settings()
                            .withExecuteLogging(true)
                            .withRenderFormatted(true)
                            .withRenderCatalog(false)
                            .withRenderSchema(true) // make different databases in single db be recognized
                            .withMaxRows(Integer.MAX_VALUE)
                            .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                            .withRenderNameCase(RenderNameCase.AS_IS)
                    );
            configuration.set(new DefaultExecuteListenerProvider(new FkExecuteListener()));
        }
        return configuration;
    }
}
