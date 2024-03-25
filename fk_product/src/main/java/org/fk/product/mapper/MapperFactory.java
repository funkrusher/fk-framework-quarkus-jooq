package org.fk.product.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.fk.core.util.request.RequestContext;
import org.fk.product.dao.*;
import org.jooq.DSLContext;

/**
 * MapperFactory to create instances of request-scoped mappers.
 */
@ApplicationScoped
public class MapperFactory {
    public ProductMapper createProductMapper(RequestContext request) { return new ProductMapper(request); }
}
