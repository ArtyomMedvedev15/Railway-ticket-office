package com.railwayticket.service.servic_api;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.exception.ClientServiceException;

import java.util.List;
import java.util.Optional;

public interface ClientServiceApi extends BaseServiceApi<ClientRailway> {
    List<ClientRailway> FindByNameClient(String name_client) throws ClientServiceException;
}
