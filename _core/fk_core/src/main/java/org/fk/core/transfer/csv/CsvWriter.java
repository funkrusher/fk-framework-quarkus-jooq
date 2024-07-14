package org.fk.core.transfer.csv;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.fk.core.dto.DTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvWriter<T extends DTO> implements AutoCloseable {

    private List<String> fieldNames;
    private SequenceWriter sequenceWriter;

    public CsvWriter(OutputStream os, List<String> fieldNames) throws IOException {
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

        this.sequenceWriter = csvMapper.writer(schema).writeValues(os);
    }

    public void writeItem(T item) throws IOException {
        Map<String, Object> map = item.getBookKeeper().touched();

        Map<String, String> csvMap = new LinkedHashMap<>();
        for (String fieldName: fieldNames) {
            Object obj = map.get(fieldName);
            if (obj == null) {
                csvMap.put(fieldName, "");
            } else {
                csvMap.put(fieldName, obj.toString());
            }
        }
        sequenceWriter.write(csvMap);
    }

    @Override
    public void close() throws IOException {
        // dont close the sequenceWriter, as it would close the stream,
        // quarkus needs the stream still open / expects it to be open because it closes it.
    }
}
