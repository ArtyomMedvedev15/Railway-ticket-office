package com.railwayticket.restclient.rest;

import com.domain.Stations;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.mysql.cj.xdevapi.Client;
import com.railwayticket.restclient.domains.ClientRailway;
import com.railwayticket.restclient.domains.Trains;
import com.railwayticket.restclient.restapi.ApiApiDelegate;
import com.railwayticket.restclient.util.*;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.MailSenderApi;
import com.railwayticket.services_api.TrainServiceApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
import com.railwayticket.services_api.exception.TrainServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RailwayRestApiImpl implements ApiApiDelegate {

    final static Logger logger = Logger.getLogger(RailwayRestApiImpl.class);

    @Autowired
    private ClientServiceApi clientServiceApi;

    @Autowired
    private TrainServiceApi trainServiceApi;

    @Autowired
    private MailSenderApi mailSender;

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ClientRailway>> allClientUsingGET() {
        List<com.domain.ClientRailway>allClient = clientServiceApi.FindAll();

        if(allClient.isEmpty()){
            logger.error("Empty list clients. " + "List size: 0" + " With status No found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("All client. " + " List size: " + allClient.size() + " With status Ok");
        return new ResponseEntity<List<ClientRailway>>(ConvertDomain.convertDomainClientRailwayList(allClient),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Trains>> allTrainUsingGET() {
        List<com.domain.Trains>allTrain= trainServiceApi.FindAll();

        logger.info("All trains. " + " List size: " + allTrain.size() + " With status Ok");
        return new ResponseEntity<List<Trains>>(ConvertDomain.convertDomainTrainsList(allTrain),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientRailway> clientRailwayByIdUsingGET(Long id) {
        if(id==null){
            logger.error("Get client by id failed. " + " Id: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        com.domain.ClientRailway clientRailway = null;
        try {
            clientRailway = clientServiceApi.getOneById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(clientRailway==null){
            logger.error("Get client by id Failed. " + " Client not found"  + " With status Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Get client by id. " + " Id: " + id + " With status OK");
        return new ResponseEntity<ClientRailway>(ConvertDomain.convertDomainClientRailway(clientRailway),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientRailway> deleteClientUsingGET(Long id) {
        com.domain.ClientRailway clientRailwayDelete = null;
        try {
            clientRailwayDelete = clientServiceApi.getOneById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(clientRailwayDelete==null){
            logger.error("Delete client failed. " + "Client: null" + " With status Not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            clientServiceApi.delete(clientRailwayDelete);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        logger.info("Delete client. " + "Client: " + clientRailwayDelete.toString() + " With status No content");

        return new ResponseEntity<ClientRailway>(ConvertDomain.convertDomainClientRailway(clientRailwayDelete),HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Trains> deleteTrainsUsingGET(Long id) {
        com.domain.Trains trainsDelete = null;
        try {
            trainsDelete = trainServiceApi.getOneById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(trainsDelete==null){
            logger.error("Delete train failed. " + "Train: null" + " With status Not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            trainServiceApi.delete(trainsDelete);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        logger.info("Delete train. " + "Train: " + trainsDelete.toString() + " With status No content");

        return new ResponseEntity<Trains>(ConvertDomain.convertDomainTrains(trainsDelete),HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<List<ClientRailway>> findAllClientByNameUsingGET(String name) {
        List<com.domain.ClientRailway>allClient = null;
        try {
            allClient = clientServiceApi.FindByNameClient(name);
        } catch (ClientServiceException e) {
            e.printStackTrace();
        }

        if(allClient.isEmpty()){
            logger.error("Empty list. " + "List size: 0" + " With status No found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("All client by name. " + "Name: " + name + " With status Ok");

        return new ResponseEntity<List<ClientRailway>>(ConvertDomain.convertDomainClientRailwayList(allClient),HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Trains>> findAllTrainsByDatesAndStationsUsingPOST(String arrivalDate, String arrivalStationFind, String departureDate, String departureStationFind) {
        Date date_departure_find= null;

        try {
            date_departure_find = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        Date date_arrival_find= null;

        try {
            date_arrival_find = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }


        List<com.domain.Trains>allTrain = null;
        try {
            allTrain = trainServiceApi.FindAllByDateDepartureArrivalStations
                    (new java.sql.Date(date_departure_find.getTime()),new java.sql.Date(date_arrival_find.getTime()),
                            Stations.valueOf(departureStationFind.toUpperCase()),Stations.valueOf(arrivalStationFind.toUpperCase()));
        } catch (TrainServiceException e) {
            e.printStackTrace();
        }

        if(allTrain.isEmpty()){
            logger.error("Empty list. " + "List size: 0" + " With status No found");
            return new ResponseEntity<List<Trains>>(Collections.emptyList(),HttpStatus.NOT_FOUND);
        }

        logger.info("All train by dates and stations. " + " Date arrival: " + date_arrival_find + " Date departure: " + date_departure_find
                + " Stations arrival: " + arrivalStationFind + " Stations departure: " + departureStationFind +  " With status Ok");
        return new ResponseEntity<List<Trains>>(ConvertDomain.convertDomainTrainsList(allTrain),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientRailway> saveClientUsingPOST(ClientRailway clientSave) {

        HttpHeaders httpHeaders = new HttpHeaders();

        if(clientSave==null){
            logger.error("Save client failed." + " Client: null " + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            clientServiceApi.save(ConvertDomain.convertDomainClientRailway(clientSave));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        logger.info("Save new client." + " Client name: " + clientSave.getNameClient() + " With status Created");
        return new ResponseEntity<>(clientSave,httpHeaders,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Trains> saveTrainUsingPOST(Trains trainSave) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(trainSave==null){
            logger.error("Save train failed." + "Train: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            trainServiceApi.save(ConvertDomain.convertDomainTrains(trainSave));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        logger.info("Save new train." + " Train name: " + trainSave.getNameTrain() + " With status Created");
        return new ResponseEntity<Trains>(trainSave,httpHeaders,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Trains> trainByIdUsingGET(Long id) {
        if(id==null){
            logger.error("Get train by id failed. " + " Id: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        com.domain.Trains trains = null;
        try {
            trains = trainServiceApi.getOneById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(trains==null){
            logger.error("Get train by id Failed. " + " Train not found"  + " With status Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Get train by id. " + " Id: " + id + " With status OK");
        return new ResponseEntity<Trains>(ConvertDomain.convertDomainTrains(trains),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ClientRailway> updateClientUsingPOST(ClientRailway clientUpdate) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(clientUpdate==null){
            logger.error("Update client failed." + " Client: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            clientServiceApi.update(ConvertDomain.convertDomainClientRailway(clientUpdate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        logger.info("Update client. " + "Client: " + clientUpdate.getNameClient() + " With status OK");
        return new ResponseEntity<>(clientUpdate,httpHeaders,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Trains> updateTrainUsingPOST(Trains trainUpdate) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(trainUpdate==null){
            logger.error("Update train failed." + " Train: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            trainServiceApi.update(ConvertDomain.convertDomainTrains(trainUpdate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        logger.info("Update train. " + "Train: " + trainUpdate.getNameTrain() + " With status OK");
        return new ResponseEntity<>(trainUpdate,httpHeaders,HttpStatus.OK);
    }

    @GetMapping("/api/clients/listclients/export/excel")
    public void exportClientsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String filename = "clientsExcel_"+dateFormat.format(new Date())+".xlsx";
        String headerValue = "attachement; filename="+filename;

        response.setHeader(headerKey,headerValue);

        List<com.domain.ClientRailway>AllClient = clientServiceApi.FindAll();

        ClientsExcelExporter excelExporter = new ClientsExcelExporter(ConvertDomain.convertDomainClientRailwayList(AllClient));
        excelExporter.ExportDataToExcel(response);
        logger.info("Export client to file");
    }

    @GetMapping("/api/train/listtrains/export/excel")
    public void exportTrainsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String filename = "trainsExcel"+dateFormat.format(new Date())+".xlsx";
        String headerValue = "attachement; filename="+filename;

        response.setHeader(headerKey,headerValue);

        List<com.domain.Trains>AllTrains = trainServiceApi.FindAll();

        TrainsExcelExporter excelExporter = new TrainsExcelExporter(ConvertDomain.convertDomainTrainsList(AllTrains));
        excelExporter.ExportDataToExcel(response);
        logger.info("Export client to file");
    }

     @PostMapping("/api/train/excel/import")
    public void importExcelToSqlTrains(@RequestParam("file")MultipartFile file) throws IOException {
        List<com.domain.Trains>allTrains = trainServiceApi.FindAll();
        TrainsExcelImporter excelImporter = new TrainsExcelImporter();
        List<Trains>importsTrains = excelImporter.ImportFromExcel(file,ConvertDomain.convertDomainTrainsList(allTrains));

        importsTrains.forEach(o1-> {
            try {
                trainServiceApi.save(ConvertDomain.convertDomainTrains(o1));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });
     }

    @PostMapping("/api/clients/excel/import")
    public void importExcelToSqlClients(@RequestParam("file")MultipartFile file) throws IOException {
        List<com.domain.ClientRailway>allClients = clientServiceApi.FindAll();
        ClientsExcelImporter excelImporter = new ClientsExcelImporter();
        List<ClientRailway>importsClients = excelImporter.ImportFromExcel(file,ConvertDomain.convertDomainClientRailwayList(allClients));

        importsClients.forEach(o1-> {
            try {
                clientServiceApi.save(ConvertDomain.convertDomainClientRailway(o1));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("/sendemail")
    public ResponseEntity<Boolean>SendMessage(@RequestParam(name = "email")String email,
                                              @RequestParam(name = "subject")String subject,
                                              @RequestParam(name = "message")String message,
                                              @RequestParam(name = "file")MultipartFile file) throws IOException, MessagingException {
        if(mailSender.SendMessageWithAttchement(email,subject,message,file)) {
            logger.info("Send email with email - " + email + new Date());
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            logger.error("Not send email.");
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}
