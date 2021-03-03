import { Component, OnInit } from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Train} from "../train";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {FormControl} from '@angular/forms';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import * as _moment from 'moment';
// @ts-ignore
import {default as _rollupMoment} from 'moment';
import {MatDialog} from "@angular/material/dialog";
import {TrainListComponent} from "../train-list/train-list.component";

const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'YYYY-MM-DD',
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'YYYY-MM-DD',
    dateA11yLabel: 'YYYY-MM-DD',
    monthYearA11yLabel: 'YYYY-MM-DD',
  },
};
@Component({
  selector: 'app-train-create',
  templateUrl: './train-create.component.html',
  styleUrls: ['./train-create.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class TrainCreateComponent implements OnInit {

  train_save:Train = new Train();
  date = new FormControl(moment());

  constructor(private trainService: TrainService, private route: ActivatedRoute,private router:Router,
              private dialog: MatDialog
              ) { }

  ngOnInit(): void {
  }

  saveTrain(){
    this.trainService.saveTrain(this.train_save).subscribe(data =>{
      this.dialog.closeAll();
      this.router.navigate(['allTrain']);
     },error => console.log(error));
  }

}
