package org.fk.core.jooq;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.fk.core.auth.FkSecurityIdentity;
import org.jboss.logging.Logger;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * DSLFactory to create request-specific instances of jooq dsl-context.
 * <p>
 * It must be request-scoped, because we need to configure tenant-specific checks on the DSLContext,
 * that need to be provided by the requests SecurityIdentity (tenant_id).
 * </p>
 */
@RequestScoped
public class DSLFactory {

    public static final Logger LOGGER = Logger.getLogger(DSLFactory.class);

    public static final String REQUEST = "request";

    @Inject
    Configuration configuration;

    @Inject
    FkSecurityIdentity fkSecurityIdentity;

    @Produces
    public DSLContext create() {
        try {
            DSLContext dsl = DSL.using(configuration);

            // put request into dsl (to make tenant-checks, and provide other layers with access to it)
            RequestContext request = new RequestContext(fkSecurityIdentity, 1);
            dsl.data(REQUEST, request);
            return dsl;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DSLContext create(RequestContext customRequest) {
        try {
            DSLContext dsl = DSL.using(configuration);

            // put request into dsl (to make tenant-checks, and provide other layers with access to it)
            dsl.data(REQUEST, customRequest);
            return dsl;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
