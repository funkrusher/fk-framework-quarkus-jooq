package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.manager.AbstractManager;
import org.fk.database1.Database1;

/**
 * GreetingManager
 */
@ApplicationScoped
public class GreetingManager extends AbstractManager {
    private static final String HELLO_WORLD = "Hello my world";

    @Inject
    Database1 database1;

    public String test() {
        return HELLO_WORLD;
    }
}
