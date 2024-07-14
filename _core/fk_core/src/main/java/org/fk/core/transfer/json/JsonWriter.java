package org.fk.core.transfer.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fk.core.dto.DTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonWriter<T extends DTO> implements AutoCloseable {

    private SequenceWriter sequenceWriter;

    public JsonWriter(OutputStream os, Class<T> clazz) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.findAndRegisterModules();
        jsonMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMapper.findAndRegisterModules();
        jsonMapper.registerModule(new JavaTimeModule());
        this.sequenceWriter = jsonMapper.writerFor(clazz).writeValues(os);
    }

    public void writeItem(T item) throws IOException {
        sequenceWriter.write(item);
    }

    @Override
    public void close() throws IOException {
        // dont close the sequenceWriter, as it would close the stream,
        // quarkus needs the stream still open / expects it to be open because it closes it.
    }
}
