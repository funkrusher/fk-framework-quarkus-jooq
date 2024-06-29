package org.fk.product.repository;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.FkQueryJooqMapper;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop.tables.Lang.LANG;

import org.fk.core.repository.AbstractRepository;
import org.fk.core.query.model.FkQuery;
import org.fk.product.dto.*;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, PRODUCT.PRODUCTID);
    }

    @Override
    protected SelectFinalStep<Record1<ProductDTO>> prepareQuery(FkQuery fkQuery) throws InvalidDataException {
        FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT.fields())
            .addMappableFields(PRODUCT_LANG.fields());

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
                    ).convertFrom(UserDTO::createOrNull),
                    multiset(
                        select(
                            PRODUCT.product_lang()
                        ).from(PRODUCT.product_lang())
                    ).convertFrom(r -> r.map(ProductLangDTO::create))
                ).convertFrom(ProductDTO::create)
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
