package org.fk.backend.controllers;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.core.dto.ProductDTO;
import org.fk.core.manager.ProductManager;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.query.QueryParameters;
import org.fk.core.util.request.RequestContext;

import java.util.List;


@Path("/api/v1/products/examples")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductExamplesControllerV1 {

    @Inject
    ProductManager productService;

    @GET
    @Operation(summary = "returns a list of all products unauthenticated")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public List<ProductDTO> query(@BeanParam QueryParameters queryParameters) throws InvalidDataException {
        RequestContext requestContext = new RequestContext(1, 1);
        return productService.query(requestContext, queryParameters);
    }

    @POST
    @Operation(summary = "creates a new product unauthenticatd")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create(ProductDTO product) throws ValidationException {
        RequestContext requestContext = new RequestContext(1, 1);
        ProductDTO created = productService.create(requestContext, product);
        return Response.ok(created).status(201).build();
    }

    @PUT
    @Operation(summary = "updates an existing product unauthenticated")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO update(ProductDTO product) throws ValidationException {
        RequestContext requestContext = new RequestContext(1, 1);
        return productService.update(requestContext, product);
    }

    @GET
    @Operation(summary = "executes multiple transactions and make sure commit/rollback is correctly done and in parallel")
    @APIResponse(responseCode = "200", description = "Multi-Transaction Test executed")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/testMultiTransaction")
    public Response testMultiTransaction() {
        RequestContext requestContext = new RequestContext(1, 1);
        productService.testMultiTransaction(requestContext);
        return Response.status(200).build();
    }


    @GET
    @Operation(summary = "test multiset")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/multiset")
    public List<ProductDTO> testMultiset() {
        RequestContext requestContext = new RequestContext(1, 1);
        return productService.testMultiset(requestContext);
    }


}
