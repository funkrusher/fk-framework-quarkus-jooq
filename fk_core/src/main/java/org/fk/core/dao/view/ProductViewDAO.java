package org.fk.core.dao.view;

import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.core.dao.AbstractViewDAO;
import org.fk.core.dao.RecordToViewMapper;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.jooq.JooqContext;
import org.fk.core.dto.ProductDTO;
import org.fk.core.dto.ProductLangDTO;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.TableOnConditionStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.*;

/**
 * ProductViewDAO
 */
public class ProductViewDAO extends AbstractViewDAO<ProductRecord, ProductDTO, Long> {

    public ProductViewDAO(JooqContext jooqContext) {
        super(jooqContext, Product.PRODUCT);
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

    private final RecordToViewMapper<ProductDTO, ProductRecord, Long> productViewMapper = new RecordToViewMapper<>(
            ProductDTO.class,
            ProductRecord.class,
            Product.PRODUCT.PRODUCTID,
            List.of(Product.PRODUCT.PRODUCTID));

    private final RecordToViewMapper<ProductLangDTO, ProductLangRecord, Long> productLangViewMapper = new RecordToViewMapper<>(
            ProductLangDTO.class,
            ProductLangRecord.class,
            Product.PRODUCT.PRODUCTID,
            List.of(ProductLang.PRODUCT_LANG.PRODUCTID, ProductLang.PRODUCT_LANG.LANGID));

    @Override
    protected List<ProductDTO> recordsToView(List<Record> records) {
        final List<ProductDTO> products = productViewMapper.extractRecords(records);
        final Map<Long, List<ProductLangDTO>> langs = productLangViewMapper.extractRecordsGroupedBy(records);
        for (ProductDTO product : products) {
            final List<ProductLangDTO> productLangs = langs.getOrDefault(product.getProductId(), new ArrayList<>());
            product.setLangs(productLangs);
            for (ProductLangDTO productLang : productLangs) {
                if (productLang.getLangId().equals(requestContext().getLangId())) {
                    product.setLang(productLang);
                }
            }
        }
        return products;
    }
}
