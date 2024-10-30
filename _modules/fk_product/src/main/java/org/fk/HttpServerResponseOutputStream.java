package org.fk;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import java.io.IOException;
import java.io.OutputStream;

public class HttpServerResponseOutputStream extends OutputStream {
    private final HttpServerResponse response;

    public HttpServerResponseOutputStream(HttpServerResponse response) {
        this.response = response;
    }

    @Override
    public void write(int b) throws IOException {
        // Write a single byte as a Buffer to the response
        response.write(Buffer.buffer(new byte[]{(byte) b}));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        // Validate indices
        if (b == null) {
            throw new NullPointerException("Byte array is null");
        }
        if (off < 0 || len < 0 || off + len > b.length) {
            throw new IndexOutOfBoundsException("Invalid offset or length");
        }

        // Create a Buffer from the provided byte array section and write it to the response
        Buffer buffer = Buffer.buffer();
        buffer.appendBytes(b, off, len);
        response.write(buffer);
    }

    @Override
    public void write(byte[] b) throws IOException {
        // Write the whole byte array as a Buffer to the response
        response.write(Buffer.buffer(b));
    }

    @Override
    public void close() throws IOException {
        // End the response when finished writing
        response.end();
    }

    @Override
    public void flush() throws IOException {
        // Optional: no need to implement if using chunked responses
    }
}