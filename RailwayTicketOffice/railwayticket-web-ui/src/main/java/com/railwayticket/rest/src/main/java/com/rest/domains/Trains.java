/*
 * RailwayTicketOffice Rest API
 * \"Spring Boot REST API for railway ticket office online\"
 *
 * OpenAPI spec version: 1.0.0
 * Contact: artyommedvedev15@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.rest.domains;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Details info about the trains
 */
@ApiModel(description = "Details info about the trains")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-09-12T12:16:28.995+03:00")
public class Trains {
  /**
   * The station where the train arrives
   */
  public enum ArrivalStationEnum {
    BREST("BREST"),
    
    MINSK("MINSK"),
    
    GRODNO("GRODNO"),
    
    VITEBSK("VITEBSK"),
    
    MOGILEV("MOGILEV"),
    
    GOMEL("GOMEL");

    private String value;

    ArrivalStationEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ArrivalStationEnum fromValue(String text) {
      for (ArrivalStationEnum b : ArrivalStationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("arrivalStation")
  private ArrivalStationEnum arrivalStation = null;

  @JsonProperty("available_ticket")
  private Integer availableTicket = null;

  @JsonProperty("date_time_arrival")
  private String dateTimeArrival = null;

  @JsonProperty("date_time_departure")
  private String dateTimeDeparture = null;

  /**
   * The station where the train starts from
   */
  public enum DepartureStationEnum {
    BREST("BREST"),
    
    MINSK("MINSK"),
    
    GRODNO("GRODNO"),
    
    VITEBSK("VITEBSK"),
    
    MOGILEV("MOGILEV"),
    
    GOMEL("GOMEL");

    private String value;

    DepartureStationEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DepartureStationEnum fromValue(String text) {
      for (DepartureStationEnum b : DepartureStationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("departureStation")
  private DepartureStationEnum departureStation = null;

  @JsonProperty("id_train")
  private Long idTrain = null;

  @JsonProperty("name_train")
  private String nameTrain = null;

  @JsonProperty("price_ticket")
  private Float priceTicket = null;

  @JsonProperty("total_ticket")
  private Integer totalTicket = null;

  /**
   * The train&#39;s type
   */
  public enum TypeTrainEnum {
    ECONOM("ECONOM"),
    
    BUSINESS("BUSINESS"),
    
    COUPE("COUPE"),
    
    RESERVED_SEAT("RESERVED_SEAT");

    private String value;

    TypeTrainEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeTrainEnum fromValue(String text) {
      for (TypeTrainEnum b : TypeTrainEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("typeTrain")
  private TypeTrainEnum typeTrain = null;

  public Trains arrivalStation(ArrivalStationEnum arrivalStation) {
    this.arrivalStation = arrivalStation;
    return this;
  }

   /**
   * The station where the train arrives
   * @return arrivalStation
  **/
  @ApiModelProperty(value = "The station where the train arrives")
  public ArrivalStationEnum getArrivalStation() {
    return arrivalStation;
  }

  public void setArrivalStation(ArrivalStationEnum arrivalStation) {
    this.arrivalStation = arrivalStation;
  }

  public Trains availableTicket(Integer availableTicket) {
    this.availableTicket = availableTicket;
    return this;
  }

   /**
   * Number of available tickets
   * @return availableTicket
  **/
  @ApiModelProperty(value = "Number of available tickets")
  public Integer getAvailableTicket() {
    return availableTicket;
  }

  public void setAvailableTicket(Integer availableTicket) {
    this.availableTicket = availableTicket;
  }

  public Trains dateTimeArrival(String dateTimeArrival) {
    this.dateTimeArrival = dateTimeArrival;
    return this;
  }

   /**
   * Date of arrival of the train at the st
   * @return dateTimeArrival
  **/
  @ApiModelProperty(example = "2020-09-15", value = "Date of arrival of the train at the st")
  public String getDateTimeArrival() {
    return dateTimeArrival;
  }

  public void setDateTimeArrival(String dateTimeArrival) {
    this.dateTimeArrival = dateTimeArrival;
  }

  public Trains dateTimeDeparture(String dateTimeDeparture) {
    this.dateTimeDeparture = dateTimeDeparture;
    return this;
  }

   /**
   * Date of departure of the train from the station
   * @return dateTimeDeparture
  **/
  @ApiModelProperty(example = "2020-09-15", value = "Date of departure of the train from the station")
  public String getDateTimeDeparture() {
    return dateTimeDeparture;
  }

  public void setDateTimeDeparture(String dateTimeDeparture) {
    this.dateTimeDeparture = dateTimeDeparture;
  }

  public Trains departureStation(DepartureStationEnum departureStation) {
    this.departureStation = departureStation;
    return this;
  }

   /**
   * The station where the train starts from
   * @return departureStation
  **/
  @ApiModelProperty(value = "The station where the train starts from")
  public DepartureStationEnum getDepartureStation() {
    return departureStation;
  }

  public void setDepartureStation(DepartureStationEnum departureStation) {
    this.departureStation = departureStation;
  }

  public Trains idTrain(Long idTrain) {
    this.idTrain = idTrain;
    return this;
  }

   /**
   * The unique id of the train
   * @return idTrain
  **/
  @ApiModelProperty(value = "The unique id of the train")
  public Long getIdTrain() {
    return idTrain;
  }

  public void setIdTrain(Long idTrain) {
    this.idTrain = idTrain;
  }

  public Trains nameTrain(String nameTrain) {
    this.nameTrain = nameTrain;
    return this;
  }

   /**
   * The train&#39;s name
   * @return nameTrain
  **/
  @ApiModelProperty(value = "The train's name")
  public String getNameTrain() {
    return nameTrain;
  }

  public void setNameTrain(String nameTrain) {
    this.nameTrain = nameTrain;
  }

  public Trains priceTicket(Float priceTicket) {
    this.priceTicket = priceTicket;
    return this;
  }

   /**
   * The price for a ticket
   * @return priceTicket
  **/
  @ApiModelProperty(value = "The price for a ticket")
  public Float getPriceTicket() {
    return priceTicket;
  }

  public void setPriceTicket(Float priceTicket) {
    this.priceTicket = priceTicket;
  }

  public Trains totalTicket(Integer totalTicket) {
    this.totalTicket = totalTicket;
    return this;
  }

   /**
   * Total number of tickets
   * @return totalTicket
  **/
  @ApiModelProperty(value = "Total number of tickets")
  public Integer getTotalTicket() {
    return totalTicket;
  }

  public void setTotalTicket(Integer totalTicket) {
    this.totalTicket = totalTicket;
  }

  public Trains typeTrain(TypeTrainEnum typeTrain) {
    this.typeTrain = typeTrain;
    return this;
  }

   /**
   * The train&#39;s type
   * @return typeTrain
  **/
  @ApiModelProperty(value = "The train's type")
  public TypeTrainEnum getTypeTrain() {
    return typeTrain;
  }

  public void setTypeTrain(TypeTrainEnum typeTrain) {
    this.typeTrain = typeTrain;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Trains trains = (Trains) o;
    return Objects.equals(this.arrivalStation, trains.arrivalStation) &&
        Objects.equals(this.availableTicket, trains.availableTicket) &&
        Objects.equals(this.dateTimeArrival, trains.dateTimeArrival) &&
        Objects.equals(this.dateTimeDeparture, trains.dateTimeDeparture) &&
        Objects.equals(this.departureStation, trains.departureStation) &&
        Objects.equals(this.idTrain, trains.idTrain) &&
        Objects.equals(this.nameTrain, trains.nameTrain) &&
        Objects.equals(this.priceTicket, trains.priceTicket) &&
        Objects.equals(this.totalTicket, trains.totalTicket) &&
        Objects.equals(this.typeTrain, trains.typeTrain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arrivalStation, availableTicket, dateTimeArrival, dateTimeDeparture, departureStation, idTrain, nameTrain, priceTicket, totalTicket, typeTrain);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Trains {\n");
    
    sb.append("    arrivalStation: ").append(toIndentedString(arrivalStation)).append("\n");
    sb.append("    availableTicket: ").append(toIndentedString(availableTicket)).append("\n");
    sb.append("    dateTimeArrival: ").append(toIndentedString(dateTimeArrival)).append("\n");
    sb.append("    dateTimeDeparture: ").append(toIndentedString(dateTimeDeparture)).append("\n");
    sb.append("    departureStation: ").append(toIndentedString(departureStation)).append("\n");
    sb.append("    idTrain: ").append(toIndentedString(idTrain)).append("\n");
    sb.append("    nameTrain: ").append(toIndentedString(nameTrain)).append("\n");
    sb.append("    priceTicket: ").append(toIndentedString(priceTicket)).append("\n");
    sb.append("    totalTicket: ").append(toIndentedString(totalTicket)).append("\n");
    sb.append("    typeTrain: ").append(toIndentedString(typeTrain)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

