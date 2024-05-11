package org.fk.library.repository;


import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.database2.public_.tables.Book;
import org.fk.database2.public_.tables.Author;
import org.fk.library.dto.AuthorDTO;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.*;

public class AuthorRepository extends AbstractRepository<AuthorDTO, Integer> {
    public AuthorRepository(DSLContext dsl) {
        super(dsl, AuthorDTO.class, Author.AUTHOR.AUTHOR_ID);

    }

    @Override
    public SelectFinalStep<Record> prepareQuery(FkQuery query) throws InvalidDataException {
        final FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(query, Author.AUTHOR)
                .addMappableFields(Author.AUTHOR.fields())
                .addMappableFields(Book.BOOK.fields());

        return dsl()
                .select(
                        asterisk(),
                        multiset(
                                selectDistinct(asterisk())
                                        .from(Book.BOOK)
                                        .where(Book.BOOK.AUTHOR_ID.eq(Author.AUTHOR.AUTHOR_ID))
                        ).as("books"))
                .from(Author.AUTHOR
                        .leftJoin(Book.BOOK)
                        .on(Book.BOOK.AUTHOR_ID
                                .eq(Author.AUTHOR.AUTHOR_ID)))
                .where(queryJooqMapper.getFilters())
                .groupBy(Author.AUTHOR.AUTHOR_ID)
                .orderBy(queryJooqMapper.getSorter())
                .offset(queryJooqMapper.getOffset())
                .limit(queryJooqMapper.getLimit());
    }
}
