package com.railwayticket.domain;

import java.sql.Date;
import java.util.Objects;

public class Trains {
    private Long id_train;
    private String name_train;
    private TypeTrain typeTrain;
    private Stations departureStation;
    private Stations arrivalStation;
    private Date date_time_departure;
    private Date date_time_arrival;
    private Integer available_ticket;
    private Integer total_ticket;
    private Float price_ticket;

    public Trains() {
    }

    public Trains(String name_train, TypeTrain typeTrain, Stations departureStation, Stations arrivalStation,
                  Date date_time_departure, Date date_time_arrival,
                  Integer available_ticket, Integer total_ticket, Float price_ticket) {
        this.name_train = name_train;
        this.typeTrain = typeTrain;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.date_time_departure = date_time_departure;
        this.date_time_arrival = date_time_arrival;
        this.available_ticket = available_ticket;
        this.total_ticket = total_ticket;
        this.price_ticket = price_ticket;
    }

    public Long getId_train() {
        return id_train;
    }

    public void setId_train(Long id_train) {
        this.id_train = id_train;
    }

    public String getName_train() {
        return name_train;
    }

    public void setName_train(String name_train) {
        this.name_train = name_train;
    }

    public TypeTrain getTypeTrain() {
        return typeTrain;
    }

    public void setTypeTrain(TypeTrain typeTrain) {
        this.typeTrain = typeTrain;
    }

    public Stations getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Stations departureStation) {
        this.departureStation = departureStation;
    }

    public Stations getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Stations arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Date getDate_time_departure() {
        return date_time_departure;
    }

    public void setDate_time_departure(Date date_time_departure) {
        this.date_time_departure = date_time_departure;
    }

    public Date getDate_time_arrival() {
        return date_time_arrival;
    }

    public void setDate_time_arrival(Date date_time_arrival) {
        this.date_time_arrival = date_time_arrival;
    }

    public Integer getAvailable_ticket() {
        return available_ticket;
    }

    public void setAvailable_ticket(Integer available_ticket) {
        this.available_ticket = available_ticket;
    }

    public Integer getTotal_ticket() {
        return total_ticket;
    }

    public void setTotal_ticket(Integer total_ticket) {
        this.total_ticket = total_ticket;
    }

    public Float getPrice_ticket() {
        return price_ticket;
    }

    public void setPrice_ticket(Float price_ticket) {
        this.price_ticket = price_ticket;
    }

    @Override
    public String toString() {
        return "Trains{" +
                "id_train=" + id_train +
                ", name_train='" + name_train + '\'' +
                ", typeTrain=" + typeTrain +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", date_time_departure=" + date_time_departure +
                ", date_time_arrival=" + date_time_arrival +
                ", available_ticket=" + available_ticket +
                ", total_ticket=" + total_ticket +
                ", price_ticket=" + price_ticket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trains trains = (Trains) o;
        return Objects.equals(id_train, trains.id_train) && Objects.equals(name_train, trains.name_train) && typeTrain == trains.typeTrain && departureStation == trains.departureStation && arrivalStation == trains.arrivalStation && Objects.equals(date_time_departure, trains.date_time_departure) && Objects.equals(date_time_arrival, trains.date_time_arrival) && Objects.equals(available_ticket, trains.available_ticket) && Objects.equals(total_ticket, trains.total_ticket) && Objects.equals(price_ticket, trains.price_ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_train, name_train, typeTrain, departureStation, arrivalStation, date_time_departure, date_time_arrival, available_ticket, total_ticket, price_ticket);
    }
}
