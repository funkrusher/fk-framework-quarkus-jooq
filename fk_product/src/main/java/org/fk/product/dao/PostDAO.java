package org.fk.product.dao;

import org.fk.codegen.testshop.tables.Post;
import org.fk.codegen.testshop.tables.Task;
import org.fk.codegen.testshop.tables.interfaces.IPost;
import org.fk.codegen.testshop.tables.interfaces.ITask;
import org.fk.codegen.testshop.tables.records.PostRecord;
import org.fk.codegen.testshop.tables.records.TaskRecord;
import org.fk.core.dao.AbstractDAO;
import org.jooq.DSLContext;

/**
 * PostDAO
 */
public class PostDAO extends AbstractDAO<PostRecord, IPost, Long> {

    public PostDAO(DSLContext dsl) {
        super(dsl, Post.POST);
    }

    @Override
    public Long getId(PostRecord object) {
        return object.getId();
    }
}
