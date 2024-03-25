package org.fk.product.dao;

import jakarta.enterprise.context.ApplicationScoped;
import org.jooq.DSLContext;

/**
 * DAOFactory to create instances of context-scoped daos.
 */
@ApplicationScoped
public class DAOFactory {

    public ClientRecordDAO createClientRecordDAO(DSLContext dsl) {
        return new ClientRecordDAO(dsl);
    }

    public ProductRecordDAO createProductRecordDAO(DSLContext dsl) { return new ProductRecordDAO(dsl); }

    public ProductViewDAO createProductViewDAO(DSLContext dsl) {
        return new ProductViewDAO(dsl);
    }

    public ProductLangRecordDAO createProductLangRecordDAO(DSLContext dsl) { return new ProductLangRecordDAO(dsl); }

    public LangRecordDAO createLangRecordDAO(DSLContext dsl) {
        return new LangRecordDAO(dsl);
    }

    public UserRecordDAO createUserRecordDAO(DSLContext dsl) {
        return new UserRecordDAO(dsl);
    }

    public UserRoleRecordDAO createUserRoleRecordDAO(DSLContext dsl) { return new UserRoleRecordDAO(dsl); }

}
