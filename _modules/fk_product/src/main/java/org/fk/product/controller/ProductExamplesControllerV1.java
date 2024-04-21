package org.fk.product.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.core.jooq.RequestContext;
import org.fk.database1.Database1;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.manager.ProductManager;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.exception.ValidationException;
import org.fk.core.query.QueryParameters;
import org.jooq.DSLContext;

import java.util.List;


@Path("/api/v1/products/examples")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductExamplesControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    Database1 database1;


    @GET
    @Operation(summary = "returns a list of all products unauthenticated")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public ProductPaginateDTO query(@BeanParam QueryParameters queryParameters) throws InvalidDataException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        return productManager.query(dsl, queryParameters);
    }

    @POST
    @Operation(summary = "creates a new product unauthenticatd")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create(ProductDTO product) throws ValidationException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        ProductDTO created = productManager.create(dsl, product);
        return Response.ok(created).status(201).build();
    }

    @PUT
    @Operation(summary = "updates an existing product unauthenticated")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO update(ProductDTO product) throws ValidationException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        return productManager.update(dsl, product);
    }

    @GET
    @Operation(summary = "executes multiple transactions and make sure commit/rollback is correctly done and in parallel")
    @APIResponse(responseCode = "200", description = "Multi-Transaction Test executed")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/testMultiTransaction")
    public Response testMultiTransaction() {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        productManager.testMultiTransaction(dsl);
        return Response.status(200).build();
    }


    @GET
    @Operation(summary = "test multiset")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/multiset")
    public List<ProductDTO> testMultiset() {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        return productManager.testMultiset(dsl);
    }
}
