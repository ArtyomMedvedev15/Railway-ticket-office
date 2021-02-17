import { Component, OnInit } from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientsService} from "../clients.service";

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  id_client: number
  clientById: Clientrailway

  constructor(private router:Router,private route: ActivatedRoute,private clientService: ClientsService) { }

  ngOnInit(): void {
    this.id_client=this.route.snapshot.params['id_client'];

    this.clientById = new Clientrailway();
    this.clientService.getClientById(this.id_client).subscribe(data=>{
      this.clientById = data;
    });
  }

  editClient(id_client:number){
    this.router.navigate(['updateClient',id_client]);
  }
}
