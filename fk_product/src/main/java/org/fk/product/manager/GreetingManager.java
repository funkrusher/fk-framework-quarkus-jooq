package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.manager.AbstractBaseManager;
import org.fk.product.dao.DAOFactory;
import org.jboss.logging.Logger;

/**
 * GreetingManager
 */
@ApplicationScoped
public class GreetingManager extends AbstractBaseManager {

    private static final Logger LOGGER = Logger.getLogger(GreetingManager.class);

    @Inject
    DSLFactory dslFactory;

    @Inject
    DAOFactory daoFactory;


    public String test() {
        return "Hello my world";
    }
}
