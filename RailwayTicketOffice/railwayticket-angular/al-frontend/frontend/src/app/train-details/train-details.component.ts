import { Component, OnInit } from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientsService} from "../clients.service";
import {TrainService} from "../train.service";
import {Train} from "../train";
import {UpdateTrainComponent} from "../update-train/update-train.component";
import {MatDialog} from "@angular/material/dialog";
import {MatCard} from "@angular/material/card";
import {MatDividerModule} from '@angular/material/divider';
import {TrainCreateComponent} from "../train-create/train-create.component";
import {BuyTicketComponent} from "../buy-ticket/buy-ticket.component";

@Component({
  selector: 'app-train-details',
  templateUrl: './train-details.component.html',
  styleUrls: ['./train-details.component.css']
})
export class TrainDetailsComponent implements OnInit {
  id_train: number;
  trainById: Train;
  client: Clientrailway = new Clientrailway();

  constructor(private router:Router,private route: ActivatedRoute,private trainService: TrainService,
              private clientService: ClientsService,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.id_train=this.route.snapshot.params['id_train'];
    this.trainById = new Train();
    this.trainService.getOneTrainById(this.id_train).subscribe(data=>{
      this.trainById = data;
    });
  }

  editTrain(id_train: number){
    const dialogRef = this.dialog.open(UpdateTrainComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        },
        id_train: id_train.toString()
      },
    });
    this.dialog.afterAllClosed.subscribe(data => {
      this.trainService.getOneTrainById(this.id_train).subscribe(data=>{
        this.trainById = data;
      });
    });
  }

  deleteTrain(id_train:number){
    this.trainService.deleteTrain(id_train).subscribe(data=>{
     });
    this.router.navigate(['/allTrain']);
  }

  buyTicket(id_train:number){
    const dialogRef = this.dialog.open(BuyTicketComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        },
        id_train: id_train.toString()
      },
    });
  }



}
