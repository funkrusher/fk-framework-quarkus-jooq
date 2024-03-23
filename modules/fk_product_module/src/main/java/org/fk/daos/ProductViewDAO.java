package org.fk.daos;

import org.fk.dao.AbstractViewDAO;
import org.fk.dao.RecordToViewMapper;
import org.fk.dtos.ProductDTO;
import org.fk.dtos.ProductLangDTO;
import org.fk.generated.testshop.tables.Product;
import org.fk.generated.testshop.tables.ProductLang;
import org.fk.generated.testshop.tables.records.ProductRecord;
import org.fk.jooq.JooqContext;
import org.jooq.Record;
import org.jooq.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

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

    private final RecordToViewMapper<ProductDTO, Long> productViewMapper = new RecordToViewMapper<>(
            ProductDTO.class,
            Product.PRODUCT.PRODUCTID,
            List.of(Product.PRODUCT.PRODUCTID));

    private final RecordToViewMapper<ProductLangDTO, Long> productLangViewMapper = new RecordToViewMapper<>(
            ProductLangDTO.class,
            Product.PRODUCT.PRODUCTID,
            List.of(ProductLang.PRODUCT_LANG.PRODUCTID, ProductLang.PRODUCT_LANG.LANGID));

    @Override
    protected List<ProductDTO> recordsToView(List<Record> records) {
        final List<ProductDTO> products = productViewMapper.extractRecords(records);
        final Map<Long, List<ProductLangDTO>> langs = productLangViewMapper.extractRecordsGroupedBy(records);
        for (ProductDTO product : products) {
            final List<ProductLangDTO> productLangs = langs.get(product.getProductId());
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