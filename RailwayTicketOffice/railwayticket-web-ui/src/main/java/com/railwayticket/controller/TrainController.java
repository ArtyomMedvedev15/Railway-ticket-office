package com.railwayticket.controller;


import com.domain.Stations;
import com.railwayticket.services_api.TrainServiceApi;
import com.railwayticket.services_api.exception.ServiceException;
import com.railwayticket.services_api.exception.TrainServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class TrainController {
    final static Logger logger = Logger.getLogger(TrainController.class);


    @Autowired
    @Qualifier("TrainServiceRestImpl")
    private TrainServiceApi trainServiceApiRest;

    @Autowired
    private com.rest.TrainRestControllerApi trainRestApi;

    @GetMapping("/listTrain")
    public String listTrainPage(Model model) throws ParseException {
        List<com.rest.domains.Trains>TrainList = trainRestApi.allTrainUsingGET();
        model.addAttribute("TrainList",TrainList);
        logger.info("All train list page load." + " List train size: " + TrainList.size() + " Time: " + new Date().toString());
        return "listtrain";
    }


    @GetMapping("/oneTrain/{id}")
    public String oneTrainPage(@PathVariable(name = "id")String id, Model model) throws ServiceException {
        model.addAttribute("oneTrain", trainRestApi.trainByIdUsingGET(Long.valueOf(id)));
        logger.info("Get one train by id. " + " Train: " + trainRestApi.trainByIdUsingGET(Long.valueOf(id)) + " Time: " + new Date().toString());
        return "onetrain";
    }

    @GetMapping("/EditTrainInfo/{id}")
    public String updateTrain(@PathVariable(name = "id")String id,Model model) throws ServiceException {
        model.addAttribute("id",id);
        model.addAttribute("editTrain", trainRestApi.trainByIdUsingGET(Long.valueOf(id)));
        logger.info("Update train. " + " Train: " + trainRestApi.trainByIdUsingGET(Long.valueOf(id)) + " Time: " + new Date().toString());
        return "editTrain";
    }

    @PostMapping("/SaveNewTrain")
    public String saveTrain( @RequestParam("name_train")String name_train,
                             @RequestParam("departure_station")String departure_station,
                             @RequestParam("arrival_station")String arrival_station,
                             @RequestParam("type_train")String type_train,
                             @RequestParam("datetime_dep")String datetime_dep,
                             @RequestParam("datetime_arr")String datetime_arr,
                             @RequestParam("total_ticket")String total_ticket,
                             @RequestParam("available_ticket")String available_ticket,
                             @RequestParam("price")String price) throws ServiceException, ParseException{

        final DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        com.rest.domains.Trains save_train = new com.rest.domains.Trains();
        save_train.setNameTrain(name_train);
        save_train.setDepartureStation(com.rest.domains.Trains.DepartureStationEnum.valueOf(departure_station.toUpperCase()));
        save_train.setArrivalStation(com.rest.domains.Trains.ArrivalStationEnum.valueOf(arrival_station.toUpperCase()));
        save_train.setTypeTrain(com.rest.domains.Trains.TypeTrainEnum.valueOf(type_train.toUpperCase()));
        save_train.setDateTimeDeparture(datetime_dep);
        save_train.setDateTimeArrival(datetime_arr);
        save_train.setTotalTicket(Integer.valueOf(total_ticket));
        save_train.setPriceTicket(Float.parseFloat(price.replace(',','.')));
        save_train.setAvailableTicket(Integer.valueOf(available_ticket));

        logger.info("Save new train. " + " Train: " + save_train.toString() + " Time: " + new Date().toString());

        trainRestApi.saveTrainUsingPOST(save_train);

        return "redirect:/listTrain";
    }

    @PostMapping("/EditTrainInfo/{id}")
    public String updateTrain(@PathVariable(name = "id")String id,
                              @RequestParam("name_train_edit")String name_train_edit,
                              @RequestParam("departure_station_edit")String departure_station_edit,
                              @RequestParam("arrival_station_edit")String arrival_station_edit,
                              @RequestParam("type_train_edit")String type_train_edit,
                              @RequestParam("datetime_dep_edit")String datetime_dep_edit,
                              @RequestParam("datetime_arr_edit")String datetime_arr_edit,
                              @RequestParam("total_ticket_edit")String total_ticket_edit,
                              @RequestParam("price_edit")String price_edit) throws ServiceException, ParseException {
        final DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        com.rest.domains.Trains trains_update = trainRestApi.trainByIdUsingGET(Long.valueOf(id));
        trains_update.setNameTrain(name_train_edit);
        trains_update.setDepartureStation(com.rest.domains.Trains.DepartureStationEnum.valueOf(departure_station_edit));
        trains_update.setArrivalStation(com.rest.domains.Trains.ArrivalStationEnum.valueOf(arrival_station_edit));
        trains_update.setTypeTrain(com.rest.domains.Trains.TypeTrainEnum.valueOf(type_train_edit));
        trains_update.setDateTimeDeparture(datetime_dep_edit);
        trains_update.setDateTimeArrival(datetime_arr_edit);
        trains_update.setTotalTicket(Integer.valueOf(total_ticket_edit));
        trains_update.setPriceTicket(Float.parseFloat(price_edit.replace(',','.')));

        logger.info("Update train. " + " Train: " + trains_update.toString() + " Time: " + new Date().toString());

        trainRestApi.updateTrainUsingPOST(trains_update);

        return "redirect:/listTrain";

    }

    @GetMapping("/DeleteTrain/{id}")
    public String deleteTrain(@PathVariable("id")String id) throws ServiceException {
        com.rest.domains.Trains delete_train = trainRestApi.trainByIdUsingGET(Long.valueOf(id));

        logger.info("Delete train. " + " Train: " + delete_train.toString() + " Time: " + new Date().toString());

        trainRestApi.deleteTrainsUsingGET(delete_train.getIdTrain());

        return "redirect:/listTrain";
    }

    @PostMapping("/FindTrain")
    public String findTrainByDate(@RequestParam(name = "departure_date")String date_departure,
                                  @RequestParam(name = "arrival_date")String arrival_date,
                                  @RequestParam(name = "departure_station_find")String departure_station,
                                  @RequestParam(name = "arrival_station_find")String arrival_station,
                                  Model model) throws ParseException, TrainServiceException {
        Date date_departure_find=new SimpleDateFormat("yyyy-MM-dd").parse(date_departure);
        Date date_arrival_find=new SimpleDateFormat("yyyy-MM-dd").parse(arrival_date);
        List<com.rest.domains.Trains> result_find;
        try {
           result_find = trainRestApi.findAllTrainsByDatesAndStationsUsingPOST
                    (arrival_date, arrival_station.toUpperCase(), date_departure, departure_station.toUpperCase());
        }catch (HttpClientErrorException httpClientErrorException){
            result_find = Collections.emptyList();
        }
        logger.info("Find train by dates and stations. " + " Date departure: " + date_departure_find.toString() +
                " Date arrival: " + date_arrival_find.toString() + " Departure station: " + Stations.valueOf(departure_station.toUpperCase())
        +" Arrival station: " + Stations.valueOf(arrival_station.toUpperCase()).getNameStation());

        model.addAttribute("ResultList",result_find);

        return "resultfindtrain";
    }

}
