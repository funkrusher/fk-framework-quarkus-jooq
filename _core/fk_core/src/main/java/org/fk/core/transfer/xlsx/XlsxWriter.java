package org.fk.core.transfer.xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.fk.core.dto.DTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class XlsxWriter<T extends DTO> implements AutoCloseable {

    private SXSSFWorkbook workbook;
    private Sheet sheet;
    private List<String> fieldNames;
    private int rowIndex = 1;
    private OutputStream os;

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

            Object obj = map.get(fieldNames.get(i));
            if (obj == null) {
                cell.setCellValue("");
            } else {
                String value = map.get(fieldNames.get(i)).toString();
                cell.setCellValue(value);
            }
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.workbook.write(os);
        } finally {
            this.workbook.close();
        }
    }
}
