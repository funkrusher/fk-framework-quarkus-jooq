package org.fk.backend.controllers;

import io.quarkus.security.Authenticated;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.exception.ValidationException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.product.dto.ProductDTO;
import org.fk.product.manager.ProductManager;
import org.fk.core.util.query.QueryParameters;
import org.fk.core.util.request.RequestContext;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductControllerV1 {

    @Inject
    ProductManager productService;

    @Inject
    FkSecurityIdentity fkSecurityIdentity;

    @GET
    @Authenticated
    @Operation(summary = "returns a list of all products")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public List<ProductDTO> query(@BeanParam QueryParameters queryParameters) throws InvalidDataException {
        RequestContext request = new RequestContext(fkSecurityIdentity, 1);
        return productService.query(request, queryParameters);
    }

    @GET
    @Authenticated
    @Operation(summary = "returns the product with the specified id")
    @APIResponse(responseCode = "200", description = "Getting the product with the specified id successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO getOne(Long productId) throws NotFoundException {
        RequestContext request = new RequestContext(fkSecurityIdentity, 1);
        return productService.getOne(request, productId).orElseThrow(NotFoundException::new);
    }


    @POST
    @Authenticated
    @Operation(summary = "creates a new product")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create(ProductDTO product) throws ValidationException {
        RequestContext request = new RequestContext(fkSecurityIdentity, 1);
        ProductDTO created = productService.create(request, product);
        return Response.ok(created).status(201).build();
    }

    @PUT
    @Authenticated
    @Operation(summary = "updates an existing product")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductDTO update(ProductDTO product) throws ValidationException {
        RequestContext request = new RequestContext(fkSecurityIdentity, 1);
        return productService.update(request, product);
    }

    @DELETE
    @Authenticated
    @Operation(summary = "deletes an existing product")
    @APIResponse(responseCode = "204", description = "product delete successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    public Response delete(ProductDTO product) {
        RequestContext request = new RequestContext(fkSecurityIdentity, 1);
        productService.delete(request, product);
        return Response.status(204).build();
    }
}
