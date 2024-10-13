package org.fk.product.repository;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.QueryJooqMapper;

import static org.fk.core.jooq.JooqHelper.nullableMapping;
import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop.tables.Lang.LANG;

import org.fk.core.query.model.FkQuery;
import org.fk.core.repository.AbstractRepository;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.LangDTO;
import org.fk.product.dto.RoleDTO;
import org.fk.product.dto.UserDTO;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT.PRODUCTID);
    }

    public SelectFinalStep<Record1<ProductDTO>> getFullQuery(FkQuery fkQuery) throws InvalidDataException {
        final QueryJooqMapper queryJooqMapper = new QueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT)
            .addMappableFields(PRODUCT_LANG);

        return dsl()
            .select(
                row(
                    PRODUCT.PRODUCTID,
                    PRODUCT.CLIENTID,
                    PRODUCT.PRICE,
                    PRODUCT.TYPEID,
                    PRODUCT.CREATEDAT,
                    PRODUCT.UPDATEDAT,
                    PRODUCT.DELETED,
                    PRODUCT.CREATORID,
                    row(
                        PRODUCT.creator().USERID,
                        PRODUCT.creator().CLIENTID,
                        PRODUCT.creator().EMAIL,
                        PRODUCT.creator().FIRSTNAME,
                        PRODUCT.creator().LASTNAME,
                        multiset(
                            select(
                                PRODUCT.creator().user_role().role().ROLEID
                            ).from(PRODUCT.creator().user_role().role())
                        ).convertFrom(r -> r.map(Records.mapping(RoleDTO::new)))
                    ).convertFrom(nullableMapping(UserDTO::new)),
                    multiset(
                        select(
                            PRODUCT.product_lang().PRODUCTID,
                            PRODUCT.product_lang().LANGID,
                            PRODUCT.product_lang().NAME,
                            PRODUCT.product_lang().DESCRIPTION,
                            row(
                                PRODUCT.product_lang().lang().LANGID,
                                PRODUCT.product_lang().lang().CODE,
                                PRODUCT.product_lang().lang().DESCRIPTION
                            ).convertFrom(Records.mapping(LangDTO::new))
                        ).from(PRODUCT.product_lang())
                    ).convertFrom(r -> r.map(Records.mapping(ProductLangDTO::new)))
                ).convertFrom(Records.mapping(ProductDTO::new))
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
