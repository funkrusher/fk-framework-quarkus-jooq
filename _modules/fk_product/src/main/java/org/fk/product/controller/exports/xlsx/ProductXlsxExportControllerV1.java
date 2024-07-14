package org.fk.product.controller.exports.xlsx;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.request.RequestContext;
import org.fk.product.manager.ProductExportManager;

@Path("/api/v1/exports/xlsx/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductXlsxExportControllerV1 {

    @Inject
    ProductExportManager productExportManager;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootXlsxFile() throws InvalidDataException {
        final StreamingOutput streamingOutput =
            os -> productExportManager.exportXlsx(new RequestContext(1, 1), os);
        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.xlsx")
            .build();
    }
}