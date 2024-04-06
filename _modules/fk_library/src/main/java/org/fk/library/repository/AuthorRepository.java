package org.fk.library.repository;


import jakarta.enterprise.context.ApplicationScoped;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.QueryParameters;
import org.fk.database2.public_.tables.Book;
import org.fk.database2.public_.tables.Author;
import org.fk.library.dto.AuthorDTO;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.*;

public class AuthorRepository extends AbstractRepository<AuthorDTO, Integer> {

    public AuthorRepository(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public List<AuthorDTO> fetch(List<Integer> authorIds) {
        // use multiset to let the database and jooq do all the work of joining the tables and mapping to dto.
        return dsl().select(
                        asterisk(),
                        multiset(
                                selectDistinct(asterisk())
                                        .from(Book.BOOK)
                                        .where(Book.BOOK.AUTHOR_ID.eq(Author.AUTHOR.AUTHOR_ID))
                        ).as("books")
                ).from(Author.AUTHOR)
                .where(Author.AUTHOR.AUTHOR_ID.in(authorIds))
                .fetchInto(AuthorDTO.class);
    }

    @Override
    public SelectSeekStepN<Record1<Integer>> getQuery(QueryParameters queryParameters) throws InvalidDataException {
        final List<Field<?>> availableFields = new ArrayList<>();
        availableFields.addAll(List.of(Author.AUTHOR.fields()));
        availableFields.addAll(List.of(Book.BOOK.fields()));

        return dsl()
                .select(Author.AUTHOR.AUTHOR_ID)
                .from(Author.AUTHOR
                        .leftJoin(Book.BOOK)
                        .on(Book.BOOK.AUTHOR_ID
                                .eq(Author.AUTHOR.AUTHOR_ID)))
                .where(DSL.and(getFilters(queryParameters, availableFields, Author.AUTHOR)))
                .groupBy(Author.AUTHOR.AUTHOR_ID)
                .orderBy(getSorters(queryParameters, availableFields, Author.AUTHOR));
    }

    @Override
    public List<Integer> paginate(QueryParameters queryParameters) throws InvalidDataException {
        return getQuery(queryParameters)
                .offset(queryParameters.getPage())
                .limit(queryParameters.getPageSize())
                .fetch(Author.AUTHOR.AUTHOR_ID);
    }

    @Override
    public int count(QueryParameters queryParameters) throws InvalidDataException {
        return dsl().fetchCount(getQuery(queryParameters));
    }

    public Stream<Integer> stream(QueryParameters queryParameters) throws InvalidDataException {
        return getQuery(queryParameters)
                .fetchSize(250)
                .fetchStream()
                .map(Record1::value1);
    }
}
