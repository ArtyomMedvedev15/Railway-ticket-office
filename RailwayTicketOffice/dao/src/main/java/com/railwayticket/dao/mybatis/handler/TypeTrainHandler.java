package com.railwayticket.dao.mybatis.handler;

import com.domain.Stations;
import com.domain.TypeTrain;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeTrainHandler implements TypeHandler<TypeTrain> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, TypeTrain typeTrain, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i,typeTrain.getId_type());
    }

    @Override
    public TypeTrain getResult(ResultSet resultSet, String s) throws SQLException {
        return TypeTrain.getTypeById(resultSet.getLong(s));
    }

    @Override
    public TypeTrain getResult(ResultSet resultSet, int i) throws SQLException {
        return TypeTrain.getTypeById(resultSet.getLong(i));
    }

    @Override
    public TypeTrain getResult(CallableStatement callableStatement, int i) throws SQLException {
        return TypeTrain.getTypeById(callableStatement.getLong(i));
    }
}
