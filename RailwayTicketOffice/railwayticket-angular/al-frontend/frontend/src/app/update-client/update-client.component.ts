import { Component, OnInit } from '@angular/core';
import {ClientsService} from "../clients.service";
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {

  clientUpdate:Clientrailway = new Clientrailway();

  constructor(private clientService:ClientsService,private router:ActivatedRoute,private route:Router) { }

  ngOnInit(): void {
    this.clientService.getClientById(this.router.snapshot.params.id_client).subscribe(data=>{
      this.clientUpdate=data;
    });
  }

  editClient() {
       this.clientService.editClient(this.clientUpdate).subscribe(data =>{
      console.log(data)
    },error => console.log(error));
  this.route.navigate(['allClient'])
  }
}
