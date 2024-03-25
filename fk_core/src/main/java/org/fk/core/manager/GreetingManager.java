package org.fk.core.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.dao.DAOFactory;
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
