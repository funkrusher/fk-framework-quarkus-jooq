package org.fk.library.repository;


import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.library.dto.AuthorDTO;
import org.fk.library.dto.BookDTO;
import org.jooq.*;

import static org.fk.database2.public_.Tables.AUTHOR;
import static org.fk.database2.public_.Tables.BOOK;
import static org.jooq.impl.DSL.*;

public class AuthorRepository extends AbstractRepository<AuthorDTO, Integer> {
    public AuthorRepository(DSLContext dsl) {
        super(dsl, AuthorDTO.class, AUTHOR.AUTHOR_ID);

    }

    @Override
    public SelectFinalStep<Record1<AuthorDTO>> prepareQuery(FkQuery query) throws InvalidDataException {
        final FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(query, AUTHOR)
                .addMappableFields(AUTHOR)
                .addMappableFields(BOOK);

        return dsl()
                .select(
                    row(
                        AUTHOR,
                        multiset(
                            select(
                                AUTHOR.book()
                            ).from(AUTHOR.book())
                        ).convertFrom(r -> r.map(BookDTO::create))
                    ).convertFrom(AuthorDTO::create)
                )
                .from(AUTHOR)
                .leftJoin(BOOK).on(BOOK.AUTHOR_ID.eq(AUTHOR.AUTHOR_ID))
                .where(queryJooqMapper.getFilters())
                .groupBy(AUTHOR.AUTHOR_ID)
                .orderBy(queryJooqMapper.getSorter())
                .offset(queryJooqMapper.getOffset())
                .limit(queryJooqMapper.getLimit());
    }
}
