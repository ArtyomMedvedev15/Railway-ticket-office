import {Component, Inject, OnInit} from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {Train} from "../train";
import {TrainService} from "../train.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ClientsService} from "../clients.service";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {MAT_FORM_FIELD, MatFormField, MatFormFieldControl} from '@angular/material/form-field';

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.css']
})
export class BuyTicketComponent implements OnInit {
  client: Clientrailway = new Clientrailway();
  buy_train_ticket:Train = new Train();

  constructor(private trainService: TrainService,private clientService:ClientsService,
              private route: ActivatedRoute,private router:Router,
              private dialog:MatDialog, @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
    this.trainService.getOneTrainById(this.data.id_train).subscribe(data=>{
      this.buy_train_ticket = data;
    });
  }

  buyTicket(){
      let dateTime = new Date();
      this.client.date_purchase = dateTime.getFullYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay();
      this.client.id_train = this.data.id_train;
      this.clientService.saveClient(this.client).subscribe(data => {
        this.dialog.closeAll();
      }, error => console.log(error));
      this.router.navigate(['/allClient']);
  }




}
