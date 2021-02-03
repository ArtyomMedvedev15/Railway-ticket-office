package com.railwayticket.restclient.util;

import com.domain.Stations;
import com.domain.TypeTrain;
import com.railwayticket.restclient.domains.ClientRailway;
import com.railwayticket.restclient.domains.Trains;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConvertDomain {
    public static List<ClientRailway> convertDomainClientRailwayList(List<com.domain.ClientRailway>sourcelist_domains){
        List<ClientRailway>converted_domains = new ArrayList<>();
        ClientRailway clientRailway;
        for (com.domain.ClientRailway client:sourcelist_domains
             ) {
           clientRailway = new ClientRailway();
           clientRailway.setIdClient(client.getId_client());
           clientRailway.setNameClient(client.getName_client());
           clientRailway.setSonameClient(client.getSoname_client());
           clientRailway.setPhoneClient(client.getPhone_client());
           clientRailway.setDatePurchase(client.getDate_purchase().toString());
           clientRailway.setIdTrain(client.getId_train());

           converted_domains.add(clientRailway);
        }
        return converted_domains;
    }
    public static List<Trains> convertDomainTrainsList(List<com.domain.Trains>sourcelist_domains){
        List<Trains>converted_domains = new ArrayList<>();
        Trains trains;
        for (com.domain.Trains train:sourcelist_domains
        ) {
            trains = new Trains();
            trains.setIdTrain(train.getId_train());
            trains.setNameTrain(train.getName_train());
            trains.setTypeTrain(Trains.TypeTrainEnum.valueOf(train.getTypeTrain().getNameType().toUpperCase()));
            trains.setArrivalStation(Trains.ArrivalStationEnum.valueOf(train.getArrivalStation().getNameStation().toUpperCase()));
            trains.setDepartureStation(Trains.DepartureStationEnum.valueOf(train.getDepartureStation().getNameStation().toUpperCase()));
            trains.setDateTimeArrival(train.getDate_time_arrival().toString());
            trains.setDateTimeDeparture(train.getDate_time_departure().toString());
            trains.setAvailableTicket(train.getAvailable_ticket());
            trains.setTotalTicket(train.getTotal_ticket());
            trains.setPriceTicket(train.getPrice_ticket());
            converted_domains.add(trains);
        }
        return converted_domains;
    }

    public static Trains convertDomainTrains(com.domain.Trains train){

        Trains convertedTrain = new Trains();

        convertedTrain.setIdTrain(train.getId_train());
        convertedTrain.setNameTrain(train.getName_train());
        convertedTrain.setTypeTrain(Trains.TypeTrainEnum.valueOf(train.getTypeTrain().getNameType().toUpperCase()));
        convertedTrain.setArrivalStation(Trains.ArrivalStationEnum.valueOf(train.getArrivalStation().getNameStation().toUpperCase()));
        convertedTrain.setDepartureStation(Trains.DepartureStationEnum.valueOf(train.getDepartureStation().getNameStation().toUpperCase()));
        convertedTrain.setDateTimeArrival(train.getDate_time_arrival().toString());
        convertedTrain.setDateTimeDeparture(train.getDate_time_departure().toString());
        convertedTrain.setAvailableTicket(train.getAvailable_ticket());
        convertedTrain.setTotalTicket(train.getTotal_ticket());
        convertedTrain.setPriceTicket(train.getPrice_ticket());

        return convertedTrain;
    }

    public static com.domain.Trains convertDomainTrains(Trains train){

        com.domain.Trains convertedTrain = new com.domain.Trains();
        java.util.Date departure_date_time = null;
        java.util.Date arrival_date_time = null;
        try {
             departure_date_time=new SimpleDateFormat("yyyy-MM-dd").parse(train.getDateTimeArrival());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        try {
             arrival_date_time=new SimpleDateFormat("yyyy-MM-dd").parse(train.getDateTimeDeparture());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        convertedTrain.setId_train(train.getIdTrain());
        convertedTrain.setName_train(train.getNameTrain());
        convertedTrain.setTypeTrain(TypeTrain.valueOf(train.getTypeTrain().getValue()));
        convertedTrain.setArrivalStation(Stations.valueOf(train.getArrivalStation().getValue()));
        convertedTrain.setDepartureStation(Stations.valueOf(train.getDepartureStation().getValue()));
        convertedTrain.setDate_time_arrival(new java.sql.Date(arrival_date_time.getTime()));
        convertedTrain.setDate_time_departure(new java.sql.Date(departure_date_time.getTime()));
        convertedTrain.setAvailable_ticket(train.getAvailableTicket());
        convertedTrain.setTotal_ticket(train.getTotalTicket());
        convertedTrain.setPrice_ticket(train.getPriceTicket());

        return convertedTrain;
    }


    public static ClientRailway convertDomainClientRailway(com.domain.ClientRailway client){
        ClientRailway clientRailway = new ClientRailway();

        clientRailway.setIdClient(client.getId_client());
        clientRailway.setNameClient(client.getName_client());
        clientRailway.setSonameClient(client.getSoname_client());
        clientRailway.setPhoneClient(client.getPhone_client());
        clientRailway.setDatePurchase(client.getDate_purchase().toString());
        clientRailway.setIdTrain(client.getId_train());

        return clientRailway;
    }

    public static com.domain.ClientRailway convertDomainClientRailway(ClientRailway client){
        com.domain.ClientRailway clientRailway = new com.domain.ClientRailway();
        java.util.Date date_purchapse = null;
        try {
            date_purchapse=new SimpleDateFormat("yyyy-MM-dd").parse(client.getDatePurchase());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        clientRailway.setId_client(client.getIdClient());
        clientRailway.setName_client(client.getNameClient());
        clientRailway.setSoname_client(client.getSonameClient());
        clientRailway.setPhone_client(client.getPhoneClient());
        clientRailway.setDate_purchase(new java.sql.Date(date_purchapse.getTime()));
        clientRailway.setId_train(client.getIdTrain());

        return clientRailway;
    }
}
