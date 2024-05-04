package org.fk.core.dao;

import org.fk.core.test.database.coretestdatabase.tables.Post;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IPost;
import org.fk.core.test.database.coretestdatabase.tables.records.PostRecord;
import org.jooq.DSLContext;

import java.util.UUID;

/**
 * PostDAO
 */
public class PostDAO extends AbstractDAO<PostRecord, IPost, UUID> {

    public PostDAO(DSLContext dsl) {
        super(dsl, Post.POST);
    }

    @Override
    public UUID getId(PostRecord object) {
        return object.getId();
    }
}
