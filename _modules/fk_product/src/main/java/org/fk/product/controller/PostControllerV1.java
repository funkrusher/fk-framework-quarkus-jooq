package org.fk.product.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.fk.core.request.RequestContext;
import org.fk.core.exception.ValidationException;
import org.fk.product.dto.CreatePostResponse;
import org.fk.product.manager.PostManager;
import org.jboss.resteasy.reactive.ResponseStatus;

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
    @ResponseStatus(201)
    public CreatePostResponse create() throws ValidationException {
        return postManager.create(new RequestContext(1, 1));
    }
}
