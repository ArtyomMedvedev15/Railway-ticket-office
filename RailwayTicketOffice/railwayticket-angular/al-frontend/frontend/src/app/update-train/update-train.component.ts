import { Component, OnInit } from '@angular/core';
import {Train} from "../train";
import {ActivatedRoute, Router} from "@angular/router";
import {TrainService} from "../train.service";

@Component({
  selector: 'app-update-train',
  templateUrl: './update-train.component.html',
  styleUrls: ['./update-train.component.css']
})
export class UpdateTrainComponent implements OnInit {

  trainUpdate:Train = new Train();

  constructor(private trainService:TrainService,private router:ActivatedRoute,private route:Router) { }

  ngOnInit(): void {
    this.trainService.getOneTrainById(this.router.snapshot.params.id_train).subscribe(data=>{
      this.trainUpdate=data;
    });
  }

  editClient() {
    this.trainService.editTrain(this.trainUpdate).subscribe(data =>{
      console.log(data)
    },error => console.log(error));
    this.route.navigate(['allTrain'])
    this.trainService.getAllTrain();
  }
}
