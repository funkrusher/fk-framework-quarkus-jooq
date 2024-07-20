package org.fk.core.transfer.pdf;

import org.fk.core.exception.MappingException;
import org.fk.core.flyingsaucer.MediaReplacedElementFactory;
import org.fk.core.flyingsaucer.FkUserAgentCallback;
import org.jboss.logging.Logger;
import org.jsoup.Jsoup;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;


public class PdfWriter implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(PdfWriter.class);

    private final OutputStream os;
    private final ITextRenderer renderer;
    private boolean isFirstItem = true;
    private int pageNumber = 0;

    public PdfWriter(OutputStream os) throws MappingException {
        this.os = os;
        this.renderer = new ITextRenderer();

        renderer.getSharedContext().setReplacedElementFactory(
            new MediaReplacedElementFactory(renderer, renderer.getSharedContext().getReplacedElementFactory()));

        renderer.getSharedContext().setUserAgentCallback(
            new FkUserAgentCallback()
        );

        try {
            renderer.getFontResolver().addFont("META-INF/resources/cmuntt.ttf", true);
        } catch (IOException e) {
            LOGGER.error("error in open pdf-writer", e);
            throw new MappingException(e);
        }
    }

    public void writeItem(String html) throws MappingException {
        renderer.setDocumentFromString(toXHTML(html));
        renderer.layout();
        if (isFirstItem) {
            renderer.createPDF(os, false);
            this.isFirstItem = false;
        } else {
            renderer.writeNextDocument(pageNumber + 1);
        }
        this.pageNumber = renderer.getWriter().getPageNumber();
    }

    @Override
    public void close() throws MappingException {
        // note: we must leave the original outputStream open.
        // jax-rs needs the stream still open / expects it to be open because it closes it.
        // see: https://stackoverflow.com/questions/39572872/closing-jax-rs-streamingoutputs-outputstream
        renderer.finishPDF();
    }

    /**
     * Helper-function to convert html to xhtml
     * - flyingsaucer expects XHTML, so we need to transform HTML to XHTML to avoid errors for invalid HTML.
     *
     * @param html html
     * @return xhtml
     */
    private String toXHTML( String html ) {
        final org.jsoup.nodes.Document document = Jsoup.parse(html);
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        return document.html();
    }
}
