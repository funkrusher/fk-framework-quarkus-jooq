package org.fk.coreTestDatabase;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;

@ApplicationScoped
public class CoreTestDatabaseConfigurationFactory {

    public static final Logger LOGGER = Logger.getLogger(CoreTestDatabaseConfigurationFactory.class);

    // pleas use AgroalDataSource and not DataSource to avoid Build-Time Error.
    @Inject
    @DataSource("coreTestDatabase")
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
                            .withRenderNameCase(RenderNameCase.AS_IS)
                    );
        }
        return configuration;
    }
}
