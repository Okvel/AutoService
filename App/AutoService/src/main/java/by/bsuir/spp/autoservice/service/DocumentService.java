package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.DocumentFormat;
import by.bsuir.spp.autoservice.entity.DocumentType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class DocumentService {
    private static DocumentService instance = new DocumentService();
    private static CellStyle defaultStyle;
    private static CellStyle headerStyle;
    private static CellStyle bottomStyle;
    private static CellStyle dateStyle;

    private DocumentService() {}

    public static DocumentService getInstance() {
        return instance;
    }

    private void createCellStyle(Workbook workbook) {
        defaultStyle = workbook.createCellStyle();
        defaultStyle.setBorderBottom(CellStyle.BORDER_THIN);
        defaultStyle.setBorderLeft(CellStyle.BORDER_THIN);
        defaultStyle.setBorderTop(CellStyle.BORDER_THIN);
        defaultStyle.setBorderRight(CellStyle.BORDER_THIN);
        defaultStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        defaultStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        bottomStyle = workbook.createCellStyle();
        bottomStyle.setBorderBottom(CellStyle.BORDER_THIN);
        bottomStyle.setBorderLeft(CellStyle.BORDER_THIN);
        bottomStyle.setBorderTop(CellStyle.BORDER_THIN);
        bottomStyle.setBorderRight(CellStyle.BORDER_THIN);
        bottomStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        bottomStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        dateStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("dd.MM.yyyy"));
        dateStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dateStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dateStyle.setBorderTop(CellStyle.BORDER_THIN);
        dateStyle.setBorderRight(CellStyle.BORDER_THIN);
        dateStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        dateStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
    }

    private String saveGeneratedDocument(DocumentType type, DocumentFormat format) throws ServiceException {
        String result = null;
        switch (format) {
            case XLSX:
                result = type.getPath() + format.getFormat();
                break;
            case CSV:
                saveCsv(type.getPath());
                result = type.getPath() + format.getFormat();
                break;
            case PDF:
                break;
        }

        return result;
    }

    private void saveCsv(String fileName) throws ServiceException {
        File outFile = new File(fileName + DocumentFormat.CSV.getFormat());
        File inputFile = new File(fileName + DocumentFormat.XLSX.getFormat());
        StringBuilder data = new StringBuilder();

        try (
                FileOutputStream fos = new FileOutputStream(outFile);
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(inputFile));
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "Windows-1251");
                ) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue()).append(";");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue()).append(";");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue()).append(";");
                            break;
                        default:
                            data.append(cell).append(";");
                    }
                }
                data.append("\r\n");
            }
            outputStreamWriter.write(data.toString());
            outputStreamWriter.flush();
        } catch (IOException ex) {
            throw new ServiceException(ex);
        }
    }
}
