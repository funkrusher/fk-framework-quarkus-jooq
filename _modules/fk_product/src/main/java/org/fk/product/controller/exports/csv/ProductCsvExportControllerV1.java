package org.fk.product.controller.exports.csv;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.transfer.csv.CsvWriter;
import org.fk.core.transfer.xlsx.XlsxWriter;
import org.fk.database1.Database1;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.manager.ProductManager;
import org.fk.core.request.RequestContext;
import org.jooq.DSLContext;
import org.jooq.Field;

import java.util.*;


@Path("/api/v1/exports/csv/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductCsvExportControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    Database1 database1;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootCsvFile() throws InvalidDataException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        var productStream = productManager.streamAll(dsl);

        StreamingOutput streamingOutput = os -> {
            // Resolve fields for export.
            ProductRecord pc = new ProductRecord();
            List<String> fieldNames = new ArrayList<>();
            for (Field<?> field : pc.fields()) {
                fieldNames.add(field.getName());
            }

            try (CsvWriter<ProductDTO> csvWriter = new CsvWriter<>(os, fieldNames)) {
                final Iterator<ProductDTO> it = productStream.iterator();
                while (it.hasNext()) {
                    ProductDTO product = it.next();
                    csvWriter.writeItem(product);
                }
            }
        };

        return Response
                .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=product_export.csv")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/lang")
    public Response streamLangCsvFile() throws InvalidDataException {
        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        var productStream = productManager.streamAll(dsl);

        StreamingOutput streamingOutput = os -> {
            // Resolve fields for export.
            ProductLangRecord pc = new ProductLangRecord();
            List<String> fieldNames = new ArrayList<>();
            for (Field<?> field : pc.fields()) {
                fieldNames.add(field.getName());
            }

            try (CsvWriter<ProductLangDTO> csvWriter = new CsvWriter<>(os, fieldNames)) {
                final Iterator<ProductDTO> it = productStream.iterator();
                while (it.hasNext()) {
                    ProductDTO product = it.next();
                    for (ProductLangDTO productLangDTO : product.getLangs()) {
                        csvWriter.writeItem(productLangDTO);
                    }
                }
            }
        };

        return Response
                .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=product_lang_export.csv")
                .build();
    }

}
