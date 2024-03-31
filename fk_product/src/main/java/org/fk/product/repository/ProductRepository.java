package org.fk.product.repository;


import jakarta.enterprise.context.ApplicationScoped;
import org.fk.database.testshop.tables.Product;
import org.fk.database.testshop.tables.ProductLang;
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
import java.util.stream.Stream;

import static org.fk.core.jooq.DSLFactory.REQUEST;
import static org.jooq.impl.DSL.*;

@ApplicationScoped
public class ProductRepository extends AbstractRepository<ProductDTO, Long> {

    public ProductRepository(DSLContext dsl) {
        super(dsl);
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
    public SelectSeekStepN<Record1<Long>> getQuery(QueryParameters queryParameters) throws InvalidDataException {
        final List<Field<?>> availableFields = new ArrayList<>();
        availableFields.addAll(List.of(Product.PRODUCT.fields()));
        availableFields.addAll(List.of(ProductLang.PRODUCT_LANG.fields()));

        return dsl()
                .select(Product.PRODUCT.PRODUCTID)
                .from(Product.PRODUCT
                        .leftJoin(ProductLang.PRODUCT_LANG)
                        .on(ProductLang.PRODUCT_LANG.PRODUCTID
                                .eq(Product.PRODUCT.PRODUCTID)))
                .where(DSL.and(getFilters(queryParameters, availableFields, Product.PRODUCT)))
                .and(Product.PRODUCT.CLIENTID.eq(request().getClientId()))
                .groupBy(Product.PRODUCT.PRODUCTID)
                .orderBy(getSorters(queryParameters, availableFields, Product.PRODUCT));
    }

    @Override
    public List<Long> paginate(QueryParameters queryParameters) throws InvalidDataException {
        return getQuery(queryParameters)
                .offset(queryParameters.getPage())
                .limit(queryParameters.getPageSize())
                .fetch(Product.PRODUCT.PRODUCTID);
    }

    @Override
    public int count(QueryParameters queryParameters) throws InvalidDataException {
        return dsl().fetchCount(getQuery(queryParameters));
    }

    public Stream<Long> stream(QueryParameters queryParameters) throws InvalidDataException {
        return getQuery(queryParameters)
                .fetchSize(250)
                .fetchStream()
                .map(Record1::value1);
    }
}
