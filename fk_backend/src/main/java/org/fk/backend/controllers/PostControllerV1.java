package org.fk.backend.controllers;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.jooq.RequestContext;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.query.QueryParameters;
import org.fk.product.dto.PostDTO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.manager.PostManager;
import org.fk.product.manager.ProductManager;
import org.jooq.DSLContext;


@Path("/api/v1/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostControllerV1 {

    @Inject
    PostManager postManager;

    @Inject
    DSLFactory dslFactory;

    @GET
    @Operation(summary = "creates a new post")
    @APIResponse(responseCode = "201", description = "post creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create() throws ValidationException {
        DSLContext dsl = dslFactory.create(new RequestContext(1, 1));
        PostDTO created = postManager.create(dsl);
        return Response.ok(created).status(201).build();
    }
}
