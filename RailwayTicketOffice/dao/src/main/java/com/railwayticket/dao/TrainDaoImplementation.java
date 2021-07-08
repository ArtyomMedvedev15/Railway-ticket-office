package com.railwayticket.dao;



import com.domain.ClientRailway;
import com.domain.Stations;
import com.domain.Trains;
import com.railwayticket.dao.mapper.ClientMapper;
import com.railwayticket.dao.mapper.TrainMapper;


import com.railwayticket.dao_api.TrainDaoApi;
import com.railwayticket.dao_api.sql_annotation.SqlQuery;
import com.railwayticket.dao_api.sql_annotation.SqlQueryImpl;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

public class TrainDaoImplementation extends SqlQueryImpl implements TrainDaoApi {

    private JdbcTemplate databaseQuery;
    final static Logger logger = Logger.getLogger(TrainDaoImplementation.class);

    @SqlQuery(sqlfilename = "sql/train/findtrainbydatesstation.sql")
    public String SQL_FINDALLTRAINBY_DATE_STATION_TRAIN;

    @SqlQuery(sqlfilename = "sql/train/getalltrainclient.sql")
    public String SQL_GETALLCLIENT_TRAIN;

    @SqlQuery(sqlfilename = "sql/train/inserttrain.sql")
    public String SQL_INSERT_TRAIN;

    @SqlQuery(sqlfilename = "sql/train/updatetrain.sql")
    public String SQL_UPDATE_TRAIN;

    @SqlQuery(sqlfilename = "sql/train/deletetrain.sql")
    public String SQL_DELETE_TRAIN;

    @SqlQuery(sqlfilename = "sql/train/getonetrain.sql")
    public String SQL_GETONE_TRAIN;

    @SqlQuery(sqlfilename = "sql/train/findalltrain.sql")
    public String SQL_FINDALL_TRAIN;

    public TrainDaoImplementation(DataSource dataSource) {
        this.databaseQuery = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure, Date date_arrival, Stations departure, Stations arrival) {

        logger.info("Find all trains by date and stations. " + " Date: " + date_departure.toString() +
                " Departure station: " + departure.getNameStation() +
                " Arrival station: " + arrival.getNameStation()+
                " Time: " + new java.util.Date().toString());
        return databaseQuery.query(SQL_FINDALLTRAINBY_DATE_STATION_TRAIN,new TrainMapper(),date_departure,date_arrival,date_departure,date_arrival,departure.getId_station(),arrival.getId_station());
    }

    @Override
    public List<ClientRailway> GetAllClientTrain(Long idTrain) {
         logger.info("Get all client by train id." + " Train id: " + idTrain + " Time: " + new java.util.Date().toString());
        return databaseQuery.query(SQL_GETALLCLIENT_TRAIN,new ClientMapper(),idTrain);
    }

    @Override
    public boolean save(Trains trains) {
        logger.info("Save new train to database. " + trains.toString() + " Time: " + new java.util.Date().toString());
        return databaseQuery.update(SQL_INSERT_TRAIN,trains.getName_train(),trains.getTypeTrain().getId_type(),trains.getDepartureStation().getId_station(),
                trains.getArrivalStation().getId_station(),trains.getDate_time_departure(),trains.getDate_time_arrival(),trains.getAvailable_ticket(),
                trains.getTotal_ticket(),trains.getPrice_ticket())>0;
    }

    @Override
    public boolean update(Trains trains) {
        logger.info("Update train. " + trains.toString() + " Time: " + new java.util.Date().toString());
        return databaseQuery.update(SQL_UPDATE_TRAIN,trains.getName_train(),trains.getTypeTrain().getId_type(),trains.getDepartureStation().getId_station(),
                trains.getArrivalStation().getId_station(),trains.getDate_time_departure(),trains.getDate_time_arrival(),trains.getAvailable_ticket(),
                trains.getTotal_ticket(),trains.getPrice_ticket(),trains.getId_train())>0;
    }

    @Override
    public boolean delete(Trains trains) {
         logger.info("Delete train. " + trains.toString() + " Time: " + new java.util.Date().toString());
        return databaseQuery.update(SQL_DELETE_TRAIN,trains.getId_train())>0;
    }

    @Override
    public Trains getOneById(Long id) {
        logger.info("Get one train by id." + " id train: " + id + " Time: " + new java.util.Date().toString());
        try {
            return databaseQuery.queryForObject(SQL_GETONE_TRAIN, new TrainMapper(), id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Trains> FindAll() {
         logger.info("Get all trains." + " Size: " + databaseQuery.query(SQL_FINDALL_TRAIN,new TrainMapper()).size() + " Time: " + new java.util.Date().toString());
        return databaseQuery.query(SQL_FINDALL_TRAIN,new TrainMapper());
    }


}
