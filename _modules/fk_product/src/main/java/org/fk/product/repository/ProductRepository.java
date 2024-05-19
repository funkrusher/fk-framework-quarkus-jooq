package org.fk.product.repository;

import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.database1.testshop.tables.Lang;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.jooq.*;
import org.jooq.Record;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.jsonObject;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {
    public ProductRepository(DSLContext dsl) {
        super(dsl, ProductDTO.class, Product.PRODUCT.PRODUCTID);
    }

    @Override
    protected ProductDTO mapResult(ProductDTO dto) {
        for (ProductLangDTO productLang : dto.getLangs()) {
            if (productLang.getLangId().equals(request().getLangId())) {
                dto.setLang(productLang);
            }
        }
        return dto;
    }

    @Override
    public SelectFinalStep<Record> prepareQuery(FkQuery query) throws InvalidDataException {
        final FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(query, Product.PRODUCT)
                .addMappableFields(Product.PRODUCT.fields())
                .addMappableFields(ProductLang.PRODUCT_LANG.fields());

        return dsl()
                .select(
                        asterisk(),
                        multiset(
                                selectDistinct(
                                        asterisk(),
                                        jsonObject(asJsonEntries(Lang.LANG.fields())).as("lang")
                                )
                                        .from(ProductLang.PRODUCT_LANG)
                                        .join(Lang.LANG).on(Lang.LANG.LANGID.eq(ProductLang.PRODUCT_LANG.LANGID))
                                        .where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(Product.PRODUCT.PRODUCTID))
                        ).as("langs"))
                .from(Product.PRODUCT
                        .leftJoin(ProductLang.PRODUCT_LANG)
                        .on(ProductLang.PRODUCT_LANG.PRODUCTID
                                .eq(Product.PRODUCT.PRODUCTID))
                        .leftJoin(Lang.LANG)
                        .on(Lang.LANG.LANGID
                                .eq(ProductLang.PRODUCT_LANG.LANGID)))
                .where(queryJooqMapper.getFilters())
                .and(Product.PRODUCT.CLIENTID.eq(request().getClientId()))
                .groupBy(Product.PRODUCT.PRODUCTID)
                .orderBy(queryJooqMapper.getSorter())
                .offset(queryJooqMapper.getOffset())
                .limit(queryJooqMapper.getLimit());
    }
}
