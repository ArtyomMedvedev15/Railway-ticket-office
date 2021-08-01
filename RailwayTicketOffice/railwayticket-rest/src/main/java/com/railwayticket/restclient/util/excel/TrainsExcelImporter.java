package com.railwayticket.restclient.util.excel;

import com.railwayticket.restclient.domains.Trains;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainsExcelImporter {

    final static Logger logger = Logger.getLogger(TrainsExcelImporter.class);

    public List<com.railwayticket.restclient.domains.Trains> ImportFromExcel(MultipartFile file, List<com.railwayticket.restclient.domains.Trains>allTrain) throws IOException {
        List<Trains>resultTrainList = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        for (Sheet sheet: workbook
        ) {
            int row_count = 0;
            for (Row row : sheet) {
                if(row_count==0){
                    row_count++;
                    continue;
                }

                Long id_train = (long)row.getCell(0).getNumericCellValue();
                String name_train = row.getCell(1).getStringCellValue();
                 com.railwayticket.restclient.domains.Trains.TypeTrainEnum typeTrain =
                        com.railwayticket.restclient.domains.Trains.TypeTrainEnum.valueOf(row.getCell(2).getStringCellValue());
                com.railwayticket.restclient.domains.Trains.DepartureStationEnum departureStation =
                        com.railwayticket.restclient.domains.Trains.DepartureStationEnum.valueOf(row.getCell(3).getStringCellValue());
                com.railwayticket.restclient.domains.Trains.ArrivalStationEnum arrivalStation =
                        com.railwayticket.restclient.domains.Trains.ArrivalStationEnum.valueOf(row.getCell(4).getStringCellValue());
                 Date datetimadeprture = java.sql.Date.valueOf(row.getCell(5).getStringCellValue());
                Date datetimearrival = java.sql.Date.valueOf(row.getCell(6).getStringCellValue());
                Integer available_ticket = (int)row.getCell(8).getNumericCellValue();
                Integer total_ticket = (int)row.getCell(7).getNumericCellValue();
                Float price_ticket = (float)row.getCell(9).getNumericCellValue();

                com.railwayticket.restclient.domains.Trains trains = new com.railwayticket.restclient.domains.Trains();

                trains.setIdTrain(id_train);
                trains.setNameTrain(name_train);
                trains.setDepartureStation(departureStation);
                trains.setArrivalStation(arrivalStation);
                trains.setTypeTrain(typeTrain);
                trains.setDateTimeDeparture(datetimadeprture.toString());
                trains.setDateTimeArrival(datetimearrival.toString());
                 trains.setTotalTicket(total_ticket);
                trains.setAvailableTicket(available_ticket);
                trains.setPriceTicket(price_ticket);

                if(!allTrain.contains(trains)) {
                    resultTrainList.add(trains);
                    logger.info("Train with id - " + trains.getIdTrain() + " was add");
                }else{
                    logger.error("Train with id - " + trains.getIdTrain() + " already exist!");
                }
            }
        }
        return resultTrainList;
    }
}
