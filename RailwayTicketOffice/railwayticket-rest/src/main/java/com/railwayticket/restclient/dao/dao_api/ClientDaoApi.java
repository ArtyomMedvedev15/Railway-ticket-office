package com.railwayticket.restclient.dao.dao_api;

import com.railwayticket.restclient.domain.ClientRailway;

import java.util.List;

public interface ClientDaoApi extends BaseDaoApi<ClientRailway> {
    List<ClientRailway> FindByName(String name);
}
