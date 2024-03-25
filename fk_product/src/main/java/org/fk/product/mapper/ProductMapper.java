package org.fk.product.mapper;


import jakarta.enterprise.context.ApplicationScoped;
import org.fk.codegen.testshop.tables.Client;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.mapper.AbstractMapper;
import org.fk.core.mapper.RecordToViewMapper;
import org.fk.core.util.request.RequestContext;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductMapper extends AbstractMapper {

    public ProductMapper(RequestContext request) {
        super(request);
    }

    private final RecordToViewMapper<ProductDTO, ProductRecord, Long> productViewMapper = new RecordToViewMapper<>(
            ProductDTO.class,
            ProductRecord.class,
            Product.PRODUCT.PRODUCTID,
            List.of(Product.PRODUCT.PRODUCTID));

    private final RecordToViewMapper<ProductLangDTO, ProductLangRecord, Long> productLangViewMapper = new RecordToViewMapper<>(
            ProductLangDTO.class,
            ProductLangRecord.class,
            ProductLang.PRODUCT_LANG.PRODUCTID,
            List.of(ProductLang.PRODUCT_LANG.PRODUCTID, ProductLang.PRODUCT_LANG.LANGID));

    public List<ProductDTO> map(Result<Record> records) {
        List<ProductRecord> productRecords = records.into(ProductRecord.class);
        List<ProductLangRecord> xLangRecords = records.into(ProductLangRecord.class);
        return map(productRecords, xLangRecords);
    }

    public List<ProductDTO> map(List<ProductRecord> productRecords, List<ProductLangRecord> xLangRecords) {
        final List<ProductDTO> products = productViewMapper.extractRecords(productRecords);
        final Map<Long, List<ProductLangDTO>> langs = productLangViewMapper.extractRecordsGroupedBy(xLangRecords);
        for (ProductDTO product : products) {
            final List<ProductLangDTO> productLangs = langs.getOrDefault(product.getProductId(), new ArrayList<>());
            product.setLangs(productLangs);
            for (ProductLangDTO productLang : productLangs) {
                if (productLang.getLangId().equals(request().getLangId())) {
                    product.setLang(productLang);
                }
            }
        }
        return products;
    }
}
