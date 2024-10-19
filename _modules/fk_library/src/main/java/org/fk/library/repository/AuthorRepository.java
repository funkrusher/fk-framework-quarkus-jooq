package org.fk.library.repository;


import org.fk.framework.query.jooq.QueryJooqMapper;
import org.fk.framework.query.model.FkQuery;
import org.fk.framework.repository.AbstractRepository;
import org.fk.framework.exception.InvalidDataException;
import org.fk.library.dto.AuthorDTO;
import org.fk.library.dto.BookDTO;
import org.jooq.*;

import static org.fk.database2.public_.Tables.AUTHOR;
import static org.fk.database2.public_.Tables.BOOK;
import static org.jooq.impl.DSL.*;

public class AuthorRepository extends AbstractRepository<AuthorDTO, Integer> {
    public AuthorRepository(DSLContext dsl) {
        super(dsl, AUTHOR.AUTHOR_ID);
    }

    public SelectFinalStep<Record1<AuthorDTO>> getFullQuery(FkQuery fkQuery) throws InvalidDataException {
        final QueryJooqMapper queryJooqMapper = new QueryJooqMapper(fkQuery, AUTHOR)
            .addMappableFields(AUTHOR)
            .addMappableFields(BOOK);

        return dsl()
            .select(
                row(
                    AUTHOR.AUTHOR_ID,
                    AUTHOR.NAME,
                    AUTHOR.NATIONALITY,
                    AUTHOR.BIRTH_DATE,
                    AUTHOR.BIOGRAPHY,
                    multiset(
                        select(
                            AUTHOR.book().BOOK_ID,
                            AUTHOR.book().TITLE,
                            AUTHOR.book().AUTHOR_ID,
                            AUTHOR.book().GENRE,
                            AUTHOR.book().PUBLICATION_DATE,
                            AUTHOR.book().ISBN
                        ).from(AUTHOR.book())
                    ).convertFrom(r -> r.map(Records.mapping(BookDTO::new)))
                ).convertFrom(Records.mapping(AuthorDTO::new))
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
