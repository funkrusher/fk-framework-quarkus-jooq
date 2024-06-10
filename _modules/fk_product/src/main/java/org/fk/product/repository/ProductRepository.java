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
import org.fk.product.manager.ProductManager;
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

import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.jsonObject;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;


public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, Product.PRODUCT.PRODUCTID);
    }
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ProductRepository.class);


    @Override
    public SelectFinalStep<? extends Record> prepareQuery(FkQuery query) throws InvalidDataException {
        final FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(query, Product.PRODUCT)
                .addMappableFields(Product.PRODUCT.fields())
                .addMappableFields(ProductLang.PRODUCT_LANG.fields());

        Timestamp ts = Timestamp.from(Instant.now().minus(20, ChronoUnit.MINUTES));

        return null;
    }


    public record ProductItem(Long productId, BigDecimal price, UserItem creator) {
    }

    public record UserItem(Integer userId, String email) {
    }


    public List<ProductItem> query2() {

        SelectFinalStep test = dsl()
                .select(
                        PRODUCT.PRODUCTID,
                        PRODUCT.PRICE,
                        row(
                                PRODUCT.fk_product_creatorId().USERID,
                                PRODUCT.fk_product_creatorId().EMAIL
                        ).mapping(UserItem::new));
        return test.fetch(Records.mapping(ProductItem::new));
    }
}
