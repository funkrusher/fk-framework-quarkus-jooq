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
        PostDTO post = new PostDTO();

        // mariadb uses RFC4122 UUID, we must use it here, or we get an error in insert.
        Ulid ulid = UlidCreator.getMonotonicUlid();
        post.setId(ulid.toRfc4122().toUuid());

        PostDAO postDAO = new PostDAO(dsl);
        postDAO.insert(post);
        return post;
    }
}
