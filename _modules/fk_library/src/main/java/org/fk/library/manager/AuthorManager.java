package org.fk.library.manager;

import jakarta.enterprise.context.ApplicationScoped;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.library.dto.AuthorDTO;
import org.fk.library.dto.AuthorPaginateDTO;
import org.fk.library.repository.AuthorRepository;
import org.jooq.DSLContext;
import org.fk.core.manager.AbstractManager;

import java.util.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class AuthorManager extends AbstractManager {

    public AuthorPaginateDTO query(DSLContext dsl, final FkQuery query) throws InvalidDataException {
        final AuthorRepository authorRepository = new AuthorRepository(dsl);

        int count = authorRepository.countQuery(query);
        List<Integer> authorIds = authorRepository.paginateQuery(query);
        List<AuthorDTO> authors = authorRepository.fetch(authorIds);

        AuthorPaginateDTO paginate = new AuthorPaginateDTO();
        paginate.setAuthors(authors);
        paginate.setCount(count);
        return paginate;
    }
}
