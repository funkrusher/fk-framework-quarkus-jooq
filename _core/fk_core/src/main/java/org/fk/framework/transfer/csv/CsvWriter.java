package org.fk.framework.transfer.csv;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fk.framework.exception.MappingException;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

public class CsvWriter<T> implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(CsvWriter.class);

    private List<String> fieldNames;
    private SequenceWriter sequenceWriter;

    final static DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public CsvWriter(OutputStream os, List<String> fieldNames) throws MappingException {
        this.fieldNames = fieldNames;

        CsvSchema.Builder builder = CsvSchema.builder();
        for (String nextHeader : fieldNames) {
            builder = builder.addColumn(nextHeader);
        }
        CsvSchema schema = builder.setUseHeader(true).build();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.findAndRegisterModules();
        csvMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        csvMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
        csvMapper.findAndRegisterModules();
        csvMapper.registerModule(new JavaTimeModule());

        try {
            this.sequenceWriter = csvMapper.writer(schema).writeValues(os);
        } catch (IOException e) {
            LOGGER.error("error in open csv-writer", e);
            throw new MappingException(e);
        }
    }

    public void writeItem(Map<String, Object> map) throws MappingException {
        try {
            Map<String, String> csvMap = new LinkedHashMap<>();
            for (String fieldName : fieldNames) {
                Object value = map.get(fieldName);
                if (value == null) {
                    csvMap.put(fieldName, "");
                } else {
                    // TODO: extend this if with all relevant types...
                    if (value instanceof LocalDateTime) {
                        LocalDateTime value0 = (LocalDateTime) value;
                        csvMap.put(fieldName, ISO_FORMATTER.format(value0));
                    } else {
                        csvMap.put(fieldName, value.toString());
                    }
                }
            }
            sequenceWriter.write(csvMap);
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
            LOGGER.error("error in close csv-writer", e);
            throw new MappingException(e);
        }
    }
}
