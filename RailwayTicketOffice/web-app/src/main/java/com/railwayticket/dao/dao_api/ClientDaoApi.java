package com.railwayticket.dao.dao_api;

import com.railwayticket.domain.ClientRailway;

import java.util.List;
import java.util.Optional;

public interface ClientDaoApi extends BaseDaoApi<ClientRailway> {
    List<ClientRailway> FindByName(String name);
}
