package org.fk.database2;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.core.jooq.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import static org.fk.core.jooq.RequestContext.DSL_DATA_KEY;

/**
 * DSLFactory to create request-specific instances of jooq dsl-context.
 * <p>
 * It must be request-scoped, because we need to configure tenant-specific checks on the DSLContext,
 * that need to be provided by the requests SecurityIdentity (tenant_id).
 * </p>
 */
@RequestScoped
public class Database2 {

    public static final Logger LOGGER = Logger.getLogger(Database2.class);

    @Inject
    Database2ConfigurationFactory configurationFactory;

    @Inject
    FkSecurityIdentity fkSecurityIdentity;

    @Produces
    @DSLContext2
    public DSLContext dsl() {
        try {
            DSLContext dsl = DSL.using(configurationFactory.getConfiguration());

            // put request into dsl (to make tenant-checks, and provide other layers with access to it)
            RequestContext request = new RequestContext(fkSecurityIdentity, 1);
            dsl.data(DSL_DATA_KEY, request);
            return dsl;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DSLContext dsl(RequestContext customRequest) {
        try {
            DSLContext dsl = DSL.using(configurationFactory.getConfiguration());

            // put request into dsl (to make tenant-checks, and provide other layers with access to it)
            dsl.data(DSL_DATA_KEY, customRequest);
            return dsl;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
