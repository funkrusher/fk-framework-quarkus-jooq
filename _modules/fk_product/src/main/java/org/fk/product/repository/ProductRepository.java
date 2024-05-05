package org.fk.product.repository;


import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkQuery;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.*;

public class ProductRepository extends AbstractRepository<ProductDTO, Long> {

    private final FkQueryJooqMapper queryJooqMapper;

    public ProductRepository(DSLContext dsl) {
        super(dsl);

        final List<Field<?>> mappableFields = new ArrayList<>();
        mappableFields.addAll(List.of(Product.PRODUCT.fields()));
        mappableFields.addAll(List.of(ProductLang.PRODUCT_LANG.fields()));
        this.queryJooqMapper = new FkQueryJooqMapper(Product.PRODUCT, mappableFields);
    }

    @Override
    public List<ProductDTO> fetch(List<Long> productIds) {
        // use multiset to let the database and jooq do all the work of joining the tables and mapping to dto.
        List<ProductDTO> products = dsl().select(
                        asterisk(),
                        multiset(
                                selectDistinct(asterisk())
                                        .from(ProductLang.PRODUCT_LANG)
                                        .where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(Product.PRODUCT.PRODUCTID))
                        ).as("langs")
                ).from(Product.PRODUCT)
                .where(Product.PRODUCT.PRODUCTID.in(productIds))
                .and(Product.PRODUCT.CLIENTID.eq(request().getClientId()))
                .fetchInto(ProductDTO.class);

        // append and change some data that is still missing after our fetch.
        for (ProductDTO product : products) {
            for (ProductLangDTO productLang : product.getLangs()) {
                if (productLang.getLangId().equals(request().getLangId())) {
                    product.setLang(productLang);
                }
            }
        }
        return products;
    }

    @Override
    public SelectSeekStepN<Record1<Long>> mapQuery(FkQuery query) throws InvalidDataException {
        return dsl()
                .select(Product.PRODUCT.PRODUCTID)
                .from(Product.PRODUCT
                        .leftJoin(ProductLang.PRODUCT_LANG)
                        .on(ProductLang.PRODUCT_LANG.PRODUCTID
                                .eq(Product.PRODUCT.PRODUCTID)))
                .where(DSL.and(queryJooqMapper.getFilters(query)))
                .and(Product.PRODUCT.CLIENTID.eq(request().getClientId()))
                .groupBy(Product.PRODUCT.PRODUCTID)
                .orderBy(queryJooqMapper.getSorter(query));
    }

    @Override
    public List<Long> paginateQuery(FkQuery query) throws InvalidDataException {
        return mapQuery(query)
                .offset(query.getPage())
                .limit(query.getPageSize())
                .fetch(Product.PRODUCT.PRODUCTID);
    }

    @Override
    public int countQuery(FkQuery query) throws InvalidDataException {
        return dsl().fetchCount(mapQuery(query));
    }

    public Stream<Long> streamQuery(FkQuery query) throws InvalidDataException {
        return mapQuery(query)
                .fetchSize(250)
                .fetchStream()
                .map(Record1::value1);
    }
}
