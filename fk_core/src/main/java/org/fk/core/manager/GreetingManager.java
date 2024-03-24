package org.fk.core.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import org.fk.core.jooq.JooqContextFactory;
import org.fk.core.dao.DAOFactory;
import org.jboss.logging.Logger;

/**
 * GreetingManager
 */
@ApplicationScoped
public class GreetingManager extends AbstractBaseManager {

    private static final Logger LOGGER = Logger.getLogger(GreetingManager.class);

    @Inject
    JooqContextFactory jooqContextFactory;

    @Inject
    DAOFactory daoFactory;


    public String test() {
        return "Hello my world";
    }
}
