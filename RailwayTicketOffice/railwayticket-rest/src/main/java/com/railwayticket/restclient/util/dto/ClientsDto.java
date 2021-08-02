package com.railwayticket.restclient.util.dto;

import com.domain.ClientRailway;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "clients")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ClientRailway.class})
public class ClientsDto {
    @XmlElement(name = "client")
    private List<ClientRailway> clients = null;

    public List<ClientRailway> getClients() {
        return clients;
    }

    public void setClients(List<ClientRailway> clientRailwayList) {
        this.clients = clientRailwayList;
    }
}
