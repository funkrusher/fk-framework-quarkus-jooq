package org.fk.database1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.core.exception.MappingException;
import org.fk.core.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import static org.fk.core.request.RequestContext.DSL_DATA_KEY;

/**
 * DSLFactory to create request-specific instances of jooq dsl-context.
 * <p>
 * It must be request-scoped, because we need to configure tenant-specific checks on the DSLContext,
 * that need to be provided by the requests SecurityIdentity (tenant_id).
 * </p>
 */
@RequestScoped
public class Database1 {

    public static final Logger LOGGER = Logger.getLogger(Database1.class);

    @Inject
    Database1ConfigurationFactory configurationFactory;

    @Inject
    FkSecurityIdentity fkSecurityIdentity;

    public DSLContext dsl(RequestContext customRequest) {
        try {
            DSLContext dsl = DSL.using(configurationFactory.getConfiguration());

            // put request into dsl (to make tenant-checks, and provide other layers with access to it)
            dsl.data(DSL_DATA_KEY, customRequest);
            return dsl;

        } catch (Exception e) {
            throw new MappingException(e);
        }
    }

}
