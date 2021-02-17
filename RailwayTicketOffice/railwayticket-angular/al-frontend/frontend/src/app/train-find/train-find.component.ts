import { Component, OnInit } from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TrainfindList} from "../trainfind-list";

@Component({
  selector: 'app-train-find',
  templateUrl: './train-find.component.html',
  styleUrls: ['./train-find.component.css']
})
export class TrainFindComponent implements OnInit {

  arrivalDate:string;
  arrivalStationFind:string;
  departureDate:string;
  departureStationFind:string;

  constructor(private trainService: TrainService,private route:ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  findtrainByDate(){
    this.router.navigate(['/resultfind',this.arrivalDate,this.arrivalStationFind,this.departureDate,this.departureStationFind]);
  }
}
