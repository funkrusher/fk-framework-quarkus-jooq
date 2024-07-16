package org.fk.core.transfer.xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.fk.core.dto.DTO;
import org.fk.core.exception.MappingException;
import org.jboss.logging.Logger;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class XlsxWriter<T extends DTO> implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(XlsxWriter.class);

    private SXSSFWorkbook workbook;
    private Sheet sheet;
    private List<String> fieldNames;
    private int rowIndex = 1;
    private OutputStream os;

    final static DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public XlsxWriter(OutputStream os, String sheetName, List<String> fieldNames) {
        this.os = os;
        this.workbook = new SXSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
        this.fieldNames = fieldNames;

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fieldNames.get(i));
        }
    }

    public void writeItem(T item) {
        Map<String, Object> map = item.getBookKeeper().touched();

        Row row = sheet.createRow(rowIndex++);
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell cell = row.createCell(i);

            Object value = map.get(fieldNames.get(i));
            if (value == null) {
                cell.setCellValue("");
            } else {
                // its safer for now to always output all types as string.
                // sometimes we should also differ types here (via instanceof) and use corresponding excel-types.
                // TODO: extend this if with all relevant types...
                if (value instanceof LocalDateTime) {
                    LocalDateTime value0 = (LocalDateTime) value;
                    cell.setCellValue(ISO_FORMATTER.format(value0));
                } else {
                    cell.setCellValue(value.toString());
                }
            }
        }
    }

    @Override
    public void close() throws MappingException {
        // note: we must leave the original outputStream open.
        // jax-rs needs the stream still open / expects it to be open because it closes it.
        // see: https://stackoverflow.com/questions/39572872/closing-jax-rs-streamingoutputs-outputstream
        try {
            try {
                this.workbook.write(os);
            } finally {
                this.workbook.close();
            }
        } catch (Exception e) {
            LOGGER.error("error in close xlsx-writer", e);
            throw new MappingException(e);
        }
    }
}
