package com.railwayticket.controller;

import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.domain.TypeTrain;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.exception.TrainServiceException;
import com.railwayticket.service.servic_api.TrainServiceApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class TrainController {
    final static Logger logger = Logger.getLogger(TrainController.class);

    @Qualifier("TrainServiceRest")
    @Autowired
    private TrainServiceApi trainServiceApiRest;

    @GetMapping("/listTrain")
    public String listTrainPage(Model model){
        List<Trains>TrainList = trainServiceApiRest.FindAll();
        model.addAttribute("TrainList",TrainList);
        logger.info("All train list page load." + " List train size: " + TrainList.size() + " Time: " + new Date().toString());
        return "listtrain";
    }

    @GetMapping("/oneTrain/{id}")
    public String oneTrainPage(@PathVariable(name = "id")String id, Model model) throws ServiceException {
        model.addAttribute("oneTrain", trainServiceApiRest.getOneById(Long.valueOf(id)));
        logger.info("Get one train by id. " + " Train: " + trainServiceApiRest.getOneById(Long.valueOf(id)) + " Time: " + new Date().toString());
        return "onetrain";
    }

    @GetMapping("/EditTrainInfo/{id}")
    public String updateTrain(@PathVariable(name = "id")String id,Model model) throws ServiceException {
        model.addAttribute("id",id);
        model.addAttribute("editTrain", trainServiceApiRest.getOneById(Long.valueOf(id)));
        logger.info("Update train. " + " Train: " + trainServiceApiRest.getOneById(Long.valueOf(id)) + " Time: " + new Date().toString());
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

        Date date_departure=new SimpleDateFormat("yyyy-MM-dd").parse(datetime_dep);
        Date date_arrival=new SimpleDateFormat("yyyy-MM-dd").parse(datetime_arr);

        Trains save_train = new Trains();
        save_train.setName_train(name_train);
        save_train.setDepartureStation(Stations.valueOf(departure_station.toUpperCase()));
        save_train.setArrivalStation(Stations.valueOf(arrival_station.toUpperCase()));
        save_train.setTypeTrain(TypeTrain.valueOf(type_train.toUpperCase()));
        save_train.setDate_time_departure(new java.sql.Date(date_departure.getTime()));
        save_train.setDate_time_arrival(new java.sql.Date(date_arrival.getTime()));
        save_train.setTotal_ticket(Integer.valueOf(total_ticket));
        save_train.setPrice_ticket(Float.parseFloat(price.replace(',','.')));
        save_train.setAvailable_ticket(Integer.valueOf(available_ticket));

        logger.info("Save new train. " + " Train: " + save_train.toString() + " Time: " + new Date().toString());

        trainServiceApiRest.save(save_train);

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
        Date date_departure=new SimpleDateFormat("yyyy-MM-dd").parse(datetime_dep_edit);
        Date date_arrival=new SimpleDateFormat("yyyy-MM-dd").parse(datetime_arr_edit);

        Trains trains_update = trainServiceApiRest.getOneById(Long.valueOf(id));
        trains_update.setName_train(name_train_edit);
        trains_update.setDepartureStation(Stations.valueOf(departure_station_edit.toUpperCase(Locale.ROOT)));
        trains_update.setArrivalStation(Stations.valueOf(arrival_station_edit.toUpperCase(Locale.ROOT)));
        trains_update.setTypeTrain(TypeTrain.valueOf(type_train_edit.toUpperCase(Locale.ROOT)));
        trains_update.setDate_time_departure(new java.sql.Date(date_departure.getTime()));
        trains_update.setDate_time_arrival(new java.sql.Date(date_arrival.getTime()));
        trains_update.setTotal_ticket(Integer.valueOf(total_ticket_edit));
        trains_update.setPrice_ticket(Float.valueOf(price_edit.replace(',','.')));

        logger.info("Update train. " + " Train: " + trains_update.toString() + " Time: " + new Date().toString());

        trainServiceApiRest.update(trains_update);

        return "redirect:/listTrain";

    }

    @GetMapping("/DeleteTrain/{id}")
    public String deleteTrain(@PathVariable("id")String id) throws ServiceException {
        Trains delete_train = trainServiceApiRest.getOneById(Long.valueOf(id));

        logger.info("Delete train. " + " Train: " + delete_train.toString() + " Time: " + new Date().toString());

        trainServiceApiRest.delete(delete_train);

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

        List<Trains>result_find = trainServiceApiRest.FindAllByDateDepartureArrivalStations(new java.sql.Date(date_departure_find.getTime()),
                new java.sql.Date(date_arrival_find.getTime()),
                Stations.valueOf(departure_station.toUpperCase()),Stations.valueOf(arrival_station.toUpperCase()));

        logger.info("Find train by dates and stations. " + " Date departure: " + date_departure_find.toString() +
                " Date arrival: " + date_arrival_find.toString() + " Departure station: " + Stations.valueOf(departure_station.toUpperCase())
        +" Arrival station: " + Stations.valueOf(arrival_station.toUpperCase()).getNameStation());

        model.addAttribute("ResultList",result_find);

        return "resultfindtrain";
    }

}
