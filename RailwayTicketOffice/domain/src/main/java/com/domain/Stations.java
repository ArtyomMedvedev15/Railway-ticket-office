package com.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "com.domain.Stations where trains arrive")
public enum Stations {
    BREST(1,"Brest"),MINSK(2,"Minsk"),GRODNO(3,"Grodno"),
    VITEBSK(4,"Vitebsk"),MOGILEV(5,"Mogilev"),GOMEL(6,"Gomel");

    @ApiModelProperty(notes = "The station's name")
    private final String nameStation;
    @ApiModelProperty(notes = "The station's id")
    private final int id_station;

    Stations(int id_station,String nameStation) {
        this.nameStation = nameStation;
        this.id_station = id_station;
    }

    public String getNameStation() {
        return nameStation;
    }

    public int getId_station() {
        return id_station;
    }

    public static Stations getStationById(Long idStations){
        if(idStations==1){
            return Stations.BREST;
        }else if(idStations==2){
            return Stations.MINSK;
        }else if(idStations==3){
            return Stations.GRODNO;
        }else if(idStations==4){
            return Stations.VITEBSK;
        }else if(idStations==5){
            return Stations.MOGILEV;
        }else if(idStations==6){
            return Stations.GOMEL;
        }else{
            return null;
        }
    }
}
