package org.fk.product.repository;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.QueryJooqMapper;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop.tables.Lang.LANG;

import org.fk.core.query.model.FkQuery;
import org.fk.core.repository.AbstractRepository;
import org.fk.product.dto.*;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<NestedProductDTO, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT.PRODUCTID);
    }

    public SelectFinalStep<Record1<NestedProductDTO>> getFullQuery(FkQuery fkQuery) throws InvalidDataException {
        final QueryJooqMapper queryJooqMapper = new QueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT)
            .addMappableFields(PRODUCT_LANG);

        return dsl()
            .select(
                row(
                    PRODUCT,
                    row(
                        PRODUCT.creator(),
                        multiset(
                            select(
                                PRODUCT.creator().user_role().role()
                            ).from(PRODUCT.creator().user_role().role())
                        ).convertFrom(r -> r.map(RoleDTO::create))
                    ).convertFrom(NestedUserDTO::createOrNull),
                    multiset(
                        select(
                            PRODUCT.product_lang()
                        ).from(PRODUCT.product_lang())
                    ).convertFrom(r -> r.map(NestedProductLangDTO::create))
                ).convertFrom(NestedProductDTO::create)
            )
            .from(PRODUCT
                .leftJoin(PRODUCT_LANG).on(PRODUCT_LANG.PRODUCTID.eq(PRODUCT.PRODUCTID))
                .leftJoin(LANG).on(LANG.LANGID.eq(PRODUCT_LANG.LANGID))
                .leftJoin(USER).on(USER.USERID.eq(PRODUCT.CREATORID)))
            .where(queryJooqMapper.getFilters())
            .and(PRODUCT.CLIENTID.eq(request().getClientId()))
            .groupBy(PRODUCT.PRODUCTID)
            .orderBy(queryJooqMapper.getSorter())
            .offset(queryJooqMapper.getOffset())
            .limit(queryJooqMapper.getLimit());
    }
}
