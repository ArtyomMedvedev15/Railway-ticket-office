package com.railwayticket.dao_api.handler;

import com.domain.Stations;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationsHandler implements TypeHandler<Stations> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Stations stations, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, stations.getId_station());

    }

    @Override
    public Stations getResult(ResultSet resultSet, String s) throws SQLException {
        return Stations.getStationById(resultSet.getLong(s));
    }

    @Override
    public Stations getResult(ResultSet resultSet, int i) throws SQLException {
        return Stations.getStationById(resultSet.getLong(i));
    }

    @Override
    public Stations getResult(CallableStatement callableStatement, int i) throws SQLException {
        return Stations.getStationById(callableStatement.getLong(i));
    }

}
