import { Component, OnInit } from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Train} from "../train";

@Component({
  selector: 'app-train-create',
  templateUrl: './train-create.component.html',
  styleUrls: ['./train-create.component.css']
})
export class TrainCreateComponent implements OnInit {

  train_save:Train = new Train();

  constructor(private trainService: TrainService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
  }

  saveTrain(){
    this.trainService.saveTrain(this.train_save).subscribe(data =>{
      console.log(data)
    },error => console.log(error));
    this.router.navigate(['allTrain']);

  }

}
