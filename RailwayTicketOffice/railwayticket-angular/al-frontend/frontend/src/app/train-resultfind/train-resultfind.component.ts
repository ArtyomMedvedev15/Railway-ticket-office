import {Component, OnInit, ViewChild} from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Train} from "../train";
import {MatPaginator} from "@angular/material/paginator";
import {BuyTicketComponent} from "../buy-ticket/buy-ticket.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-train-resultfind',
  templateUrl: './train-resultfind.component.html',
  styleUrls: ['./train-resultfind.component.css']
})
export class TrainResultfindComponent implements OnInit {
  train_list: Train[];
  arrivalDate: string;
  arrivalStationFind: string;
  departureDate: string;
  departureStationFind: string;
  formDt: FormData;
  size: number;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private trainService: TrainService, private router: ActivatedRoute,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.findTrainByDate();
  }

  findTrainByDate() {
    this.arrivalDate =  this.router.snapshot.params.arrivalDate;
    this.arrivalStationFind = this.router.snapshot.params.arrivalStationFind;
    this.departureDate = this.router.snapshot.params.departureDate;
    this.departureStationFind = this.router.snapshot.params.departureStationFind;

    this.trainService.findtrainbydates(this.arrivalDate, this.arrivalStationFind, this.departureDate, this.departureStationFind).subscribe(data=>{
      this.train_list = data;
     });


  }

  buyTicket(id_train: number) {
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
