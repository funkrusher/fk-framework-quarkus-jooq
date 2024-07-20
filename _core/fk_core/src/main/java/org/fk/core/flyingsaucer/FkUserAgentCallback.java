package org.fk.core.flyingsaucer;

import org.xhtmlrenderer.swing.NaiveUserAgent;
import java.io.InputStream;

public class FkUserAgentCallback extends NaiveUserAgent {

    public FkUserAgentCallback() {
        super();
    }

    @Override
    protected InputStream resolveAndOpenStream(String uri) {
        if (uri.startsWith("custom://")) {
            // classloader as source.
            InputStream is = getClass().getClassLoader().getResourceAsStream(
                uri.replaceFirst("custom://", ""));
            return is;
        }
        return super.resolveAndOpenStream(uri);
    }
}