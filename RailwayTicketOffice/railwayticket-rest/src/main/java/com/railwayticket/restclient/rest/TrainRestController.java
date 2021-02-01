package com.railwayticket.restclient.rest;


import com.domain.Stations;
import com.domain.Trains;
import com.railwayticket.services_api.TrainServiceApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
import com.railwayticket.services_api.exception.TrainServiceException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/train/")
@Api(value = "Trains requests",description = "Operations for work with trains in railway tickets ")
public class TrainRestController {

    @Autowired
    private TrainServiceApi trainServiceApi;

    @RequestMapping(value = "{id}",method = RequestMethod.GET,produces  = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find train by id.",notes = "Allows you to get a single train by ID",
            response = Trains.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully get train by id."),
            @ApiResponse(code = 400,message = "Error id train equal null"),
            @ApiResponse(code = 404,message = "Train was not found by this id")
    })
    public ResponseEntity<Trains> TrainById(@ApiParam(name = "id",value = "ID value for find train by id.",required = true)@PathVariable("id") Long id_client) throws ServiceException {

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
    @ApiOperation(value = "Save new train.",notes = "Allows you to save new train to database.",
            response = Trains.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Successfully save train."),
            @ApiResponse(code = 400,message = "Error train for save equal null"),
    })
    public ResponseEntity<Trains>saveTrain(@ApiParam(name = "Train Save",value = "Train model for save new train with full field.",required = true)@RequestBody Trains trains) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(trains==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        trainServiceApi.save(trains);

        return new ResponseEntity<>(trains,httpHeaders,HttpStatus.CREATED);

    }

    @RequestMapping(value = "/updateTrain",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update train.",notes = "Allows you to update train.",
            response = Trains.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully update train."),
            @ApiResponse(code = 400,message = "Error train for update equal null"),
    })
    public ResponseEntity<Trains>updateTrain(@ApiParam(name = "Train update",value = "Train model for update train info with full field.",required = true)@RequestBody Trains trains, UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(trains==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        trainServiceApi.update(trains);

        return new ResponseEntity<>(trains,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteTrain/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Delete train.",notes = "Allows you to delete train by id.",
            response = Trains.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Successfully delete train."),
            @ApiResponse(code = 404,message = "Error train for delete equal null"),
    })
    public ResponseEntity<Trains>deleteTrains(@ApiParam(name = "id",value = "ID value for delete train.",required = true)@PathVariable("id")Long id_train) throws ServiceException {
        Trains trainsDelete = trainServiceApi.getOneById(id_train);

        if(trainsDelete==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        trainServiceApi.delete(trainsDelete);

        return new ResponseEntity<>(trainsDelete,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/allTrain",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find all train.",notes = "Allows you to get all trains.",
            response = Trains.class,responseContainer = "List",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully find all train."),
            @ApiResponse(code = 404,message = "List of train was empty"),
    })
    public ResponseEntity<List<Trains>>allTrain(){
        List<Trains>allTrain= trainServiceApi.FindAll();

        if(allTrain.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allTrain,HttpStatus.OK);
    }

    @RequestMapping(value = "/findtrainbydates",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find all train by dates and stations.",notes = "Allows you to get all train by dates and stations.",
            response = Trains.class,responseContainer = "List",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully find all train by dates and stations."),
            @ApiResponse(code = 404,message = "List of train was empty"),
    })
    public ResponseEntity<List<Trains>>FindAllTrainsByDatesAndStations(@ApiParam(defaultValue = "2021-01-28",name = "departure_date",value = "Date when train departure",required = true)@RequestParam(name = "departure_date")String date_departure,
                                                                       @ApiParam(defaultValue = "2021-02-02",name = "arrival_date",value = "Date when train arrival",required = true)@RequestParam(name = "arrival_date")String arrival_date,
                                                                       @ApiParam(defaultValue = "Brest",name = "departure_station_find",value = "The station where the train starts from",required = true)@RequestParam(name = "departure_station_find")String departure_station_find,
                                                                       @ApiParam(defaultValue = "Minsk",name = "arrival_station_find",value = "The station where the train arrives",required = true)@RequestParam(name = "arrival_station_find")String arrival_station_find) throws ClientServiceException, ParseException, TrainServiceException {

        Date date_departure_find=new SimpleDateFormat("yyyy-MM-dd").parse(date_departure);
        Date date_arrival_find=new SimpleDateFormat("yyyy-MM-dd").parse(arrival_date);


        List<Trains>allTrain = trainServiceApi.FindAllByDateDepartureArrivalStations
                (new java.sql.Date(date_departure_find.getTime()),new java.sql.Date(date_arrival_find.getTime()),
                        Stations.valueOf(departure_station_find.toUpperCase()),Stations.valueOf(arrival_station_find.toUpperCase()));

        if(allTrain.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allTrain,HttpStatus.OK);
    }



}
