package org.fk.core.jooq;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.impl.DefaultRecordListenerProvider;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * DSLFactory to create instances of jooq dsl-context.
 */
@ApplicationScoped
public class DSLFactory {

    public static final Logger LOGGER = Logger.getLogger(DSLFactory.class);

    @Inject
    DataSource dataSource;

    SQLDialect sqlDialect = null;

    public DSLContext create(RequestContext request) {
        try {
            return DSL.using(getConfiguration(request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Configuration getConfiguration(RequestContext request) {
        if (sqlDialect == null) {
            try {
                final Connection connection = dataSource.getConnection();
                sqlDialect = DSL.using(connection).configuration().dialect();
            } catch (Exception e) {
                LOGGER.error("unable to resolve SQLDialect from database!", e);
                throw new RuntimeException(e);
            }
        }
        Configuration configuration = new DefaultConfiguration()
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
        configuration.set(new DefaultRecordListenerProvider(new FkInsertListener()));
        configuration.set(new DefaultExecuteListenerProvider(new FkExecuteListener(request)));
        configuration.data("request", request);
        return configuration;
    }
}
