package org.fk.library.repository;


import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
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

    private final FkQueryJooqMapper queryJooqMapper;

    public AuthorRepository(DSLContext dsl) {
        super(dsl);

        final List<Field<?>> mappableFields = new ArrayList<>();
        mappableFields.addAll(List.of(Author.AUTHOR.fields()));
        mappableFields.addAll(List.of(Book.BOOK.fields()));
        this.queryJooqMapper = new FkQueryJooqMapper(Author.AUTHOR, mappableFields);
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
    public SelectSeekStepN<Record1<Integer>> mapQuery(FkQuery query) throws InvalidDataException {
        return dsl()
                .select(Author.AUTHOR.AUTHOR_ID)
                .from(Author.AUTHOR
                        .leftJoin(Book.BOOK)
                        .on(Book.BOOK.AUTHOR_ID
                                .eq(Author.AUTHOR.AUTHOR_ID)))
                .where(DSL.and(queryJooqMapper.getFilters(query)))
                .groupBy(Author.AUTHOR.AUTHOR_ID)
                .orderBy(queryJooqMapper.getSorter(query));
    }

    @Override
    public List<Integer> paginateQuery(FkQuery query) throws InvalidDataException {
        return mapQuery(query)
                .offset(query.getPage())
                .limit(query.getPageSize())
                .fetch(Author.AUTHOR.AUTHOR_ID);
    }

    @Override
    public int countQuery(FkQuery query) throws InvalidDataException {
        return dsl().fetchCount(mapQuery(query));
    }

    @Override
    public Stream<Integer> streamQuery(FkQuery query) throws InvalidDataException {
        return mapQuery(query)
                .fetchSize(250)
                .fetchStream()
                .map(Record1::value1);
    }
}
