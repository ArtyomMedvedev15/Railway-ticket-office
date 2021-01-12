package com.railwayticket.dao;

import com.railwayticket.dao.dao_api.TrainDaoApi;
import com.railwayticket.dao.mapper.TrainMapper;
import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.ClientServiceApiImplementation;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

public class TrainDaoImplementation implements TrainDaoApi {

    private JdbcTemplate databaseQuery;
    final static Logger logger = Logger.getLogger(TrainDaoImplementation.class);

    public TrainDaoImplementation(DataSource dataSource) {
        this.databaseQuery = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure,Date date_arrival, Stations departure, Stations arrival) {
        String sql_find_by_date_stations_train = "SELECT * FROM trains WHERE date_time_departure>=? and date_time_departure<=? " +
                "and departure_station_id=? and arrival_station_id=?";
        logger.info("Find all trains by date and stations. " + " Date: " + date_departure.toString() +
                " Departure station: " + departure.getNameStation() +
                " Arrival station: " + arrival.getNameStation()+
                " Time: " + new java.util.Date().toString());
        return databaseQuery.query(sql_find_by_date_stations_train,new TrainMapper(),date_departure,date_arrival,departure.getId_station(),arrival.getId_station());
    }

    @Override
    public boolean save(Trains trains) {
        String sql_save_train = "INSERT INTO trains(name_train,type_train_id,departure_station_id,arrival_station_id,date_time_departure,date_time_arrival,available_ticket,total_ticket,price_ticket)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        logger.info("Save new train to database. " + trains.toString() + " Time: " + new java.util.Date().toString());
        return databaseQuery.update(sql_save_train,trains.getName_train(),trains.getTypeTrain().getId_type(),trains.getDepartureStation().getId_station(),
                trains.getArrivalStation().getId_station(),trains.getDate_time_departure(),trains.getDate_time_arrival(),trains.getAvailable_ticket(),
                trains.getTotal_ticket(),trains.getPrice_ticket())>0;
    }

    @Override
    public boolean update(Trains trains) {
        String sql_update_train = "UPDATE trains SET name_train = ?,type_train_id=?,departure_station_id=?,arrival_station_id=?,date_time_departure=?," +
                "date_time_arrival=?,available_ticket=?,total_ticket=?,price_ticket=? WHERE id_train=?";
        logger.info("Update train. " + trains.toString() + " Time: " + new java.util.Date().toString());
        return databaseQuery.update(sql_update_train,trains.getName_train(),trains.getTypeTrain().getId_type(),trains.getDepartureStation().getId_station(),
                trains.getArrivalStation().getId_station(),trains.getDate_time_departure(),trains.getDate_time_arrival(),trains.getAvailable_ticket(),
                trains.getTotal_ticket(),trains.getPrice_ticket(),trains.getId_train())>0;
    }

    @Override
    public boolean delete(Trains trains) {
        String sql_delete_train = "DELETE FROM trains WHERE id_train=?";
        logger.info("Delete train. " + trains.toString() + " Time: " + new java.util.Date().toString());
        return databaseQuery.update(sql_delete_train,trains.getId_train())>0;
    }

    @Override
    public Trains getOneById(Long id) {
        String sql_getone_train = "SELECT * FROM trains WHERE id_train=?";
        logger.info("Get one train by id." + " id train: " + id + " Time: " + new java.util.Date().toString());
        return databaseQuery.queryForObject(sql_getone_train,new TrainMapper(),id);
    }

    @Override
    public List<Trains> FindAll() {
        String sql_find_all_train = "SELECT * FROM trains";
        logger.info("Get all trains." + " Size: " + databaseQuery.query(sql_find_all_train,new TrainMapper()).size() + " Time: " + new java.util.Date().toString());
        return databaseQuery.query(sql_find_all_train,new TrainMapper());
    }
}
