package org.fk.backend.controllers.exports.csv;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.fk.core.auth.FkSecurityIdentity;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.product.manager.ProductManager;
import org.fk.core.transfer.TransferCsvMapper;
import org.fk.core.util.request.RequestContext;
import org.jooq.Field;

import java.util.*;


@Path("/api/v1/exports/csv/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductCsvExportControllerV1 {

    @Inject
    FkSecurityIdentity fkSecurityIdentity;

    @Inject
    ProductManager productService;

    @Inject
    @TransferCsvMapper
    CsvMapper csvMapper;

    @GET
    @Authenticated
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/")
    public Response streamRootCsvFile() {
        RequestContext requestContext = new RequestContext(fkSecurityIdentity, 1);
        var productStream = productService.streamAll(requestContext);

        StreamingOutput streamingOutput = outputStream -> {
            ProductRecord pc = new ProductRecord();
            List<String> fieldNames = new ArrayList<>();
            for (Field<?> field : pc.fields()) {
                fieldNames.add(field.getName());
            }

            CsvSchema.Builder builder = CsvSchema.builder();
            for (String nextHeader : fieldNames) {
                builder = builder.addColumn(nextHeader);
            }
            CsvSchema schema = builder.setUseHeader(true).build();

            Iterator<ProductDTO> it = productStream.iterator();

            SequenceWriter sequenceWriter = csvMapper.writer(schema).writeValues(outputStream);
            while (it.hasNext()) {
                ProductDTO product = it.next();
                ProductRecord record = product.into(new ProductRecord());

                Map<String, String> map = new LinkedHashMap<>();
                for (String fieldName: fieldNames) {
                    map.put(fieldName, record.getValue(fieldName).toString());
                }
                sequenceWriter.write(map);
            }
        };

        return Response
                .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=product_export.csv")
                .build();
    }



    @GET
    @Authenticated
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/lang")
    public Response streamLangCsvFile() {
        RequestContext requestContext = new RequestContext(fkSecurityIdentity, 1);
        var productStream = productService.streamAll(requestContext);

        StreamingOutput streamingOutput = outputStream -> {
            ProductLangRecord pc = new ProductLangRecord();
            List<String> fieldNames = new ArrayList<>();
            for (Field<?> field : pc.fields()) {
                fieldNames.add(field.getName());
            }

            CsvSchema.Builder builder = CsvSchema.builder();
            for (String nextHeader : fieldNames) {
                builder = builder.addColumn(nextHeader);
            }
            CsvSchema schema = builder.setUseHeader(true).build();

            Iterator<ProductDTO> it = productStream.iterator();

            SequenceWriter sequenceWriter = csvMapper.writer(schema).writeValues(outputStream);
            while (it.hasNext()) {
                ProductDTO product = it.next();
                for (ProductLangDTO productLangDTO : product.getLangs()) {
                    ProductLangRecord record = productLangDTO.into(new ProductLangRecord());

                    Map<String, String> map = new LinkedHashMap<>();
                    for (String fieldName: fieldNames) {
                        map.put(fieldName, record.getValue(fieldName).toString());
                    }
                    sequenceWriter.write(map);
                }
            }
        };

        return Response
                .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=product_lang_export.csv")
                .build();
    }

}
