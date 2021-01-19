package com.railwayticket.restclient.dao.mapper;

import com.railwayticket.restclient.domain.Stations;
import com.railwayticket.restclient.domain.Trains;
import com.railwayticket.restclient.domain.TypeTrain;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainMapper implements RowMapper<Trains> {
    @Override
    public Trains mapRow(ResultSet resultSet, int i) throws SQLException {
        Trains trains = new Trains();
        trains.setId_train(resultSet.getLong("id_train"));
        trains.setName_train(resultSet.getString("name_train"));
        trains.setTypeTrain(TypeTrain.getTypeById(resultSet.getLong("type_train_id")));
        trains.setDepartureStation(Stations.getStationById(resultSet.getLong("departure_station_id")));
        trains.setArrivalStation(Stations.getStationById(resultSet.getLong("arrival_station_id")));
        trains.setDate_time_departure(resultSet.getDate("date_time_departure"));
        trains.setDate_time_arrival(resultSet.getDate("date_time_arrival"));
        trains.setAvailable_ticket(resultSet.getInt("available_ticket"));
        trains.setTotal_ticket(resultSet.getInt("total_ticket"));
        trains.setPrice_ticket(resultSet.getFloat("price_ticket"));
        return trains;
    }
}
