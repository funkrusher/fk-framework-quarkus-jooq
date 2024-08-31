package org.fk.product.controller;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.exception.ValidationException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.core.request.RequestContext;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.manager.ProductManager;
import org.fk.core.query.model.FkQuery;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


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
    public String query(@BeanParam FkQuery fkQuery) throws InvalidDataException {
        return productManager.query(new RequestContext(securityIdentity, 1), fkQuery);
    }

    @GET
    @Authenticated
    @Operation(summary = "returns the product with the specified id")
    @APIResponse(responseCode = "200", description = "Getting the product with the specified id successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public String getOne(Long productId) throws NotFoundException {
        return productManager.getOne(new RequestContext(securityIdentity, 1), productId).orElseThrow(NotFoundException::new);
    }


    @POST
    @Authenticated
    @Operation(summary = "creates a new product")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create(ProductDTO product) throws ValidationException {
        String created = productManager.create(new RequestContext(securityIdentity, 1), product);
        return Response.ok(created).status(201).build();
    }

    @PUT
    @Authenticated
    @Operation(summary = "updates an existing product")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public String update(ProductDTO product) throws ValidationException {
        return productManager.update(new RequestContext(securityIdentity, 1), product);
    }

    @DELETE
    @Authenticated
    @Operation(summary = "deletes an existing product")
    @APIResponse(responseCode = "204", description = "product delete successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    public Response delete(ProductDTO product) {
        productManager.delete(new RequestContext(securityIdentity, 1), product);
        return Response.status(204).build();
    }
}
