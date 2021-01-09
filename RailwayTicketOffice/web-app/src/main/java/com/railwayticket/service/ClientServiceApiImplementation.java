package com.railwayticket.service;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

public class ClientServiceApiImplementation implements ClientServiceApi {
    @Override
    public Optional<ClientRailway> FindByNameClient(String name_client) {

        return Optional.empty();
    }

    @Override
    public boolean save(ClientRailway clientRailway) {

        return false;
    }

    @Override
    public boolean update(ClientRailway clientRailway) {
        return false;
    }

    @Override
    public boolean delete(ClientRailway clientRailway) {
        return false;
    }

    @Override
    public ClientRailway getOneById(Long id) {
        return null;
    }

    @Override
    public Optional<ClientRailway> FindAll() {
        return Optional.empty();
    }
}
