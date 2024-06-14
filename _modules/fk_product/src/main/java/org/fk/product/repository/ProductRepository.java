package org.fk.product.repository;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.FkQueryJooqMapper;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop.tables.Lang.LANG;
import static org.jooq.Records.mapping;

import org.fk.core.repository.AbstractRepository;
import org.fk.core.query.model.FkQuery;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.RoleDTO;
import org.fk.product.dto.UserDTO;
import org.jooq.*;
import org.jooq.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, PRODUCT.PRODUCTID);
    }

    @Override
    protected SelectFinalStep<? extends Record> prepareQuery(FkQuery fkQuery) throws InvalidDataException {
        FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT.fields())
            .addMappableFields(PRODUCT_LANG.fields());

        // experiment: try to wrap the SELECT into a "container"-row,
        // to be able to use convertFrom on the ProductDTO for mapping everything beforehand.
        // After fetching, unwrap the "container"-row and extract the contents.
        // benefit: abstractions can work and expect the "container"-row, and type-safety still guaranteed.
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

                    // Nest a projection of USER fields (using implicit joins)
                    row(
                        PRODUCT.creator().USERID,
                        PRODUCT.creator().CLIENTID,
                        PRODUCT.creator().EMAIL,
                        PRODUCT.creator().FIRSTNAME,
                        PRODUCT.creator().LASTNAME,
                        multiset(
                            selectDistinct(
                                PRODUCT.creator().role().ROLEID
                            ).from(PRODUCT.creator().role())
                        ).convertFrom(r -> r.map(mapping(RoleDTO::create)))
                    ).convertFrom(r -> (r.get(PRODUCT.creator().USERID) == null) ? null : mapping(UserDTO::create).apply(r)),

                    multiset(
                        selectDistinct(
                            PRODUCT_LANG.PRODUCTID,
                            PRODUCT_LANG.LANGID,
                            PRODUCT_LANG.NAME,
                            PRODUCT_LANG.DESCRIPTION

                        )
                            .from(PRODUCT_LANG)
                            .where(PRODUCT_LANG.PRODUCTID.eq(PRODUCT.PRODUCTID))
                    ).convertFrom(r -> r.map(mapping(ProductLangDTO::create)))
                ).convertFrom(mapping(ProductDTO::create))
            )
            .from(PRODUCT
                .leftJoin(PRODUCT_LANG)
                .on(PRODUCT_LANG.PRODUCTID
                    .eq(PRODUCT.PRODUCTID))
                .leftJoin(LANG)
                .on(LANG.LANGID
                    .eq(PRODUCT_LANG.LANGID))
                .leftJoin(USER)
                .on(USER.USERID.eq(PRODUCT.CREATORID)))
            .where(queryJooqMapper.getFilters())
            .and(PRODUCT.CLIENTID.eq(request().getClientId()))
            .groupBy(PRODUCT.PRODUCTID)
            .orderBy(queryJooqMapper.getSorter())
            .offset(queryJooqMapper.getOffset())
            .limit(queryJooqMapper.getLimit());
    }
}
