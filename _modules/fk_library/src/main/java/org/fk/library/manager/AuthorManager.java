package org.fk.library.manager;

import jakarta.enterprise.context.ApplicationScoped;
import org.fk.framework.exception.InvalidDataException;
import org.fk.framework.query.model.FkQuery;
import org.fk.framework.query.jooq.QueryExecutor;
import org.fk.library.dto.AuthorDTO;
import org.fk.library.dto.QueryAuthorResponse;
import org.fk.library.repository.AuthorRepository;
import org.jooq.DSLContext;
import org.fk.framework.manager.AbstractManager;

import java.util.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class AuthorManager extends AbstractManager {

    public QueryAuthorResponse queryNested(DSLContext dsl, final FkQuery fkQuery) throws InvalidDataException {
        final AuthorRepository repo = new AuthorRepository(dsl);
        final QueryExecutor<AuthorDTO, Integer> selectFull = repo.executor(repo::getFullQuery);

        int count = selectFull.count(fkQuery.getFilters());
        List<AuthorDTO> authors = selectFull.query(fkQuery);

        return new QueryAuthorResponse(authors, count);
    }
}
