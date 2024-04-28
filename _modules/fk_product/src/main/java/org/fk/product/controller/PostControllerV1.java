package org.fk.product.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.fk.core.request.RequestContext;
import org.fk.core.exception.ValidationException;
import org.fk.product.dto.PostDTO;
import org.fk.product.manager.PostManager;
import org.jooq.DSLContext;
import org.fk.database1.Database1;

@Path("/api/v1/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="Post Controller V1", description = "Post Operations, only available for logged-in users")
public class PostControllerV1 {

    @Inject
    PostManager postManager;

    @Inject
    Database1 database1;

    @GET
    @Operation(summary = "creates a new post")
    @APIResponse(responseCode = "201", description = "post creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create() throws ValidationException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        PostDTO created = postManager.create(dsl);
        return Response.ok(created).status(201).build();
    }
}
