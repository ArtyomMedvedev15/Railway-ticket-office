package com.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.util.Objects;

@ApiModel(description = "Details info about the clients")
public class ClientRailway {
    @ApiModelProperty(notes = "The unique id of the client")
    private Long id_client;
    @ApiModelProperty(notes = "The ID train which the customer bought the ticket for")
    private Long id_train;
    @ApiModelProperty(notes = "The client's name")
    private String name_client;
    @ApiModelProperty(notes = "The client's soname")
    private String soname_client;
    @ApiModelProperty(notes = "Date when the ticket was purchased")
    private Date date_purchase;
    @ApiModelProperty(notes = "The client's phone number")
    private String phone_client;

    public ClientRailway() {
    }

    public ClientRailway(Long id_client, Long id_train, String name_client, String soname_client, Date date_purchase, String phone_client) {
        this.id_client = id_client;
        this.id_train = id_train;
        this.name_client = name_client;
        this.soname_client = soname_client;
        this.date_purchase = date_purchase;
        this.phone_client = phone_client;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public Long getId_train() {
        return id_train;
    }

    public void setId_train(Long id_train) {
        this.id_train = id_train;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getSoname_client() {
        return soname_client;
    }

    public void setSoname_client(String soname_client) {
        this.soname_client = soname_client;
    }

    public Date getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(Date date_purchase) {
        this.date_purchase = date_purchase;
    }

    public String getPhone_client() {
        return phone_client;
    }

    public void setPhone_client(String phone_client) {
        this.phone_client = phone_client;
    }

    @Override
    public String toString() {
        return "com.domain.ClientRailway{" +
                "id_client=" + id_client +
                ", id_train=" + id_train +
                ", name_client='" + name_client + '\'' +
                ", soname_client='" + soname_client + '\'' +
                ", date_purchase=" + date_purchase.toString() +
                ", phone_client='" + phone_client + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRailway that = (ClientRailway) o;
        return Objects.equals(id_client, that.id_client) && Objects.equals(id_train, that.id_train) && Objects.equals(name_client, that.name_client) && Objects.equals(soname_client, that.soname_client) && Objects.equals(date_purchase, that.date_purchase) && Objects.equals(phone_client, that.phone_client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_client, id_train, name_client, soname_client, date_purchase, phone_client);
    }
}
