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
 * Details info about the clients
 */
@ApiModel(description = "Details info about the clients")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-09-12T12:16:28.995+03:00")
public class ClientRailway {
  @JsonProperty("date_purchase")
  private String datePurchase = null;

  @JsonProperty("id_client")
  private Long idClient = null;

  @JsonProperty("id_train")
  private Long idTrain = null;

  @JsonProperty("name_client")
  private String nameClient = null;

  @JsonProperty("phone_client")
  private String phoneClient = null;

  @JsonProperty("soname_client")
  private String sonameClient = null;

  public ClientRailway datePurchase(String datePurchase) {
    this.datePurchase = datePurchase;
    return this;
  }

   /**
   * Date when the ticket was purchased
   * @return datePurchase
  **/
  @ApiModelProperty(example = "2020-09-15", value = "Date when the ticket was purchased")
  public String getDatePurchase() {
    return datePurchase;
  }

  public void setDatePurchase(String datePurchase) {
    this.datePurchase = datePurchase;
  }

  public ClientRailway idClient(Long idClient) {
    this.idClient = idClient;
    return this;
  }

   /**
   * The unique id of the client
   * @return idClient
  **/
  @ApiModelProperty(value = "The unique id of the client")
  public Long getIdClient() {
    return idClient;
  }

  public void setIdClient(Long idClient) {
    this.idClient = idClient;
  }

  public ClientRailway idTrain(Long idTrain) {
    this.idTrain = idTrain;
    return this;
  }

   /**
   * The ID train which the customer bought the ticket for
   * @return idTrain
  **/
  @ApiModelProperty(value = "The ID train which the customer bought the ticket for")
  public Long getIdTrain() {
    return idTrain;
  }

  public void setIdTrain(Long idTrain) {
    this.idTrain = idTrain;
  }

  public ClientRailway nameClient(String nameClient) {
    this.nameClient = nameClient;
    return this;
  }

   /**
   * The client&#39;s name
   * @return nameClient
  **/
  @ApiModelProperty(value = "The client's name")
  public String getNameClient() {
    return nameClient;
  }

  public void setNameClient(String nameClient) {
    this.nameClient = nameClient;
  }

  public ClientRailway phoneClient(String phoneClient) {
    this.phoneClient = phoneClient;
    return this;
  }

   /**
   * The client&#39;s phone number
   * @return phoneClient
  **/
  @ApiModelProperty(value = "The client's phone number")
  public String getPhoneClient() {
    return phoneClient;
  }

  public void setPhoneClient(String phoneClient) {
    this.phoneClient = phoneClient;
  }

  public ClientRailway sonameClient(String sonameClient) {
    this.sonameClient = sonameClient;
    return this;
  }

   /**
   * The client&#39;s soname
   * @return sonameClient
  **/
  @ApiModelProperty(value = "The client's soname")
  public String getSonameClient() {
    return sonameClient;
  }

  public void setSonameClient(String sonameClient) {
    this.sonameClient = sonameClient;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientRailway clientRailway = (ClientRailway) o;
    return Objects.equals(this.datePurchase, clientRailway.datePurchase) &&
        Objects.equals(this.idClient, clientRailway.idClient) &&
        Objects.equals(this.idTrain, clientRailway.idTrain) &&
        Objects.equals(this.nameClient, clientRailway.nameClient) &&
        Objects.equals(this.phoneClient, clientRailway.phoneClient) &&
        Objects.equals(this.sonameClient, clientRailway.sonameClient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datePurchase, idClient, idTrain, nameClient, phoneClient, sonameClient);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientRailway {\n");
    
    sb.append("    datePurchase: ").append(toIndentedString(datePurchase)).append("\n");
    sb.append("    idClient: ").append(toIndentedString(idClient)).append("\n");
    sb.append("    idTrain: ").append(toIndentedString(idTrain)).append("\n");
    sb.append("    nameClient: ").append(toIndentedString(nameClient)).append("\n");
    sb.append("    phoneClient: ").append(toIndentedString(phoneClient)).append("\n");
    sb.append("    sonameClient: ").append(toIndentedString(sonameClient)).append("\n");
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

