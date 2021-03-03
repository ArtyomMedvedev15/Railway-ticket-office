import {Component, Inject, OnInit} from '@angular/core';
import {ClientsService} from "../clients.service";
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {

  clientUpdate:Clientrailway = new Clientrailway();

  constructor(private clientService:ClientsService,private router:ActivatedRoute,private route:Router,
   @Inject(MAT_DIALOG_DATA) public data: any,
  private dialog:MatDialog) { }

  ngOnInit(): void {
    this.clientService.getClientById(this.data.id_client).subscribe(data=>{
      this.clientUpdate=data;
    });
  }

  editClient() {
       this.clientService.editClient(this.clientUpdate).subscribe(data =>{
      this.dialog.closeAll();
    },error => console.log(error));
   }
}
