import { Component, OnInit } from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TrainfindList} from "../trainfind-list";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter} from "@angular/material-moment-adapter";
import {FormControl} from "@angular/forms";
// @ts-ignore
import {default as _rollupMoment} from "moment";
import * as _moment from "moment";
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
  selector: 'app-train-find',
  templateUrl: './train-find.component.html',
  styleUrls: ['./train-find.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class TrainFindComponent implements OnInit {
  date = new FormControl(moment());

  arrivalDate: string;
  arrivalStationFind: string;
  departureDate: string;
  departureStationFind: string;

  constructor(private trainService: TrainService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  findtrainByDate(){
    this.router.navigate(['/resultfind', (moment(this.arrivalDate)).format('YYYY-MM-DD'), this.arrivalStationFind, (moment(this.departureDate)).format('YYYY-MM-DD'), this.departureStationFind]);
  }
}
