package org.fk.product.dao;

import org.fk.database1.testshop.tables.Post;
import org.fk.database1.testshop.tables.records.PostRecord;
import org.fk.core.dao.AbstractDAO;
import org.jooq.DSLContext;

import java.util.UUID;

/**
 * PostDAO
 */
public class PostDAO extends AbstractDAO<PostRecord, UUID> {

    public PostDAO(DSLContext dsl) {
        super(dsl, Post.POST);
    }
}
