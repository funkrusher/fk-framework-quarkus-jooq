package org.fk.core.jooq;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.core.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;

import java.sql.Connection;

import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.impl.DefaultRecordListenerProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;

/**
 * JooqContextFactory to create instances of context-scoped jooq dsl-contexts.
 */
@ApplicationScoped
public class JooqContextFactory {

    public static final Logger LOGGER = Logger.getLogger(JooqContextFactory.class);
    @ConfigProperty(name = "jooq.dialect")
    String jooqDialect;

    @Inject
    DataSource dataSource;

    SQLDialect sqlDialect = null;

    public JooqContext createJooqContext(RequestContext requestContext) {
        try {
            DSLContext ctx = DSL.using(getConfiguration(requestContext));
            return new JooqContext(requestContext, ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Configuration getConfiguration(RequestContext requestContext) {
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
        configuration.set(new DefaultRecordListenerProvider(new JooqInsertListener()));
        configuration.set(new DefaultExecuteListenerProvider(new JooqExecuteListener(requestContext)));
        return configuration;
    }
}
