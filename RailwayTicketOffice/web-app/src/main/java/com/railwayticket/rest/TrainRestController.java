package com.railwayticket.rest;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.exception.ClientServiceException;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.exception.TrainServiceException;
import com.railwayticket.service.servic_api.TrainServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/train/")
public class TrainRestController {

    @Autowired
    private TrainServiceApi trainServiceApi;

    @RequestMapping(value = "{id}",method = RequestMethod.GET,produces  = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trains> TrainById(@PathVariable("id") Long id_client) throws ServiceException {

        if(id_client==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Trains trains = trainServiceApi.getOneById(id_client);

        if(trains==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(trains,HttpStatus.OK);
    }

    @RequestMapping(value = "/saveTrain",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trains>saveTrain(@RequestBody Trains trains) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(trains==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        trainServiceApi.save(trains);

        return new ResponseEntity<>(trains,httpHeaders,HttpStatus.CREATED);

    }

    @RequestMapping(value = "/updateTrain",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trains>updateTrain(@RequestBody Trains trains, UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(trains==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        trainServiceApi.update(trains);

        return new ResponseEntity<>(trains,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteTrain/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trains>deleteTrains(@PathVariable("id")Long id_train) throws ServiceException {
        Trains trainsDelete = trainServiceApi.getOneById(id_train);

        if(trainsDelete==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        trainServiceApi.delete(trainsDelete);

        return new ResponseEntity<>(trainsDelete,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/allTrain",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Trains>>allTrain(){
        List<Trains>allTrain= trainServiceApi.FindAll();

        if(allTrain.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allTrain,HttpStatus.OK);
    }

    @RequestMapping(value = "/findtrainbydates",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Trains>>FindAllTrainsByDatesAndStations(@RequestParam(name = "departure_date")String date_departure,
                                                                      @RequestParam(name = "arrival_date")String arrival_date,
                                                                      @RequestParam(name = "departure_station_find")String departure_station_find,
                                                                      @RequestParam(name = "arrival_station_find")String arrival_station_find) throws ClientServiceException, ParseException, TrainServiceException {

        Date date_departure_find=new SimpleDateFormat("yyyy-MM-dd").parse(date_departure);
        Date date_arrival_find=new SimpleDateFormat("yyyy-MM-dd").parse(arrival_date);


        List<Trains>allTrain = trainServiceApi.FindAllByDateDepartureArrivalStations
                (new java.sql.Date(date_departure_find.getTime()),new java.sql.Date(date_arrival_find.getTime()),
                        Stations.valueOf(departure_station_find.toUpperCase()),Stations.valueOf(arrival_station_find.toUpperCase()));

        if(allTrain.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allTrain,HttpStatus.OK);
    }



}
