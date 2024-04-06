package org.fk.library.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fk.core.jooq.RequestContext;
import org.fk.database2.Database2;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.query.QueryParameters;
import org.fk.library.manager.AuthorManager;
import org.jooq.DSLContext;
import org.fk.library.dto.AuthorPaginateDTO;

import java.util.List;


@Path("/api/v1/library/examples")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorExamplesControllerV1 {

    @Inject
    AuthorManager authorManager;
    @Inject
    Database2 database2;
    @GET
    @Operation(summary = "returns a list of all authors unauthenticated")
    @APIResponse(responseCode = "200", description = "List of all products successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Path("/")
    public AuthorPaginateDTO query(@BeanParam QueryParameters queryParameters) throws InvalidDataException {
        DSLContext dsl = database2.dsl(new RequestContext(1, 1));
        return authorManager.query(dsl, queryParameters);
    }
}
