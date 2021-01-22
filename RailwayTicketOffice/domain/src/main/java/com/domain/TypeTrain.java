package com.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Type trains")
public enum TypeTrain {

    ECONOM(1,"Econom"),BUSINESS(2,"Business"),COUPE(3,"Coupe"),RESERVED_SEAT(4,"Reserved seat");

    @ApiModelProperty(notes = "The type's name")
    private final String nameType;
    @ApiModelProperty(notes = "The type's id")
    private final int id_type;

    TypeTrain(int id_type,String nameType) {
        this.nameType = nameType;
        this.id_type = id_type;
    }

    public int getId_type() {
        return id_type;
    }

    public String getNameType() {
        return nameType;
    }

    public static TypeTrain getTypeById(Long idType){
       if(idType==1){
           return TypeTrain.ECONOM;
       }else if(idType==2){
           return TypeTrain.BUSINESS;
       }else if(idType==3){
           return TypeTrain.COUPE;
       }else if(idType==4){
           return TypeTrain.RESERVED_SEAT;
       }else{
           return null;
       }
    }
}
