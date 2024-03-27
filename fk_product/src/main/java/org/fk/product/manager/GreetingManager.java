package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.manager.AbstractManager;
import org.jboss.logging.Logger;

/**
 * GreetingManager
 */
@ApplicationScoped
public class GreetingManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(GreetingManager.class);

    @Inject
    DSLFactory dslFactory;

    public String test() {
        return "Hello my world";
    }
}
