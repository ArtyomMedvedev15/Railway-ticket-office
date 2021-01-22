package com.railwayticket.dao_api;





import com.domain.ClientRailway;
import com.domain.Stations;
import com.domain.Trains;

import java.sql.Date;
import java.util.List;

public interface TrainDaoApi extends BaseDaoApi<Trains> {
    List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure, Date date_arrival, Stations departure, Stations arrival);
    List<ClientRailway>GetAllClientTrain(Long idTrain);

}
