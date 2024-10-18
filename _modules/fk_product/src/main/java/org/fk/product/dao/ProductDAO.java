package org.fk.product.dao;

import org.fk.core.exception.MappingException;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.core.dao.AbstractDAO;
import org.fk.product.dto.CreateProductRequest;
import org.fk.product.dto.CreateProductResponse;
import org.fk.product.dto.UpdateProductRequest;
import org.fk.product.dto.UpdateProductResponse;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.row;

/**
 * ProductRecordDAO
 */
public class ProductDAO extends AbstractDAO<ProductRecord, Long> {

    public ProductDAO(DSLContext dsl) {
        super(dsl, PRODUCT);
    }

    public CreateProductResponse create(CreateProductRequest createProductRequest) {

        // project request to jooq-query.
        Long productId = dsl()
            .insertInto(PRODUCT)
            .set(PRODUCT.CLIENTID, createProductRequest.clientId())
            .set(PRODUCT.PRICE, createProductRequest.price())
            .set(PRODUCT.TYPEID, createProductRequest.typeId())
            .set(PRODUCT.DELETED, false)
            .returning(PRODUCT.PRODUCTID)
            .fetchOne(PRODUCT.PRODUCTID);

        // get db-content for id, and project to response...
        Record1<CreateProductResponse> result = dsl()
            .select(
                row(
                    PRODUCT.PRODUCTID,
                    PRODUCT.CLIENTID,
                    PRODUCT.PRICE,
                    PRODUCT.TYPEID,
                    PRODUCT.CREATEDAT,
                    PRODUCT.UPDATEDAT,
                    PRODUCT.DELETED,
                    PRODUCT.CREATORID
                ).convertFrom(x -> x.into(CreateProductResponse.class))
            )
            .from(PRODUCT)
            .where(PRODUCT.PRODUCTID.eq(productId))
            .fetchOne();

        if (result != null) {
            return result.value1();
        } else {
            throw new MappingException("internal error");
        }
    }
}
