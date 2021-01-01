package com.railwayticket.domain;

public enum Stations {
    BREST("Brest"),MINSK("Minsk"),GRODNO("Grodno"),
    VITEBSK("Vitebsk"),MOGILEV("Mogilev"),GOMEL("Gomel");

    private final String nameStation;

    Stations(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getNameStation() {
        return nameStation;
    }
}
