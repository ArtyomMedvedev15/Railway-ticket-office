package com.domain;

import com.domain.util.DateTimeAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.Objects;


@XmlRootElement(name = "train")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(description = "Details info about the trains")
public class Trains {

     @ApiModelProperty(notes = "The unique id of the train")
    private Long id_train;

     @ApiModelProperty(notes = "The train's name")
    private String name_train;

     @ApiModelProperty(notes = "The train's type")
    private TypeTrain typeTrain;

     @ApiModelProperty(notes = "The station where the train starts from")
    private Stations departureStation;

     @ApiModelProperty(notes = "The station where the train arrives")
    private Stations arrivalStation;

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @JsonFormat(pattern="yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(notes = "Date of departure of the train from the station",example = "2020-09-15")
    private Date date_time_departure;

    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @JsonFormat(pattern="yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(notes = "Date of arrival of the train at the st",example = "2020-09-15")
    private Date date_time_arrival;

     @ApiModelProperty(notes = "Number of available tickets")
    private Integer available_ticket;

     @ApiModelProperty(notes = "Total number of tickets")
    private Integer total_ticket;

     @ApiModelProperty(notes = "The price for a ticket")
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
        return "com.domain.Trains{" +
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
