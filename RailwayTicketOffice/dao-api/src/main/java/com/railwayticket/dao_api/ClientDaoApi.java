package com.railwayticket.dao_api;



import com.domain.ClientRailway;

import java.util.List;

public interface ClientDaoApi extends BaseDaoApi<ClientRailway> {
    List<ClientRailway> FindByName(String name);
}
