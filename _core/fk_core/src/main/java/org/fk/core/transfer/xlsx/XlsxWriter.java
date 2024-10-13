package org.fk.core.transfer.xlsx;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.fk.core.dto.AbstractDTO;
import org.fk.core.exception.MappingException;
import org.jboss.logging.Logger;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class XlsxWriter<T> implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(XlsxWriter.class);

    private SXSSFWorkbook workbook;
    private Sheet sheet;
    private List<String> fieldNames;
    private int rowIndex = 1;
    private OutputStream os;
    private CellStyle headerCellStyle;
    private CellStyle dataCellStyle;
    private CellStyle alternateDataCellStyle;
    private int[] maxColumnWidths;

    final static DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public XlsxWriter(OutputStream os, String sheetName, List<String> fieldNames) {
        this.os = os;
        this.workbook = new SXSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
        this.fieldNames = fieldNames;
        this.maxColumnWidths = new int[fieldNames.size()];

        // Create header row
        Row headerRow = sheet.createRow(0);
        createHeaderCellStyle();
        createDataCellStyle();
        createAlternateDataCellStyle();
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fieldNames.get(i));
            cell.setCellStyle(headerCellStyle);
            maxColumnWidths[i] = fieldNames.get(i).length();
        }

        // Apply auto-filter to the header row
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, fieldNames.size() - 1));
    }

    private void createHeaderCellStyle() {
        headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
    }

    private void createDataCellStyle() {
        dataCellStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 11);
        dataCellStyle.setFont(dataFont);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
    }

    private void createAlternateDataCellStyle() {
        alternateDataCellStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 11);
        alternateDataCellStyle.setFont(dataFont);
        alternateDataCellStyle.setBorderBottom(BorderStyle.THIN);
        alternateDataCellStyle.setBorderTop(BorderStyle.THIN);
        alternateDataCellStyle.setBorderRight(BorderStyle.THIN);
        alternateDataCellStyle.setBorderLeft(BorderStyle.THIN);
        alternateDataCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        alternateDataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public void writeItem(Map<String, Object> map) {
        Row row = sheet.createRow(rowIndex++);
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(rowIndex % 2 == 0 ? alternateDataCellStyle : dataCellStyle);

            Object value = map.get(fieldNames.get(i));
            String stringValue;
            if (value == null) {
                stringValue = "";
            } else if (value instanceof LocalDateTime) {
                stringValue = ISO_FORMATTER.format((LocalDateTime) value);
            } else {
                stringValue = value.toString();
            }

            cell.setCellValue(stringValue);

            // Update max column width
            if (stringValue.length() > maxColumnWidths[i]) {
                maxColumnWidths[i] = stringValue.length();
            }
        }
    }

    @Override
    public void close() throws MappingException {
        // Set the column widths based on the cached max widths
        for (int i = 0; i < fieldNames.size(); i++) {
            // default: 256
            sheet.setColumnWidth(i, maxColumnWidths[i] * 300); // Approximate calculation for width
        }

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