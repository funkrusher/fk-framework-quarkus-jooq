package org.fk.core.transfer.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fk.core.dto.DTO;
import org.fk.core.exception.MappingException;
import org.fk.core.transfer.csv.CsvWriter;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonWriter<T extends DTO> implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(JsonWriter.class);

    private SequenceWriter sequenceWriter;

    public JsonWriter(OutputStream os, Class<T> clazz) throws MappingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.findAndRegisterModules();
        jsonMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMapper.findAndRegisterModules();
        jsonMapper.registerModule(new JavaTimeModule());

        try {
            this.sequenceWriter = jsonMapper.writerFor(clazz).writeValues(os);
        } catch (IOException e) {
            LOGGER.error("error in open json-writer", e);
            throw new MappingException(e);
        }
    }

    public void writeItem(T item) throws MappingException {
        try {
            sequenceWriter.write(item);
        } catch (Exception e) {
            LOGGER.error("error in write item", e);
            throw new MappingException(e);
        }
    }

    @Override
    public void close() throws MappingException {
        // note: we must leave the original outputStream open.
        // jax-rs needs the stream still open / expects it to be open because it closes it.
        // see: https://stackoverflow.com/questions/39572872/closing-jax-rs-streamingoutputs-outputstream
        try {
            sequenceWriter.close();
        } catch (IOException e) {
            LOGGER.error("error in close json-writer", e);
            throw new MappingException(e);
        }
    }
}
