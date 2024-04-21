package org.fk.library.manager;

import jakarta.enterprise.context.ApplicationScoped;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.QueryParameters;
import org.fk.library.dto.AuthorDTO;
import org.fk.library.dto.AuthorPaginateDTO;
import org.fk.library.repository.AuthorRepository;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.fk.core.manager.AbstractManager;

import java.util.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class AuthorManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(AuthorManager.class);

    public AuthorPaginateDTO query(DSLContext dsl, final QueryParameters queryParameters) throws InvalidDataException {
        final AuthorRepository authorRepository = new AuthorRepository(dsl);

        int count = authorRepository.count(queryParameters);
        List<Integer> authorIds = authorRepository.paginate(queryParameters);
        List<AuthorDTO> authors = authorRepository.fetch(authorIds);

        AuthorPaginateDTO paginate = new AuthorPaginateDTO();
        paginate.setAuthors(authors);
        paginate.setCount(count);
        return paginate;
    }
}
