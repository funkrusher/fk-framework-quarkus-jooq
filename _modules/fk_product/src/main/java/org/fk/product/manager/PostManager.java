package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.manager.AbstractManager;
import org.fk.core.exception.ValidationException;
import org.fk.core.request.RequestContext;
import org.fk.database1.Database1;
import org.fk.database1.testshop.tables.dtos.PostDto;
import org.fk.product.dao.PostDAO;

/**
 * PostManager
 */
@ApplicationScoped
public class PostManager extends AbstractManager {

    @Inject
    Database1 database1;

    public PostDto create(RequestContext requestContext) throws ValidationException {
        return database1.dsl(requestContext).transactionResult(tsx -> {
            // post will be given a ULID uuid automatically during the insert call on the dao.
            PostDto post = new PostDto();
            PostDAO postDAO = new PostDAO(tsx.dsl());
            postDAO.insert(post);
            return post;
        });
    }
}
