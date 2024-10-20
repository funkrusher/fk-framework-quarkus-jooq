package org.fk.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.fk.framework.exception.InvalidDataException;
import org.fk.framework.exception.ValidationException;
import org.fk.framework.query.model.FkQuery;
import org.fk.framework.request.RequestContext;
import org.fk.product.dto.*;
import org.fk.product.manager.ProductManager;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;
import java.util.Map;


@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product Controller V1", description = "Product Operations, for testing many different use-cases")
public class ProductControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    ObjectMapper jsonMapper;

    @GET
    @Operation(summary = "returns the product with the specified id")
    @APIResponse(responseCode = "200", description = "Getting the product with the specified id successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public ProductResponse getOneNested(Long productId) throws NotFoundException {
        return productManager.getOneNested(new RequestContext(1, 1), productId).orElseThrow(NotFoundException::new);
    }

    @GET
    @Operation(summary = "returns a list of all products")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public QueryProductResponse queryNested(
        @BeanParam QueryProductRequest queryProductRequest) throws InvalidDataException {
        final FkQuery fkQuery = new FkQuery();
        return productManager.queryNested(new RequestContext(1, 1), fkQuery);
    }

    @POST
    @Operation(summary = "creates a new product")
    @APIResponse(responseCode = "201", description = "product creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    @RequestBody(content = @Content(examples = {
        @ExampleObject(
            name = CreateProductRequest.EXAMPLE1_NAME,
            description = CreateProductRequest.EXAMPLE1_DESCRIPTION,
            value = CreateProductRequest.EXAMPLE1_VALUE
        )
    }))
    @APIResponse(content = @Content(examples = {
        @ExampleObject(
            name = CreateProductResponse.EXAMPLE1_NAME,
            description = CreateProductResponse.EXAMPLE1_DESCRIPTION,
            value = CreateProductResponse.EXAMPLE1_VALUE
        )
    }))
    @ResponseStatus(201)
    public CreateProductResponse create(CreateProductRequest createProductRequest) throws ValidationException {
        return productManager.create(new RequestContext(1, 1), createProductRequest);
    }

    @PUT
    @Operation(summary = "updates an existing product")
    @APIResponse(responseCode = "200", description = "product update successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public UpdateProductResponse update(UpdateProductRequest updateProductRequest) throws ValidationException {
        return productManager.update(new RequestContext(1, 1), updateProductRequest);
    }

    @PATCH
    @Operation(summary = "patch an existing product")
    @RequestBody(
        required = true,
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = PatchProductRequest.class)))
    @APIResponse(responseCode = "200", description = "product patch successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/{productId}")
    public void patch(Map<String, Object> patch) throws ValidationException {
        productManager.patch(new RequestContext(1, 1), patch);
    }

    @DELETE
    @Operation(summary = "deletes an existing product")
    @APIResponse(responseCode = "204", description = "product delete successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @ResponseStatus(204)
    @Path("/{productId}")
    public Response delete(Long productId) {
        productManager.delete(new RequestContext(1, 1), productId);
        return Response.status(204).build();
    }

    @GET
    @Operation(summary = "executes multiple transactions and make sure commit/rollback is correctly done and in parallel")
    @APIResponse(responseCode = "200", description = "Multi-Transaction Test executed")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/testMultiTransaction")
    public Response testMultiTransaction() {
        productManager.testMultiTransaction(new RequestContext(1, 1));
        return Response.status(200).build();
    }

    @GET
    @Operation(summary = "test multiset")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/multiset")
    public List<ProductResponse> testMultiset() {
        return productManager.testMultiset(new RequestContext(1, 1));
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/export/json")
    public Response streamJson() throws InvalidDataException {
        final StreamingOutput streamingOutput =
            os -> productManager.exportJson(new RequestContext(1, 1), os);
        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.json")
            .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/export/csv")
    public Response streamCsv() throws InvalidDataException {
        final StreamingOutput streamingOutput =
            os -> productManager.exportCsv(new RequestContext(1, 1), os);
        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.csv")
            .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/export/xlsx")
    public Response streamXlsx() throws InvalidDataException {
        final StreamingOutput streamingOutput =
            os -> productManager.exportXlsx(new RequestContext(1, 1), os);
        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.xlsx")
            .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/export/pdf")
    public Response streamPdf(
        @DefaultValue("de") @QueryParam("language") String language,
        @DefaultValue("250") @QueryParam("chunkSize") Integer chunkSize
    ) throws InvalidDataException {
        final StreamingOutput streamingOutput =
            os -> productManager.exportPdf(new RequestContext(1, 1), os, language, chunkSize);
        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.pdf")
            .build();
    }
}
