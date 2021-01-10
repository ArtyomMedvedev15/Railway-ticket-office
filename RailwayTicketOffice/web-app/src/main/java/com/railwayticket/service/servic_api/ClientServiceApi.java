package com.railwayticket.service.servic_api;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.exception.ClientServiceException;

import java.util.Optional;

public interface ClientServiceApi extends BaseServiceApi<ClientRailway> {
    Optional<ClientRailway>FindByNameClient(String name_client) throws ClientServiceException;
}
