package com.railwayticket.dao.dao_api;

import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TrainDaoApi extends BaseDaoApi<Trains> {
    List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure,Date date_arrival, Stations departure, Stations arrival);
}
