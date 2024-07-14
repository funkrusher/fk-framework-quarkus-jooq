package org.fk.product.controller.exports.json;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.transfer.json.JsonWriter;
import org.fk.database1.Database1;
import org.fk.product.dto.ProductDTO;
import org.fk.product.manager.ProductManager;
import org.fk.core.request.RequestContext;
import org.jooq.DSLContext;

import java.util.*;


@Path("/api/v1/exports/json/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductJsonExportControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    Database1 database1;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootJsonFile() throws InvalidDataException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        var productStream = productManager.streamAll(dsl);

        StreamingOutput streamingOutput = os -> {
            try (JsonWriter<ProductDTO> jsonWriter = new JsonWriter<>(os, ProductDTO.class)) {
                final Iterator<ProductDTO> it = productStream.iterator();
                while (it.hasNext()) {
                    ProductDTO product = it.next();
                    jsonWriter.writeItem(product);
                }
            }
        };
        return Response
                .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=product_export.json")
                .build();
    }
}
