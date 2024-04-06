package org.fk.product.dao;

import org.fk.database1.testshop.tables.Post;
import org.fk.database1.testshop.tables.Task;
import org.fk.database1.testshop.tables.interfaces.IPost;
import org.fk.database1.testshop.tables.interfaces.ITask;
import org.fk.database1.testshop.tables.records.PostRecord;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.fk.core.dao.AbstractDAO;
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
