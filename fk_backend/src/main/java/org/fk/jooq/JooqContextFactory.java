package org.fk.jooq;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jetbrains.annotations.NotNull;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.*;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.util.Arrays;

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

    public JooqContext createJooqContext(RequestContext requestContext) {
        try {
            DSLContext ctx = DSL.using(getConfiguration(requestContext));
            return new JooqContext(requestContext, ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Configuration getConfiguration(RequestContext requestContext) {
        final Optional<@NotNull SQLDialect> maybeSqlDialect =
                Arrays.stream(SQLDialect.families()).filter(x -> x.name().equalsIgnoreCase(jooqDialect)).findFirst();
        if (maybeSqlDialect.isEmpty()) {
            throw new RuntimeException("sql-dialect not found! " + jooqDialect);
        }
        final SQLDialect sqlDialect = maybeSqlDialect.get();

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
