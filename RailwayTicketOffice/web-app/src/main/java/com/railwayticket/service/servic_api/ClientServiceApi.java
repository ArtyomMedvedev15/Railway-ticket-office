package com.railwayticket.service.servic_api;

import com.railwayticket.domain.ClientRailway;

import java.util.Optional;

public interface ClientServiceApi extends BaseServiceApi<ClientRailway> {
    Optional<ClientRailway>FindByNameClient(String name_client);
}
