package org.fk.product.manager;

import com.github.f4b6a3.ulid.UlidCreator;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.fk.core.manager.AbstractManager;
import org.fk.core.util.exception.ValidationException;
import org.fk.product.dao.PostDAO;
import org.fk.product.dto.PostDTO;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;

/**
 * ProductManager
 */
@ApplicationScoped
public class PostManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(PostManager.class);

    @Transactional(rollbackOn = Exception.class)
    public PostDTO create(DSLContext dsl) throws ValidationException {
        // post will be given a ULID uuid automatically during the insert call on the dao.
        PostDTO post = new PostDTO();
        PostDAO postDAO = new PostDAO(dsl);
        postDAO.insert(post);
        return post;
    }
}
