package com.railwayticket.service.servic_api;

import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.exception.TrainServiceException;

import java.sql.Date;
import java.util.Optional;

public interface TrainServiceApi extends BaseServiceApi<Trains> {
    Optional<Trains>FindAllByDateDepartureArrivalStations(Date date, Stations departure,Stations arrival) throws TrainServiceException;
}
