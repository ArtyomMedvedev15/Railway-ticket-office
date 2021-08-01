package com.railwayticket.restclient.util.excel;

import com.railwayticket.restclient.domains.ClientRailway;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientsExcelImporter {
    final static Logger logger = Logger.getLogger(TrainsExcelImporter.class);

    public List<ClientRailway> ImportFromExcel(MultipartFile file, List<ClientRailway>allClients) throws IOException {
        List<ClientRailway>resultClientsList = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        for (Sheet sheet: workbook
        ) {
            int row_count = 0;
            for (Row row : sheet) {
                if(row_count==0){
                    row_count++;
                    continue;
                }

                Long id_client = (long)row.getCell(0).getNumericCellValue();
                Long id_train = (long)row.getCell(1).getNumericCellValue();
                String name_client = row.getCell(2).getStringCellValue();
                String soname_client = row.getCell(3).getStringCellValue();
                String phone_client = row.getCell(4).getStringCellValue();
                String date_purchapse = row.getCell(5).getStringCellValue();

                ClientRailway clientRailway = new ClientRailway();
                clientRailway.setIdClient(id_client);
                clientRailway.setIdTrain(id_train);
                clientRailway.setNameClient(name_client);
                clientRailway.setSonameClient(soname_client);
                clientRailway.setPhoneClient(phone_client);
                clientRailway.setDatePurchase(date_purchapse);


                if(!allClients.contains(clientRailway)) {
                    resultClientsList.add(clientRailway);
                    logger.info("Client with id - " + clientRailway.getIdClient() + " was add.");
                }else{
                    logger.error("Client with id - " + clientRailway.getIdTrain() + " already exist!");
                }
            }
        }
        return resultClientsList;
    }
}
