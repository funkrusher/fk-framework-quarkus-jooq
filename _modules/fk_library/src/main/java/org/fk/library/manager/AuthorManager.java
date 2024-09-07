package org.fk.library.manager;

import jakarta.enterprise.context.ApplicationScoped;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.core.query.jooq.QueryExecutor;
import org.fk.library.dto.NestedAuthorDTO;
import org.fk.library.dto.NestedAuthorPaginateResultDTO;
import org.fk.library.repository.AuthorRepository;
import org.jooq.DSLContext;
import org.fk.core.manager.AbstractManager;

import java.util.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class AuthorManager extends AbstractManager {

    public NestedAuthorPaginateResultDTO queryNested(DSLContext dsl, final FkQuery fkQuery) throws InvalidDataException {
        final AuthorRepository repo = new AuthorRepository(dsl);
        final QueryExecutor<NestedAuthorDTO, Integer> selectFull = repo.executor(repo::getFullQuery);

        int count = selectFull.count(fkQuery.getFilters());
        List<NestedAuthorDTO> authors = selectFull.query(fkQuery);

        NestedAuthorPaginateResultDTO paginate = new NestedAuthorPaginateResultDTO();
        paginate.setAuthors(authors);
        paginate.setCount(count);
        return paginate;
    }
}
