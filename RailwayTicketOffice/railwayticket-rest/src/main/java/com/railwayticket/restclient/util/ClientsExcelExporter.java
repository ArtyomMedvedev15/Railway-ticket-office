package com.railwayticket.restclient.util;

import com.railwayticket.restclient.domains.ClientRailway;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientsExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ClientRailway> clientRailwayList;

    public ClientsExcelExporter(List<ClientRailway> clientRailwayList) {
        this.clientRailwayList = clientRailwayList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Clients");
    }

    private void WriteHeaderRow() {
        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(17);
        cellStyle.setFont(font);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        Cell cell = row.createCell(0);
        cell.setCellValue("Client ID");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Train ID");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(2);
        cell.setCellValue("Name Client");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(3);
        cell.setCellValue("Soname Client");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("Phone Client");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue("Date purchapse");
        cell.setCellStyle(cellStyle);

    }

    private void WriteDataRows() {
        int row_count = 1;

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(17);
        cellStyle.setFont(font);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        for (ClientRailway clientRailway : clientRailwayList) {
            Row row = sheet.createRow(row_count++);

            Cell cell = row.createCell(0);
            cell.setCellValue(clientRailway.getIdClient());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(clientRailway.getIdTrain());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(clientRailway.getNameClient());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(clientRailway.getSonameClient());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(clientRailway.getPhoneClient());
            sheet.autoSizeColumn(4);

            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue(clientRailway.getDatePurchase());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(cellStyle);

        }
    }

    public void ExportDataToExcel(HttpServletResponse response) throws IOException {
        WriteHeaderRow();
        WriteDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
