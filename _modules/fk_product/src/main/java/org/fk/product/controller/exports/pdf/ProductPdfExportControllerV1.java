package org.fk.product.controller.exports.pdf;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.MessageBundles;
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
import org.fk.core.flyingsaucer.MediaReplacedElementFactory;
import org.fk.core.request.RequestContext;
import org.fk.database1.Database1;
import org.fk.product.dto.ProductDTO;
import org.fk.product.manager.ProductManager;
import org.fk.product.message.ProductMessages;
import org.fk.product.template.Templates;
import org.jooq.DSLContext;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Path("/api/v1/exports/pdf/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductPdfExportControllerV1 {

    @Inject
    ProductManager productManager;

    @Inject
    Database1 database1;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/{chunkSize}/{language}")
    public Response streamRootPdfInChunksFile(int chunkSize, String language) throws InvalidDataException {

        DSLContext dsl = database1.dsl(new RequestContext(1, 1));
        var productStream = productManager.streamAll(dsl);

        Stream<List<ProductDTO>> chunkStream = chunk(productStream, chunkSize);

        StreamingOutput streamingOutput = outputStream -> {
            Locale locale = null;
            if (language.equals("de")) {
                locale = Locale.GERMANY;
            } else {
                locale = Locale.US;
            }

            ProductMessages productMessages = MessageBundles.get(ProductMessages.class, Localized.Literal.of(locale.toLanguageTag()));

            String test = productMessages.product_paginate_localizationTest();

            Iterator<List<ProductDTO>> it = chunkStream.iterator();

            ITextRenderer renderer = new ITextRenderer();

            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont("META-INF/resources/cmuntt.ttf", true);

            // we need to create the target PDF
            // we'll create one page per input string, but we call layout for the first
            String introductionPage = "<html>" +
                "    Introduction..." +
                "</html>";
            renderer.setDocumentFromString(introductionPage);
            renderer.layout();
            renderer.createPDF(outputStream, false);

            int pageNumber = renderer.getWriter().getPageNumber();

            // each page after the first we add using layout() followed by writeNextDocument()
            while (it.hasNext()) {
                List<ProductDTO> productsChunk = it.next();

                List<ProductDTO> productsChunkWithData = productsChunk.stream().map(x -> x.setTypeId(generateLorem())).toList();

                String html = Templates.productsTemplate(productsChunkWithData).setLocale(locale).render();
                renderer.getSharedContext().setReplacedElementFactory(new MediaReplacedElementFactory(renderer, renderer.getSharedContext().getReplacedElementFactory()));
                renderer.setDocumentFromString(html);
                renderer.layout();

                pageNumber = renderer.getWriter().getPageNumber();

                renderer.writeNextDocument(pageNumber);
            }

            // complete the PDF
            renderer.finishPDF();
        };

        return Response
            .ok(streamingOutput, MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=product_export.pdf")
            .build();
    }

    private String generateLorem() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return generatedString;
    }

    /**
     * Helper function to chunk a stream of items into a stream of List of items.
     *
     * @param stream stream
     * @param size chunk-size of each chunk
     * @return stream of list of items.
     */
    Stream<List<ProductDTO>> chunk(Stream<ProductDTO> stream, int size) {
        Iterator<ProductDTO> iterator = stream.iterator();
        Iterator<List<ProductDTO>> listIterator = new Iterator<>() {

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public List<ProductDTO> next() {
                List<ProductDTO> result = new ArrayList<>(size);
                for (int i = 0; i < size && iterator.hasNext(); i++) {
                    result.add(iterator.next());
                }
                return result;
            }
        };
        return StreamSupport.stream(((Iterable<List<ProductDTO>>) () -> listIterator).spliterator(), false);
    }
}
