package org.fk.provider.flyingsaucer;

import com.lowagie.text.Image;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
import org.xhtmlrenderer.pdf.ITextImageElement;

import java.io.InputStream;

import java.io.FileInputStream;

/**
 * Replaced element in order to replace elements like
 * <tt>&lt;div class="media" data-src="image.png" /></tt> with the real
 * media content.
 * see: https://stackoverflow.com/questions/11477065/using-flying-saucer-to-render-images-to-pdf-in-memory
 */
public class MediaReplacedElementFactory implements ReplacedElementFactory {
    private final ITextRenderer renderer;
    private final ReplacedElementFactory superFactory;

    public MediaReplacedElementFactory(ITextRenderer renderer, ReplacedElementFactory superFactory) {
        this.renderer = renderer;
        this.superFactory = superFactory;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox, UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }
        String nodeName = element.getNodeName();
        String className = element.getAttribute("class");
        // Replace any <div class="media" data-src="image.png" /> with the
        // binary data of `image.png` into the PDF.
        if ("div".equals(nodeName) && "media".equals(className)) {
            if (!element.hasAttribute("data-src")) {
                throw new RuntimeException("An element with class `media` is missing a `data-src` attribute indicating the media file.");
            }
            InputStream input = null;
            try {
                input = getClass().getClassLoader().getResourceAsStream(element.getAttribute("data-src"));
                final byte[] bytes = IOUtils.toByteArray(input);

                final Image image = Image.getInstance(bytes);
                final int factor = renderer.getSharedContext().getDotsPerPixel(); //Need to add this line

                image.scaleAbsolute(image.getPlainWidth() * factor, image.getPlainHeight() * factor);
                final FSImage fsImage = new ITextFSImage(image);
                if ((cssWidth != -1) || (cssHeight != -1)) {
                    fsImage.scale(cssWidth, cssHeight);
                }
                return new ITextImageElement(fsImage);
            } catch (Exception e) {
                throw new RuntimeException("There was a problem trying to read a template embedded graphic.", e);
            } finally {
                IOUtils.closeQuietly(input);
            }
        }
        return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
    }

    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }
}