package com.railwayticket.service.servic_api;

import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.exception.TrainServiceException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TrainServiceApi extends BaseServiceApi<Trains> {
    List<Trains> FindAllByDateDepartureArrivalStations(java.sql.Date date_departure,java.sql.Date date_arrival, Stations departure, Stations arrival) throws TrainServiceException;
}
