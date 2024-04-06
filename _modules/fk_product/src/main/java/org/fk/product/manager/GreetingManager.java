package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.manager.AbstractManager;
import org.fk.database1.Database1;
import org.jboss.logging.Logger;

/**
 * GreetingManager
 */
@ApplicationScoped
public class GreetingManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(GreetingManager.class);

    @Inject
    Database1 database1;

    public String test() {
        return "Hello my world";
    }
}
