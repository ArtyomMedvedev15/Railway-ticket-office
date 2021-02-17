import { Component, OnInit } from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {Train} from "../train";
import {TrainService} from "../train.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ClientsService} from "../clients.service";

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.css']
})
export class BuyTicketComponent implements OnInit {
  client: Clientrailway = new Clientrailway();
  total_price:string;
  private id_train:number;
  buy_train_ticket:Train;

  constructor(private trainService: TrainService,private clientService:ClientsService,
              private route: ActivatedRoute,private router:Router) {
  }

  ngOnInit(): void {
    this.id_train = this.route.snapshot.params.id_train;
    this.trainService.getOneTrainById(this.id_train).subscribe(data=>{
      this.buy_train_ticket = data;
    })
  }

  buyTicket(){
      let dateTime = new Date()
      this.client.date_purchase = dateTime.getFullYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay();
      this.client.id_train = this.buy_train_ticket.id_train;
      this.clientService.saveClient(this.client).subscribe(data =>{
        console.log(data)
      },error => console.log(error));

      this.router.navigate(['/allClient'])
  }




}
