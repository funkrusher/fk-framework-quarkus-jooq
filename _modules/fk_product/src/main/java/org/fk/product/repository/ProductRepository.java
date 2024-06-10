package org.fk.product.repository;

import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.database1.testshop.tables.Lang;
import org.fk.database1.testshop.tables.User;
import org.fk.database1.testshop.tables.UserRole;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop.tables.records.RoleRecord;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.UserDTO;
import org.fk.product.dto.UserRoleDTO;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.jsonObject;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, Product.PRODUCT.PRODUCTID);
    }

    @Override
    protected ProductDTO mapResult(Record rec) {
        ProductDTO product = rec.into(ProductDTO.class);
        for (ProductLangDTO productLang : product.getLangs()) {
            if (productLang.getLangId().equals(request().getLangId())) {
                product.setLang(productLang);
            }
        }
        return product;
    }

    @Override
    public SelectFinalStep<? extends Record> prepareQuery(FkQuery query) throws InvalidDataException {
        final FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(query, Product.PRODUCT)
                .addMappableFields(Product.PRODUCT.fields())
                .addMappableFields(ProductLang.PRODUCT_LANG.fields());

        Timestamp ts = Timestamp.from(Instant.now().minus(20, ChronoUnit.MINUTES));


        return dsl()
                .select(
                        Product.PRODUCT.PRODUCTID,
                        Product.PRODUCT.CLIENTID,
                        Product.PRODUCT.PRICE,
                        Product.PRODUCT.TYPEID,
                        Product.PRODUCT.CREATEDAT,
                        Product.PRODUCT.UPDATEDAT,
                        Product.PRODUCT.DELETED,
                        Product.PRODUCT.CREATORID,

                        // Nest a projection of USER fields (using implicit joins)
                        row(
                                Product.PRODUCT.fk_product_creatorId().USERID,
                                Product.PRODUCT.fk_product_creatorId().CLIENTID,
                                Product.PRODUCT.fk_product_creatorId().EMAIL,
                                Product.PRODUCT.fk_product_creatorId().FIRSTNAME,
                                Product.PRODUCT.fk_product_creatorId().LASTNAME
                        ).mapping(UserRecord::new),

                        multiset(
                                select(
                                        Product.PRODUCT.fk_product_creatorId().fk_user_role_roleId().ROLEID
                                )
                        .from(Product.PRODUCT.fk_product_creatorId().fk_user_role_roleId())
                        ).convertFrom(r -> r.map(Records.mapping(RoleRecord::new))),

                        multiset(
                                selectDistinct(
                                        asterisk(),
                                        jsonObject(asJsonEntries(Lang.LANG.fields())).as("lang")
                                )
                                        .from(ProductLang.PRODUCT_LANG)
                                        .join(Lang.LANG).on(Lang.LANG.LANGID.eq(ProductLang.PRODUCT_LANG.LANGID))
                                        .where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(Product.PRODUCT.PRODUCTID))
                        ).as("langs"))
                .from(Product.PRODUCT)
                .leftJoin(ProductLang.PRODUCT_LANG)
                .on(ProductLang.PRODUCT_LANG.PRODUCTID
                        .eq(Product.PRODUCT.PRODUCTID))
                .leftJoin(Lang.LANG)
                .on(Lang.LANG.LANGID
                        .eq(ProductLang.PRODUCT_LANG.LANGID))
                .leftJoin(User.USER)
                .on(User.USER.USERID.eq(Product.PRODUCT.CREATORID))
                .where(queryJooqMapper.getFilters())
                .and(Product.PRODUCT.CLIENTID.eq(request().getClientId()))
                .groupBy(Product.PRODUCT.PRODUCTID)
                .orderBy(queryJooqMapper.getSorter())
                .offset(queryJooqMapper.getOffset())
                .limit(queryJooqMapper.getLimit());
    }
}
