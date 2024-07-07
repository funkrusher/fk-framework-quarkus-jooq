package org.fk.backend2.startup;

import io.quarkus.runtime.Startup;
import io.smallrye.config.Priorities;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.core.init.DataInitPlan;
import org.fk.database2.Database2ConfigurationFactory;
import org.fk.library.init.LibraryBasicDataInit;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * Backend2Startup
 */
@Singleton
public class Backend2Startup {
    @ConfigProperty(name = "org.fk.backend2.init-data-enabled", defaultValue = "false")
    Boolean initDataEnabled;
    @Inject
    Database2ConfigurationFactory configurationFactory;

    @Startup
    @Priority(Priorities.APPLICATION)
    public void startup() {
        if (initDataEnabled) {
            final DSLContext dsl = DSL.using(configurationFactory.getConfiguration());
            new DataInitPlan()
                    .withPersistedPlan(true)
                    .add(new LibraryBasicDataInit())
                    .execute(dsl);
        }
    }
}
