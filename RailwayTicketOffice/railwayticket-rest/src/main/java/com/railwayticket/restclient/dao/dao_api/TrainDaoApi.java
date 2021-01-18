package com.railwayticket.restclient.dao.dao_api;

import com.railwayticket.restclient.domain.ClientRailway;
import com.railwayticket.restclient.domain.Stations;
import com.railwayticket.restclient.domain.Trains;

import java.sql.Date;
import java.util.List;

public interface TrainDaoApi extends BaseDaoApi<Trains> {
    List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure,Date date_arrival, Stations departure, Stations arrival);
    List<ClientRailway>GetAllClientTrain(Long idTrain);
}
