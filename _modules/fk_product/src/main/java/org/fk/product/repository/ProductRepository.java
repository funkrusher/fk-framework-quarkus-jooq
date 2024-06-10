package org.fk.product.repository;

import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.database1.testshop.tables.Lang;
import org.fk.database1.testshop.tables.User;
import org.fk.database1.testshop.tables.UserRole;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop.tables.records.RoleRecord;
import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.User.USER;
import static org.fk.database1.testshop.tables.Role.ROLE;
import static org.fk.database1.testshop.tables.UserRole.USER_ROLE;
import static org.fk.database1.testshop.tables.Lang.LANG;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.RoleDTO;
import org.fk.product.dto.UserDTO;
import org.fk.product.dto.LangDTO;
import org.fk.product.dto.UserRoleDTO;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.jsonObject;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, PRODUCT.PRODUCTID);}

    protected ProductDTO mapResult(Record rec) {
        ProductDTO product = rec.into(ProductDTO.class);
        for (ProductLangDTO productLang : product.getLangs()) {
            if (productLang.getLangId().equals(request().getLangId())) {
                product.setLang(productLang);
            }
        }
        return product;
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

    @Override
    public Stream<ProductDTO> stream(FkQuery fkQuery) {
        FkQueryJooqMapper queryJooqMapper = getQueryJooqMapper(fkQuery);
        return dsl()
                .select(
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
                                PRODUCT.fk_product_creatorId().USERID,
                                PRODUCT.fk_product_creatorId().CLIENTID,
                                PRODUCT.fk_product_creatorId().EMAIL,
                                PRODUCT.fk_product_creatorId().FIRSTNAME,
                                PRODUCT.fk_product_creatorId().LASTNAME,
                                multiset(
                                        selectDistinct(
                                                ROLE.ROLEID
                                        )
                                                .from(USER_ROLE)
                                                .join(ROLE).on(ROLE.ROLEID.eq(USER_ROLE.ROLEID))
                                                .where(ROLE.ROLEID.eq(USER_ROLE.ROLEID))
                                ).convertFrom(r -> r.map(Records.mapping(RoleDTO::new)))
                        ).mapping(UserDTO::new).convertFrom(user -> {
                            if (user.getUserId() == null) {
                                return null;
                            }
                            return user;
                        }),

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
                .map(Records.mapping(ProductDTO::new));
    }
}
