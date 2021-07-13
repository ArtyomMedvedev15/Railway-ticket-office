package com.railwayticket.restclient.util;

import com.railwayticket.restclient.domains.Trains;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TrainsExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<com.railwayticket.restclient.domains.Trains>trainsList;

    public TrainsExcelExporter(List<com.railwayticket.restclient.domains.Trains> trainsList) {
        this.trainsList = trainsList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Trains");
    }

    private void WriteHeaderRow(){
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
        cell.setCellValue("Train ID");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Train Name");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(2);
        cell.setCellValue("Train Type");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(3);
        cell.setCellValue("Departure station");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("Arrival station");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue("Date time departure");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(6);
        cell.setCellValue("Date time arrival");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(7);
        cell.setCellValue("Total Ticket");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(8);
        cell.setCellValue("Available Ticket");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(9);
        cell.setCellValue("Price Ticket");
        cell.setCellStyle(cellStyle);
    }

    private void WriteDataRows(){
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
        for (Trains train: trainsList){
            Row row = sheet.createRow(row_count++);

            Cell cell = row.createCell(0);
            cell.setCellValue(train.getIdTrain());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(train.getNameTrain());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(train.getTypeTrain().getValue());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(train.getDepartureStation().getValue());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(train.getArrivalStation().getValue());
            sheet.autoSizeColumn(4);

            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue(train.getDateTimeDeparture());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6);
            cell.setCellValue(train.getDateTimeArrival());
            sheet.autoSizeColumn(6);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7);
            cell.setCellValue(train.getTotalTicket());
            sheet.autoSizeColumn(7);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(8);
            cell.setCellValue(train.getAvailableTicket());
            sheet.autoSizeColumn(8);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(9);
            cell.setCellValue(train.getPriceTicket());
            sheet.autoSizeColumn(9);
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
