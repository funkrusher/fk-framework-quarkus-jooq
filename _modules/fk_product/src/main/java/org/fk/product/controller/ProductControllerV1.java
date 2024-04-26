package org.fk.product.controller;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.exception.ValidationException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.database1.DSLContext1;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.manager.ProductManager;
import org.fk.core.query.QueryParameters;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jooq.DSLContext;


@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="Product Controller V1", description = "Product Operations, only available for logged-in users")
public class ProductControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    @DSLContext1
    DSLContext dsl;

    @GET
    @Authenticated
    @Operation(summary = "returns a list of all products")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public ProductPaginateDTO query(@BeanParam QueryParameters queryParameters) throws InvalidDataException {
        return productManager.query(dsl, queryParameters);
    }

    @GET
    @Authenticated
    @Operation(summary = "returns the product with the specified id")
    @APIResponse(responseCode = "200", description = "Getting the product with the specified id successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO getOne(Long productId) throws NotFoundException {
        return productManager.getOne(dsl, productId).orElseThrow(NotFoundException::new);
    }


    @POST
    @Authenticated
    @Operation(summary = "creates a new product")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create(ProductDTO product) throws ValidationException {
        ProductDTO created = productManager.create(dsl, product);
        return Response.ok(created).status(201).build();
    }

    @PUT
    @Authenticated
    @Operation(summary = "updates an existing product")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO update(ProductDTO product) throws ValidationException {
        return productManager.update(dsl, product);
    }

    @DELETE
    @Authenticated
    @Operation(summary = "deletes an existing product")
    @APIResponse(responseCode = "204", description = "product delete successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    public Response delete(ProductDTO product) {
        productManager.delete(dsl, product);
        return Response.status(204).build();
    }
}
