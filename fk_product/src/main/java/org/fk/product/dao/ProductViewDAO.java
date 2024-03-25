package org.fk.product.dao;

import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.dao.AbstractViewDAO;
import org.fk.product.dto.ProductDTO;
import org.jooq.*;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.Records.mapping;

/**
 * ProductViewDAO
 */
public class ProductViewDAO extends AbstractViewDAO<ProductRecord, ProductDTO, Long> {

    public ProductViewDAO(DSLContext dsl) {
        super(dsl, Product.PRODUCT);
    }

    @Override
    protected Long getId(ProductRecord object) {
        return object.getProductId();
    }

    @Override
    protected List<Field<?>> getViewFields() {
        List<Field<?>> fields = new ArrayList<>();
        fields.addAll(List.of(table().fields()));
        fields.addAll(List.of(ProductLang.PRODUCT_LANG.fields()));
        return fields;
    }

    @Override
    protected TableOnConditionStep<Record> getViewJoins() {
        return Product.PRODUCT
                .leftJoin(ProductLang.PRODUCT_LANG)
                .on(ProductLang.PRODUCT_LANG.PRODUCTID
                        .eq(Product.PRODUCT.PRODUCTID));
    }
}
