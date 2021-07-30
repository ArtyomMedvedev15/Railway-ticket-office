package com.railwayticket.restclient.util.dto;

import javax.xml.bind.annotation.*;

import com.domain.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "trains")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Trains.class})
public class TrainsDto implements Serializable {
    @XmlElement(name = "train")
    private List<Trains> trains = null;

    public List<Trains> getTrains() {
        return trains;
    }

    public void setTrains(List<Trains> trains) {
        this.trains = trains;
    }
}
