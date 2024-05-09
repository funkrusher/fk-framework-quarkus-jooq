package org.fk.backend1.startup;

import io.quarkus.runtime.Startup;
import io.smallrye.config.Priorities;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.core.init.DataInitPlan;
import org.fk.database1.Database1ConfigurationFactory;
import org.fk.product.init.ProductBasicDataInit;
import org.fk.product.init.ProductTestDataInit;
import org.fk.root.init.RootBasicDataInit;
import org.fk.root.init.RootTestDataInit;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * Backend1Startup
 */
@Singleton
public class Backend1Startup {
    @ConfigProperty(name = "org.fk.backend1.init-data-enabled", defaultValue = "false")
    Boolean initDataEnabled;
    @Inject
    Database1ConfigurationFactory configurationFactory;

    @Startup
    @Priority(Priorities.APPLICATION)
    public void startup() {
        if (initDataEnabled) {
            final DSLContext dsl = DSL.using(configurationFactory.getConfiguration());
            new DataInitPlan()
                    .withPersistedPlan(true)
                    .add(new RootBasicDataInit())
                    .add(new ProductBasicDataInit())
                    .execute(dsl);
        }
    }
}
