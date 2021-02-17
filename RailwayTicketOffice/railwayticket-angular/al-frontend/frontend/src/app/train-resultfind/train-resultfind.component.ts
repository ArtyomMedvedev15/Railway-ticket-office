import { Component, OnInit } from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Train} from "../train";

@Component({
  selector: 'app-train-resultfind',
  templateUrl: './train-resultfind.component.html',
  styleUrls: ['./train-resultfind.component.css']
})
export class TrainResultfindComponent implements OnInit {
  train_list: Train[]
  arrivalDate:string;
  arrivalStationFind:string;
  departureDate:string;
  departureStationFind:string;

  constructor(private trainService: TrainService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.findTrainByDate();
    console.log(this.train_list)
  }

  findTrainByDate(){
    this.arrivalDate =  this.router.snapshot.params.arrivalDate;
    this.arrivalStationFind = this.router.snapshot.params.arrivalStationFind;
    this.departureDate = this.router.snapshot.params.departureDate;
    this.departureStationFind = this.router.snapshot.params.departureStationFind;

    this.trainService.findtrainbydates(this.arrivalDate,this.arrivalStationFind,this.departureDate,this.departureStationFind).subscribe(data=>{
      this.train_list=data;
    })
  }
}
