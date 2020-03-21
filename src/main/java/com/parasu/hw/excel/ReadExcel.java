package com.parasu.hw.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

public class ReadExcel {

    private String getCellValue(Cell cell) {
        String str = "";
        if (cell == null) {
            return str;
        }
        CellType ct = cell.getCellType();
        if (ct == CellType.BOOLEAN) {
            str = "" + cell.getBooleanCellValue();
        } else if (ct == CellType.NUMERIC) {
            str = "" + cell.getNumericCellValue();
        } else if (ct == CellType.STRING) {
            str = "" + cell.getStringCellValue();
        }
        return str;
    }

    public Map<String, List<String>> read(String fileName) throws IOException {
        File excelFile = new File(fileName);
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();


        Map<String, List<String>> excelValues = new HashMap<>();
        int[] idRows = { 1, 2 };
        int cellIndex = 1;
        while(rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            StringBuilder sb = new StringBuilder();
            List<String> rowValues = new ArrayList<>();
            cellIndex = 1;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = getCellValue(cell);
                rowValues.add(cellValue);
                if(isIdCell(idRows, cellIndex)) {
                    sb.append(cellValue);
                    sb.append("___");
                }
                cellIndex++;
            }

            if(StringUtils.hasText(sb.toString())) {
                excelValues.put(sb.toString(), rowValues);
            }
        }

        workbook.close();
        fis.close();

        return excelValues;
    }

    private boolean isIdCell(int[] idCells, int cellId) {
        boolean b = false;
        for(int id : idCells) {
            if(id == cellId) {
                b = true;
                break;
            }
        }
        return b;
    }
}
