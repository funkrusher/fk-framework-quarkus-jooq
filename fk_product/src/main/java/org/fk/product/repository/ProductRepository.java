package org.fk.product.repository;


import jakarta.enterprise.context.ApplicationScoped;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.QueryParameters;
import org.fk.core.jooq.RequestContext;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@ApplicationScoped
public class ProductRepository extends AbstractRepository {

    public ProductRepository(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public List<Field<?>> getViewFields() {
        List<Field<?>> fields = new ArrayList<>();
        fields.addAll(List.of(Product.PRODUCT.fields()));
        fields.addAll(List.of(ProductLang.PRODUCT_LANG.fields()));
        return fields;
    }

    @Override
    public TableOnConditionStep<Record> getViewJoins() {
        return Product.PRODUCT
                .leftJoin(ProductLang.PRODUCT_LANG)
                .on(ProductLang.PRODUCT_LANG.PRODUCTID
                        .eq(Product.PRODUCT.PRODUCTID));
    }

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

    public Optional<ProductDTO> fetchOne(Long productId) {
        List<ProductDTO> result = fetch(List.of(productId));
        if (result == null || result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.getFirst());
        }
    }

    public SelectSeekStepN<Record1<Long>> getQueryParamQuery(QueryParameters queryParameters) throws InvalidDataException {
        return dsl()
                .select(Product.PRODUCT.PRODUCTID)
                .from(getViewJoins())
                .where(DSL.and(getFilters(queryParameters, Product.PRODUCT)))
                .groupBy(Product.PRODUCT.PRODUCTID)
                .orderBy(getSorters(queryParameters, Product.PRODUCT));
    }


    public ProductPaginateDTO paginate(QueryParameters queryParameters) throws InvalidDataException {
        List<Long> productIds = getQueryParamQuery(queryParameters)
                .offset(queryParameters.getPage())
                .limit(queryParameters.getPageSize())
                .fetch(Product.PRODUCT.PRODUCTID);

        int count = dsl().fetchCount(getQueryParamQuery(queryParameters));

        List<ProductDTO> products = fetch(productIds);

        ProductPaginateDTO paginate = new ProductPaginateDTO();
        paginate.setProducts(products);
        paginate.setCount(count);
        return paginate;
    }


}
