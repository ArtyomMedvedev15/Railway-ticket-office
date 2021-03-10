import { Component, OnInit } from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientsService} from "../clients.service";
import {MatDialog} from "@angular/material/dialog";
import {UpdateClientComponent} from "../update-client/update-client.component";

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  id_client: number;
  clientById: Clientrailway = new Clientrailway();

  constructor(private router:Router, private route: ActivatedRoute, private clientService: ClientsService,
  private dialog:MatDialog) { }

  ngOnInit(): void {
    this.id_client = this.route.snapshot.params['id_client'];

    this.clientById = new Clientrailway();
    this.clientService.getClientById(this.id_client).subscribe(data => {
      this.clientById = data;
    });
  }

  editClient(id_client:number){
    const dialogRef = this.dialog.open(UpdateClientComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        },
        id_client: id_client.toString()
      },
    });
    this.dialog.afterAllClosed.subscribe(data => {
      this.clientService.getClientById(this.id_client).subscribe(data => {
        this.clientById = data;
      });
    });
  }
}
