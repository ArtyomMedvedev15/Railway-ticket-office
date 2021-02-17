import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Clientrailway} from "./clientrailway";
import {Train} from "./train";

@Injectable({
  providedIn: 'root'
})
export class TrainService {

  private base_url = "http://localhost:8181/api/train/"
  param_find:HttpParams = new HttpParams();


  constructor(private httpClient: HttpClient) { }

  getAllTrain():Observable<Train[]>{
    return this.httpClient.get<Train[]>(this.base_url+"allTrain");
  }

  getOneTrainById(id:number):Observable<Train>{
    return this.httpClient.get<Train>(this.base_url+id)
  }

  saveTrain(trainSave:Train):Observable<any>{
    return this.httpClient.post(this.base_url+"saveTrain",trainSave);
  }

  editTrain(trainUpdate:Train):Observable<any>{
    return this.httpClient.post(this.base_url+"updateTrain",trainUpdate);
  }

  deleteTrain(id:number):Observable<Object>{
    return this.httpClient.get(this.base_url+"deleteTrain/"+id);
  }

  findtrainbydates(arrivalDate:string,arrivalStationFind:string,departureDate:string,departureStationFind:string):Observable<Train[]>{
    this.param_find.set("arrivalDate",arrivalDate);
    this.param_find.set("arrivalStationFind",arrivalStationFind);
    this.param_find.set("departureDate",departureDate);
    this.param_find.set("departureStationFind",departureStationFind);
    return this.httpClient.post<Train[]>(this.base_url+"findtrainbydates",this.param_find);
  }
}
