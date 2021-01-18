package com.railwayticket.restclient.dao.mapper;

import com.railwayticket.restclient.domain.ClientRailway;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<ClientRailway> {
    @Override
    public ClientRailway mapRow(ResultSet resultSet, int i) throws SQLException {
        ClientRailway clientRailway = new ClientRailway();
        clientRailway.setId_client(resultSet.getLong("id_client"));
        clientRailway.setId_train(resultSet.getLong("id_train"));
        clientRailway.setName_client(resultSet.getString("name_client"));
        clientRailway.setSoname_client(resultSet.getString("soname_client"));
        clientRailway.setDate_purchase(resultSet.getDate("date_purchase"));
        clientRailway.setPhone_client(resultSet.getString("phone_client"));
        return clientRailway;
    }
}
