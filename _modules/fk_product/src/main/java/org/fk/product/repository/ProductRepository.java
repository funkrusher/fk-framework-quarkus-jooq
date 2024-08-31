package org.fk.product.repository;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.QueryJooqMapper;

import static org.fk.database1.testshop.Tables.ROLE;
import static org.fk.database1.testshop.Tables.USER_ROLE;
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

import java.util.stream.Collectors;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT.PRODUCTID);
    }

    public SelectFinalStep<Record1<JSON>> getFullQuery(FkQuery fkQuery) throws InvalidDataException {
        final QueryJooqMapper queryJooqMapper = new QueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT)
            .addMappableFields(PRODUCT_LANG);

        return dsl()
            .select(
                jsonObject(
                    key("productId").value(PRODUCT.PRODUCTID),
                    key("price").value(PRODUCT.PRICE),

                    key("userId").value(USER.USERID),
                    key("email").value(USER.EMAIL),
                    key("firstName").value(USER.FIRSTNAME),
                    key("lastName").value(USER.LASTNAME),
                    key("roles").value(
                        select(
                            jsonArrayAgg(
                                jsonObject(
                                    key("role").value(ROLE.ROLEID)
                                )
                            )
                        ).from(USER).join(USER_ROLE).on(USER_ROLE.USERID.eq(USER.USERID))
                            .join(ROLE).on(ROLE.ROLEID.eq(USER_ROLE.ROLEID))
                            .where(USER.USERID.eq(PRODUCT.CREATORID))
                    ),
                    key("langs").value(
                        select(
                            jsonArrayAgg(
                                jsonObject(
                                    key("name").value(PRODUCT_LANG.NAME),
                                    key("langId").value(PRODUCT_LANG.LANGID)
                                )
                            )
                        ).from(PRODUCT_LANG).where(PRODUCT_LANG.PRODUCTID.eq(PRODUCT.PRODUCTID))
                    )
                )
            )
            .from(PRODUCT
                .leftJoin(PRODUCT_LANG).on(PRODUCT_LANG.PRODUCTID.eq(PRODUCT.PRODUCTID))
                .leftJoin(LANG).on(LANG.LANGID.eq(PRODUCT_LANG.LANGID))
                .leftJoin(USER).on(USER.USERID.eq(PRODUCT.CREATORID))
                .leftJoin(USER_ROLE).on(USER_ROLE.USERID.eq(USER.USERID))
                .leftJoin(ROLE).on(ROLE.ROLEID.eq(USER_ROLE.ROLEID)))
            .where(queryJooqMapper.getFilters())
            .and(PRODUCT.CLIENTID.eq(request().getClientId()))
            .groupBy(PRODUCT.PRODUCTID)
            .orderBy(queryJooqMapper.getSorter())
            .offset(queryJooqMapper.getOffset())
            .limit(queryJooqMapper.getLimit());
    }
}
