import { Component, OnInit } from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {ClientsService} from "../clients.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-clientrailway-list',
  templateUrl: './clientrailway-list.component.html',
  styleUrls: ['./clientrailway-list.component.css']
})
export class ClientrailwayListComponent implements OnInit {
  clientsrailway_list: Clientrailway[]
  name_client:string;

  constructor(private   clientService: ClientsService,private router:Router) { }

  ngOnInit(): void {
      this.getAllClients();
  }


  private getAllClients(){
    this.clientService.getAllClients().subscribe(data=>{
      this.clientsrailway_list=data;
    })
  }

  editClient(id_client:number){
    this.router.navigate(['updateClient',id_client]);
  }

  deleteClient(id_client: number) {
      this.clientService.deleteClient(id_client).subscribe(data=>{
        this.getAllClients();
        console.log(data);
      })
  }

  clientById(id_client:number){
    this.router.navigate(['clientone/',id_client]);
  }

  findclientbyname(name:string){
    if(name!=="") {
      this.clientService.findClientByName(name).subscribe(data => {
        this.clientsrailway_list = data;
      })
    }else{
      this.getAllClients();
    }
  }

}
