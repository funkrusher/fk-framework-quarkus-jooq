package org.fk.backend.controllers.exports.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.fk.core.jooq.DSLFactory;
import org.fk.product.dto.ProductDTO;
import org.fk.core.transfer.TransferJsonMapper;
import org.fk.product.manager.ProductManager;
import org.fk.core.jooq.RequestContext;
import org.jooq.DSLContext;

import java.util.*;


@Path("/api/v1/exports/json/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductJsonExportControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    @TransferJsonMapper
    ObjectMapper objectMapper;

    @Inject
    DSLFactory dslFactory;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootJsonFile() {
        DSLContext dsl = dslFactory.create(new RequestContext(1, 1));
        var productStream = productManager.streamAll(dsl);

        StreamingOutput streamingOutput = outputStream -> {
            Iterator<ProductDTO> it = productStream.iterator();

            SequenceWriter sequenceWriter = objectMapper.writerFor(ProductDTO.class).writeValues(outputStream);
            while (it.hasNext()) {
                ProductDTO product = it.next();
                sequenceWriter.write(product);
            }
        };

        return Response
                .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=product_export.json")
                .build();
    }
}
