package org.fk.rest.exports.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.fk.auth.FkSecurityIdentity;
import org.fk.dtos.ProductDTO;
import org.fk.transfer.TransferJsonMapper;
import org.fk.services.ProductService;
import org.fk.util.request.RequestContext;

import java.util.*;


@Path("/api/v1/exports/json/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductJsonExportResourceV1 {

    @Inject
    FkSecurityIdentity fkSecurityIdentity;

    @Inject
    ProductService productService;

    @Inject
    @TransferJsonMapper
    ObjectMapper objectMapper;

    @GET
    @Authenticated
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootJsonFile() {
        RequestContext requestContext = new RequestContext(fkSecurityIdentity, 1);
        var productStream = productService.streamAll(requestContext);

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
