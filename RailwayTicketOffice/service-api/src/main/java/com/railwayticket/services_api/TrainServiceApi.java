package com.railwayticket.services_api;

import com.domain.Stations;
import com.domain.Trains;
import com.railwayticket.services_api.exception.TrainServiceException;

import java.util.List;

public interface TrainServiceApi extends BaseServiceApi<Trains> {
    List<Trains> FindAllByDateDepartureArrivalStations(java.sql.Date date_departure, java.sql.Date date_arrival, Stations departure, Stations arrival) throws TrainServiceException;
}
