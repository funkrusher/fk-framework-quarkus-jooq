package org.fk.product.dao;

import jakarta.enterprise.context.ApplicationScoped;
import org.jooq.DSLContext;

/**
 * DAOFactory to create instances of context-scoped daos.
 */
@ApplicationScoped
public class DAOFactory {

    public ClientDAO createClientDAO(DSLContext dsl) {
        return new ClientDAO(dsl);
    }

    public ProductDAO createProductDAO(DSLContext dsl) { return new ProductDAO(dsl); }

    public ProductLangDAO createProductLangDAO(DSLContext dsl) { return new ProductLangDAO(dsl); }

    public LangDAO createLangDAO(DSLContext dsl) {
        return new LangDAO(dsl);
    }

    public UserDAO createUserDAO(DSLContext dsl) {
        return new UserDAO(dsl);
    }

    public UserRoleDAO createUserRoleDAO(DSLContext dsl) { return new UserRoleDAO(dsl); }

}
