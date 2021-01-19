package com.railwayticket.restclient.service.service_api;

import com.railwayticket.restclient.domain.Stations;
import com.railwayticket.restclient.domain.Trains;
import com.railwayticket.restclient.service.exception.TrainServiceException;

import java.util.List;

public interface TrainServiceApi extends BaseServiceApi<Trains> {
    List<Trains> FindAllByDateDepartureArrivalStations(java.sql.Date date_departure, java.sql.Date date_arrival, Stations departure, Stations arrival) throws TrainServiceException;
}
