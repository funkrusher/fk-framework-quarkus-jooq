package org.fk.product.controller.exports.xlsx;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.apache.commons.lang3.RandomStringUtils;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.request.RequestContext;
import org.fk.core.transfer.xlsx.XlsxWriter;
import org.fk.database1.Database1;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.dto.ProductDTO;
import org.fk.product.manager.ProductManager;
import org.jooq.DSLContext;
import org.jooq.Field;

import java.util.*;
import java.util.Iterator;
import java.util.List;

@Path("/api/v1/exports/xlsx/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductXlsxExportControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    Database1 database1;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootXlsxFile() throws InvalidDataException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        var productStream = productManager.streamAll(dsl);

        StreamingOutput streamingOutput = os -> {

            // Resolve fields for export.
            ProductRecord pc = new ProductRecord();
            List<String> fieldNames = new ArrayList<>();
            for (Field<?> field : pc.fields()) {
                fieldNames.add(field.getName());
            }

            try (XlsxWriter<ProductDTO> xlsxWriter = new XlsxWriter<>(os, "Products", fieldNames)) {
                final Iterator<ProductDTO> it = productStream.iterator();
                while (it.hasNext()) {
                    ProductDTO product = it.next();
                    product.setTypeId(generateLorem());
                    xlsxWriter.writeItem(product);
                }
            }
        };

        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.xlsx")
            .build();
    }

    private String generateLorem() {
        String generatedString = RandomStringUtils.randomAlphanumeric(100);
        return generatedString;
    }

}