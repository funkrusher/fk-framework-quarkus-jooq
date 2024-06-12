package org.fk.product.repository;

import org.fk.core.query.jooq.FkQueryJooqMapper;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop.tables.Role.ROLE;
import static org.fk.database1.testshop.tables.UserRole.USER_ROLE;
import static org.fk.database1.testshop.tables.Lang.LANG;

import org.fk.core.repository.AbstractRepository;
import org.fk.core.query.model.FkQuery;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.RoleDTO;
import org.fk.product.dto.UserDTO;
import org.fk.product.dto.LangDTO;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, PRODUCT.PRODUCTID);
    }

    private TableLike<?> getJoins() {
        return PRODUCT
            .leftJoin(PRODUCT_LANG)
            .on(PRODUCT_LANG.PRODUCTID
                .eq(PRODUCT.PRODUCTID))
            .leftJoin(LANG)
            .on(LANG.LANGID
                .eq(PRODUCT_LANG.LANGID))
            .leftJoin(USER)
            .on(USER.USERID.eq(PRODUCT.CREATORID));
    }

    private FkQueryJooqMapper getQueryJooqMapper(FkQuery fkQuery) {
        return new FkQueryJooqMapper(fkQuery, PRODUCT)
            .addMappableFields(PRODUCT.fields())
            .addMappableFields(ProductLang.PRODUCT_LANG.fields());
    }


    @Override
    public int count(FkQuery fkQuery) {
        FkQueryJooqMapper queryJooqMapper = getQueryJooqMapper(fkQuery);
        return dsl().fetchCount(
            dsl().select()
                .from(getJoins())
                .where(queryJooqMapper.getFilters())
                .and(PRODUCT.CLIENTID.eq(request().getClientId()))
                .groupBy(PRODUCT.PRODUCTID)
        );
    }

    public static <R extends org.jooq.Record, T> T nullableMapping(R record, Function<R, T> mapper) {
        // TODO: is this the right way to process nullable 1:1 relationships?
        if (record == null || record.get(0) == null) {
            return null;
        }
        return mapper.apply(record);
    }

    @Override
    public Stream<ProductDTO> stream(FkQuery fkQuery) {
        FkQueryJooqMapper queryJooqMapper = getQueryJooqMapper(fkQuery);

        // experiment: try to wrap the SELECT into a "container"-row,
        // to be able to use convertFrom on the ProductDTO for mapping everything beforehand.
        // After fetching, unwrap the "container"-row and extract the contents.
        // benefit: abstractions can work and expect the "container"-row, and type-safety still guaranteed.
        return dsl()
            .select(
                row(
                    PRODUCT.PRODUCTID,
                    DSL.val((Integer) null).as(PRODUCT.CLIENTID), // testing the ignore of a field.
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
                                ROLE.ROLEID
                            )
                                .from(USER_ROLE)
                                .join(ROLE).on(ROLE.ROLEID.eq(USER_ROLE.ROLEID))
                                .where(ROLE.ROLEID.eq(USER_ROLE.ROLEID))
                        ).convertFrom(r -> r.map(Records.mapping(RoleDTO::new)))
                    ).convertFrom(r -> nullableMapping(r, Records.mapping(UserDTO::new))),

                    multiset(
                        selectDistinct(
                            PRODUCT_LANG.PRODUCTID,
                            PRODUCT_LANG.LANGID,
                            PRODUCT_LANG.NAME,
                            PRODUCT_LANG.DESCRIPTION,
                            row(
                                LANG.LANGID,
                                LANG.CODE,
                                LANG.DESCRIPTION
                            ).mapping(LangDTO::new)

                        )
                            .from(PRODUCT_LANG)
                            .join(LANG).on(LANG.LANGID.eq(PRODUCT_LANG.LANGID))
                            .where(PRODUCT_LANG.PRODUCTID.eq(PRODUCT.PRODUCTID))
                    ).convertFrom(r -> r.map(Records.mapping(ProductLangDTO::new)))
                ).convertFrom(Records.mapping(ProductDTO::new)).as("container")
            )
            .from(getJoins())
            .where(queryJooqMapper.getFilters())
            .and(PRODUCT.CLIENTID.eq(request().getClientId()))
            .groupBy(PRODUCT.PRODUCTID)
            .orderBy(queryJooqMapper.getSorter())
            .offset(queryJooqMapper.getOffset())
            .limit(queryJooqMapper.getLimit())
            .fetchSize(250)
            .fetchStream()
            .map(x -> (ProductDTO) x.get("container"));
    }
}
