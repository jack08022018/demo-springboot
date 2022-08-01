package com.demo.service.impl;

import com.demo.repository.employee.dto.EmployeeInfo;
import com.demo.service.ExportService;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {
    @Override
    public ByteArrayInputStream exportExcel(MultipartFile file) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFSheet sheet = workbook.createSheet("Proposal");

        List<EmployeeInfo> data = new ArrayList<>();

//      header
        int colNums = createHeader(sheet, workbook, 2);

//      data
//        pushExcelData(sheet, workbook, data, 3, colNums);

        workbook.write(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private int createHeader(Sheet sheet, Workbook workbook, int currentRow) {
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setWrapText(true);
        style.setFillBackgroundColor(IndexedColors.YELLOW.index);
        style.setFillForegroundColor(IndexedColors.YELLOW.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        String[] cols = {
                "Số hợp đồng",
                "Số hồ sơ",
                "Ngày bắt đầu"
        };
        int[] widths = {20, 20, 20};
        for (int i = 0; i < widths.length; i++) {
            sheet.setColumnWidth(i, widths[i]*256);
        }

        Row row = sheet.createRow(currentRow);
        row.setHeight((short) 500);
        Cell cell;
        for (int i = 0; i < cols.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cols[i]);
            cell.setCellStyle(style);
        }
        return widths.length;
    }

    private void pushExcelData(Sheet sheet, Workbook workbook, List<EmployeeInfo> data, int currentRow, int colNums) {
        XSSFCellStyle styleNormal = getCellStyle(workbook, ExcelFormat.MORMAL);
        Row row = sheet.createRow(currentRow);
        Cell cell;
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            row = sheet.createRow(currentRow + rowIndex);
            for (int colIndex = 0; colIndex < colNums; colIndex++) {
                cell = row.createCell(colIndex);
                setCellValue(cell, styleNormal, colIndex, data.get(rowIndex));
            }
        }
    }
    private void setCellValue(Cell cell, XSSFCellStyle style, int colIndex, EmployeeInfo info) {
        cell.setCellStyle(style);
        switch (colIndex) {
            case 0:
                cell.setCellValue(info.getEmpNo());
                break;
            case 1:
                cell.setCellValue(info.getFullName());
                break;
            case 2:
                cell.setCellValue(info.getSalary());
                break;
        }
    }

    private XSSFCellStyle getCellStyle(Workbook workbook, Enum format) {
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setWrapText(true);
        if(format == ExcelFormat.PERCENT) {
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0%")); //0.00%
        }
        if(format == ExcelFormat.MONEY) {
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0")); // \"$\"#,##0_);(\"$\"#,##0)
        }
        return style;
    }
    enum ExcelFormat {
        PERCENT,
        MONEY,
        MORMAL;
    }
}
