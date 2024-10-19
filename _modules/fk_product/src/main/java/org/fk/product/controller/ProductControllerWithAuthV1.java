package org.fk.product.controller;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.fk.framework.auth.FkSecurityIdentity;
import org.fk.framework.exception.InvalidDataException;
import org.fk.framework.exception.ValidationException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.framework.request.RequestContext;
import org.fk.product.dto.*;
import org.fk.product.manager.ProductManager;
import org.fk.framework.query.model.FkQuery;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;


@Path("/api/v1/auth/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="Product Controller With Auth V1", description = "Product Operations, only available for logged-in users")
public class ProductControllerWithAuthV1 {

    @Inject
    ProductManager productManager;

    @Inject
    FkSecurityIdentity securityIdentity;

    @GET
    @Authenticated
    @Operation(summary = "returns a list of all products")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public QueryProductResponse queryNested(@BeanParam FkQuery fkQuery) throws InvalidDataException {
        return productManager.queryNested(new RequestContext(securityIdentity, 1), fkQuery);
    }

    @GET
    @Authenticated
    @Operation(summary = "returns the product with the specified id")
    @APIResponse(responseCode = "200", description = "Getting the product with the specified id successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO getOneNested(Long productId) throws NotFoundException {
        return productManager.getOneNested(new RequestContext(securityIdentity, 1), productId).orElseThrow(NotFoundException::new);
    }


    @POST
    @Authenticated
    @Operation(summary = "creates a new product")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    @ResponseStatus(201)
    public CreateProductResponse create(CreateProductRequest createProductRequest) throws ValidationException {
        return productManager.create(new RequestContext(securityIdentity, 1), createProductRequest);
    }

    @PUT
    @Authenticated
    @Operation(summary = "updates an existing product")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public UpdateProductResponse update(UpdateProductRequest updateProductRequest) throws ValidationException {
        return productManager.update(new RequestContext(securityIdentity, 1), updateProductRequest);
    }

    @DELETE
    @Authenticated
    @Operation(summary = "deletes an existing product")
    @APIResponse(responseCode = "204", description = "product delete successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public Response delete(Long productId) {
        productManager.delete(new RequestContext(securityIdentity, 1), productId);
        return Response.status(204).build();
    }
}
