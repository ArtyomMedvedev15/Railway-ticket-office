package com.railwayticket.domain;

public enum TypeTrain {

    ECONOM("Econom"),BUSINESS("Business"),COUPE("Coupe"),RESERVED_SEAT("Reserved seat");

    private final String nameType;

    TypeTrain(String nameType) {
        this.nameType = nameType;
    }

    public String getNameType() {
        return nameType;
    }
}
