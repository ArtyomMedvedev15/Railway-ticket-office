import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Clientrailway} from "./clientrailway";
import {Train} from "./train";

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  private base_url = "http://localhost:8181/api/clients/"
  formDt: FormData = new FormData();

  constructor(private httpClient: HttpClient) { }

  getAllClients():Observable<Clientrailway[]>{
    return this.httpClient.get<Clientrailway[]>(this.base_url+"allClient");
  }

  saveClient(client:Clientrailway): Observable<any>{
    return this.httpClient.post(this.base_url+"saveClient",client);
  }

  getClientById(id_client:number):Observable<Clientrailway>{
    return this.httpClient.get<Clientrailway>(this.base_url+id_client)
  }

  editClient(client:Clientrailway):Observable<any>{
    return this.httpClient.post(this.base_url+"updateClient",client);
  }

  deleteClient(id:number):Observable<Object>{
      return this.httpClient.get(this.base_url+"deleteClient/"+id)
  }

  findClientByName(name:string):Observable<Clientrailway[]>{
    return this.httpClient.get<Clientrailway[]>(this.base_url+"findclientbyname/"+name);
  }

  exportToExcel():void{
    window.location.href = this.base_url+"listclients/export/excel";
  }

  importFromExcel(file: File): Observable<HttpEvent<any>>{
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', this.base_url + 'excel/import', formData, {
      reportProgress: true
    });

    return this.httpClient.request(req);
  }
}
