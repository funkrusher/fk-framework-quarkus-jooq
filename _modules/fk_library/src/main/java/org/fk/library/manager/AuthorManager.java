package org.fk.library.manager;

import jakarta.enterprise.context.ApplicationScoped;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.core.query.jooq.QueryExecutor;
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

    public AuthorPaginateDTO query(DSLContext dsl, final FkQuery fkQuery) throws InvalidDataException {
        final AuthorRepository repo = new AuthorRepository(dsl);
        final QueryExecutor<AuthorDTO, Integer> selectFull = repo.executor(repo::getFullQuery);

        int count = selectFull.count(fkQuery.getFilters());
        List<AuthorDTO> authors = selectFull.query(fkQuery);

        AuthorPaginateDTO paginate = new AuthorPaginateDTO();
        paginate.setAuthors(authors);
        paginate.setCount(count);
        return paginate;
    }
}
