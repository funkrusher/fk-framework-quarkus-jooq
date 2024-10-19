package org.fk.product.repository;

import org.fk.framework.exception.InvalidDataException;
import org.fk.framework.query.jooq.QueryJooqMapper;
import org.fk.framework.query.model.FkQuery;
import org.fk.framework.repository.AbstractRepository;
import org.fk.product.dto.*;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.fk.database1.testshop.tables.Lang.LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductResponse, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT.PRODUCTID);
    }

    public SelectFinalStep<Record1<ProductResponse>> getFullQuery(FkQuery fkQuery) throws InvalidDataException {
        final QueryJooqMapper queryJooqMapper = new QueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT)
            .addMappableFields(PRODUCT_LANG);

        return dsl()
            .select(
                row(
                    PRODUCT,
                    row(
                        PRODUCT.user(),
                        multiset(
                            select(
                                PRODUCT.user().userRole().role().ROLEID
                            ).from(PRODUCT.user().userRole().role())
                        ).convertFrom(r -> r.map(RoleResponse::create))
                    ).convertFrom(UserResponse::createOrNull),
                    multiset(
                        select(
                            PRODUCT.productLang(),
                            row(
                                PRODUCT.productLang().lang()
                            ).convertFrom(LangResponse::create)
                        ).from(PRODUCT.productLang())
                    ).convertFrom(r -> r.map(ProductLangResponse::create))
                ).convertFrom(ProductResponse::create)
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
