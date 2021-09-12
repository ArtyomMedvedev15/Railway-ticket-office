package com.railwayticket.dao_api;

import com.domain.ClientRailway;
import com.domain.Stations;
import com.domain.Trains;
import com.railwayticket.dao_api.handler.StationsHandler;
import com.railwayticket.dao_api.handler.TypeTrainHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
@Mapper
 public interface TrainsMyBatisDaoImplementation extends TrainDaoApi {

    @Insert("<script>" +
            "<if test='id_train==null'>"+
            "INSERT INTO trains (name_train,type_train_id,departure_station_id,arrival_station_id,date_time_departure,date_time_arrival,available_ticket,total_ticket,price_ticket)" +
            "VALUES(#{name_train},${typeTrain.getId_type()},${departureStation.getId_station()},${arrivalStation.getId_station()},#{date_time_departure},#{date_time_arrival},#{available_ticket},#{total_ticket},#{price_ticket})" +
            "</if>"+
            "<if test='id_train!=null'>"+
            "INSERT INTO trains (id_train,name_train,type_train_id,departure_station_id,arrival_station_id,date_time_departure,date_time_arrival,available_ticket,total_ticket,price_ticket)" +
            "VALUES(#{id_train},#{name_train},${typeTrain.getId_type()},${departureStation.getId_station()},${arrivalStation.getId_station()},#{date_time_departure},#{date_time_arrival},#{available_ticket},#{total_ticket},#{price_ticket})"+
            "</if>"+
            "</script>")
    @Override
    boolean save(Trains trains);

    @Update("UPDATE trains SET name_train = #{name_train},type_train_id=${typeTrain.getId_type()},departure_station_id=${departureStation.getId_station()},arrival_station_id=${arrivalStation.getId_station()},date_time_departure=#{date_time_departure}," +
            "date_time_arrival=#{date_time_arrival},available_ticket=#{available_ticket},total_ticket=#{total_ticket},price_ticket=#{price_ticket} WHERE id_train=#{id_train}")
    @Override
    boolean update(Trains trains);

    @Delete("DELETE FROM trains WHERE id_train=#{id_train}")
    @Override
    boolean delete(Trains trains);


    @Select("SELECT * FROM trains WHERE id_train=#{id}")
    @Results(value = {
            @Result(column = "id_train",property = "id_train"),
            @Result(column = "name_train",property = "name_train"),
            @Result(column = "type_train_id",property = "typeTrain",typeHandler = TypeTrainHandler.class),
            @Result(column = "departure_station_id",property = "departureStation",typeHandler = StationsHandler.class),
            @Result(column = "arrival_station_id",property = "arrivalStation",typeHandler = StationsHandler.class),
            @Result(column = "date_time_departure",property = "date_time_departure"),
            @Result(column = "date_time_arrival",property = "date_time_arrival"),
            @Result(column = "available_ticket",property = "available_ticket"),
            @Result(column = "total_ticket",property = "total_ticket"),
            @Result(column = "price_ticket",property = "price_ticket")
    })
    @Override
    Trains getOneById(Long id);

    @Select("SELECT * FROM trains")
    @Results(value = {
            @Result(column = "id_train",property = "id_train"),
            @Result(column = "name_train",property = "name_train"),
            @Result(column = "type_train_id",property = "typeTrain",typeHandler = TypeTrainHandler.class),
            @Result(column = "departure_station_id",property = "departureStation",typeHandler = StationsHandler.class),
            @Result(column = "arrival_station_id",property = "arrivalStation",typeHandler = StationsHandler.class),
            @Result(column = "date_time_departure",property = "date_time_departure"),
            @Result(column = "date_time_arrival",property = "date_time_arrival"),
            @Result(column = "available_ticket",property = "available_ticket"),
            @Result(column = "total_ticket",property = "total_ticket"),
            @Result(column = "price_ticket",property = "price_ticket")
    })
    @Override
    List<Trains> FindAll();

    @Select("SELECT * FROM trains WHERE date_time_departure>=#{date_departure} and date_time_departure<=#{date_arrival} " +
            "and date_time_arrival>=#{date_departure} and date_time_arrival<=#{date_arrival} " +
            "and departure_station_id=${departure_station.getId_station()} and arrival_station_id=${arrival_station.getId_station()}")
    @Results(value = {
            @Result(column = "id_train",property = "id_train"),
            @Result(column = "name_train",property = "name_train"),
            @Result(column = "type_train_id",property = "typeTrain",typeHandler = TypeTrainHandler.class),
            @Result(column = "departure_station_id",property = "departureStation",typeHandler = StationsHandler.class),
            @Result(column = "arrival_station_id",property = "arrivalStation",typeHandler = StationsHandler.class),
            @Result(column = "date_time_departure",property = "date_time_departure"),
            @Result(column = "date_time_arrival",property = "date_time_arrival"),
            @Result(column = "available_ticket",property = "available_ticket"),
            @Result(column = "total_ticket",property = "total_ticket"),
            @Result(column = "price_ticket",property = "price_ticket")
    })
    @Override
    List<Trains> FindAllByDateDepartureArrivalStations(Date date_departure, Date date_arrival, Stations departure_station, Stations arrival_station);

    @Select("SELECT * FROM client_railway WHERE id_train = #{idTrain}")
    @Override
    List<ClientRailway> GetAllClientTrain(Long idTrain);
}
