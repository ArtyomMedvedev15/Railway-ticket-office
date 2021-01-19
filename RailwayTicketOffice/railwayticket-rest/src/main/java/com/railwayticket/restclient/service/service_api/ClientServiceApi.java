package com.railwayticket.restclient.service.service_api;

import com.railwayticket.restclient.domain.ClientRailway;
import com.railwayticket.restclient.service.exception.ClientServiceException;

import java.util.List;

public interface ClientServiceApi extends BaseServiceApi<ClientRailway> {
    List<ClientRailway> FindByNameClient(String name_client) throws ClientServiceException;
}
