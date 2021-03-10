import { Component, OnInit } from '@angular/core';
import {TrainService} from "../train.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TrainfindList} from "../trainfind-list";
import {DateAdapter, ErrorStateMatcher, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter} from "@angular/material-moment-adapter";
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup, FormGroupDirective, NgForm,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
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
  dateArrival = new FormControl(moment());
  dateDeparture = new FormControl(moment());

  findForm: FormGroup;
  matcher: MyErrorStateMatcher;

  arrivalDate: string;
  arrivalStationFind: string;
  departureDate: string;
  departureStationFind: string;

  constructor(private formBuilder: FormBuilder, private trainService: TrainService, private route: ActivatedRoute, private router: Router) {
    this.findForm = this.formBuilder.group({
          arrivalDate: ['', [Validators.required]],
          arrivalStationsFind: '',
          departureDate: ['', [Validators.required]],
          departureStationFind: '',
        },
        { validators: [this.ms],
              updateOn: 'blur'});
  }

  ngOnInit(): void {
  }

  findtrainByDate(){
     if (this.arrivalStationFind === this.departureStationFind){
       this.matcher = new MyErrorStateMatcher();
       alert('Error in stations');
       if(new Date(this.departureDate).getTime() > new Date(this.arrivalDate).getTime()) {
         alert('Error in dates');
       }
    }else {
      this.router.navigate(['/resultfind', (moment(this.arrivalDate)).format('YYYY-MM-DD'), this.arrivalStationFind, (moment(this.departureDate)).format('YYYY-MM-DD'), this.departureStationFind]);
    }
  }

  ms():ValidatorFn | null  {
      return (formGroup: FormGroup) => {
        const arrivalStation = formGroup.get('arrivalStationsFind');
        const departureStation = formGroup.get('departureStationFind');

        if (arrivalStation.value === departureStation.value) {
          return { stationError: true };
        }

        return null;
      };
    }
}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return (control && control.invalid);
  }
}


