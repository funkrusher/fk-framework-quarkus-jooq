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
import org.fk.database1.testshop.tables.dtos.PostDto;
import org.fk.product.manager.PostManager;

@Path("/api/v1/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="Post Controller V1", description = "Post Operations, only available for logged-in users")
public class PostControllerV1 {

    @Inject
    PostManager postManager;

    @GET
    @Operation(summary = "creates a new post")
    @APIResponse(responseCode = "201", description = "post creation successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public Response create() throws ValidationException {
        PostDto created = postManager.create(new RequestContext(1, 1));
        return Response.ok(created).status(201).build();
    }
}
