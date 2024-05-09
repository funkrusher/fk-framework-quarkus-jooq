package org.fk.root.init;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.fk.core.init.ModuleInitializer;
import org.fk.database1.Database1ConfigurationFactory;
import org.fk.database1.testshop.Testshop;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

@Startup
@ApplicationScoped
public class RootModuleInit {
    private static final String ROOT_MODULE_ID = "root";
    public static final Logger LOGGER = Logger.getLogger(RootModuleInit.class);

    @Inject
    RootModuleInit(
            final ModuleInitializer moduleInitializer,
            final Database1ConfigurationFactory configurationFactory) {

        LOGGER.info("RootModuleInit ...");

        final DSLContext dsl = DSL.using(configurationFactory.getConfiguration());
        moduleInitializer.init(dsl, ROOT_MODULE_ID, Testshop.TESTSHOP.getQualifiedName(), tx1 -> {

        });
    }
}
