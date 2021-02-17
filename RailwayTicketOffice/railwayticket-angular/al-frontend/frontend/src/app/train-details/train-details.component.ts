import { Component, OnInit } from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientsService} from "../clients.service";
import {TrainService} from "../train.service";
import {Train} from "../train";

@Component({
  selector: 'app-train-details',
  templateUrl: './train-details.component.html',
  styleUrls: ['./train-details.component.css']
})
export class TrainDetailsComponent implements OnInit {
  id_train: number;
  trainById: Train;
  client: Clientrailway = new Clientrailway();

  constructor(private router:Router,private route: ActivatedRoute,private trainService: TrainService,private clientService: ClientsService) { }

  ngOnInit(): void {
    this.id_train=this.route.snapshot.params['id_train'];

    this.trainById = new Train();
    this.trainService.getOneTrainById(this.id_train).subscribe(data=>{
      this.trainById = data;
    });
  }

  editTrain(id_train:number){
    this.router.navigate(['updateTrain',id_train]);
  }

  deleteTrain(id_train:number){
    this.trainService.deleteTrain(id_train).subscribe(data=>{
      console.log(data);
    });
    this.router.navigate(['/allTrain']);

  }

  buyTicket(){
    this.router.navigate(['buyTicket',this.id_train])
  }



}
