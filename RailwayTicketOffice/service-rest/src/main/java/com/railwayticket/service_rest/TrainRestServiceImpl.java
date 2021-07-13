package com.railwayticket.service_rest;


import com.domain.Stations;
import com.domain.Trains;
import com.railwayticket.services_api.TrainServiceApi;
import com.railwayticket.services_api.exception.ServiceException;
import com.railwayticket.services_api.exception.TrainServiceException;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TrainRestServiceImpl implements TrainServiceApi {

    @Autowired
    private RestTemplate restTemplate;

    final static Logger logger = Logger.getLogger(TrainRestServiceImpl.class);

    private String base_url = "http://localhost:8181/api/train/";

    @Override
    public boolean save(Trains trains) throws ServiceException {
        ResponseEntity<String> response = restTemplate.postForEntity( base_url+"saveTrain", trains , String.class );
        if(response.getStatusCode().toString().equals("201 CREATED")){
            logger.info("Save new train. " + "Train: " + trains.toString() + "With status Created");
            return true;
        }else{
            logger.error("Save new train failed. " + "Train: null" + "With status Bad request" + response.getStatusCode().toString());
            return false;
        }
    }

    @Override
    public boolean update(Trains trains) throws ServiceException {
        ResponseEntity<String> response_request_update = restTemplate.postForEntity( "http://localhost:8181/api/train/updateTrain", trains , String.class );
        if(response_request_update.getStatusCode().toString().equals("200 OK")){
            logger.info("Update train. " + "Train: " + trains.toString() + "With status Ok");
            return true;
        }else{
            logger.error("Update train failed. " + "Train: null" + "With status Bad request");
            return false;
        }
    }

    @Override
    public boolean delete(Trains trains) throws ServiceException {
        ResponseEntity<String> result_request_delete = restTemplate.getForEntity(base_url+"deleteTrain/"+trains.getId_train(),String.class);

        if(result_request_delete.getStatusCode().toString().equals("204 NO_CONTENT")){
            logger.info("Delete train. " + "Train: " + trains.toString() + "With status No content");
            return true;
        }else{
            logger.error("Delete train failed. " + "Train: null" + "With status Bad request");
            return false;
        }
    }

    @Override
    public Trains getOneById(Long id) throws ServiceException {
        Trains trains = restTemplate.getForObject(base_url+id,Trains.class);
        if (trains != null) {
            logger.info("Get one client. " + "Train: " + trains.toString() + "With status OK");
            return trains;
        }else{
            logger.error("Get one train failed. " + "Train: null" + "With status Bad request");
            return null;
        }
    }

    @Override
    public List<Trains> FindAll() {
        List<Trains> trainAllClient =  Arrays.asList(restTemplate.getForObject(base_url+"allTrain",Trains[].class).clone());

    logger.info("Get all train. " + " List size: " + trainAllClient.size() + " With status Ok");
    return trainAllClient;

    }

    @Override
    public List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure, Date date_arrival, Stations departure, Stations arrival) throws TrainServiceException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("departure_date", date_departure.toString());
        map.add("arrival_date", date_arrival.toString());
        map.add("departure_station_find", departure.getNameStation().toUpperCase(Locale.ROOT));
        map.add("arrival_station_find", arrival.getNameStation().toUpperCase(Locale.ROOT));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Trains[]> response = restTemplate.postForEntity( base_url+"findtrainbydates", request , Trains[].class );

        List<Trains>allTrainByDatesStations = Arrays.asList(Objects.requireNonNull(response.getBody()));

        if (allTrainByDatesStations!=null && (date_departure!=null && date_arrival!=null)){
            logger.info("Get all train by dates and stations. " + " List size: " + allTrainByDatesStations.size() + " With status Ok");
            return allTrainByDatesStations;
        }else{
            logger.info("Get all train by dates and stations faild. " + " List size: 0" + " With status not found");
            return null;
        }
    }

    @Override
    public void ImportExcel(MultipartFile file) {

        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(convert(file)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(bodyMap, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity( base_url+"excel/import", request , Void.class );

        logger.info("Import excel to database");
    }

    public static File convert(MultipartFile file)
    {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convFile;
    }
}
