import {Component,AfterViewInit, OnInit, ViewChild} from '@angular/core';
import {Train} from "../train";
import {TrainService} from "../train.service";
import {Router} from "@angular/router";
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatIconModule} from "@angular/material/icon";


@Component({
  selector: 'app-train-list',
  templateUrl: './train-list.component.html',
  styleUrls: ['./train-list.component.css']
})

export class TrainListComponent implements OnInit {
  displayedColumns: string[] = ['Number train', 'Name train', 'Type train', 'Departure station','Arrival station',
  'Date and time departure','Date and time arrival','Available tickets','Total tickets','Price','Delete','Edit','Details'];
  dataSource: MatTableDataSource<Train>;

  train_list: Train[]

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private trainService: TrainService,private router:Router) {

    this.dataSource = new MatTableDataSource(this.train_list);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngAfterViewInit() {

    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.getAllTrain();
  }


  private getAllTrain(){
    this.trainService.getAllTrain().subscribe((data:Train[])=>{
      this.dataSource.data=data;
    })
  }

  editTrain(id_train:number){
    this.router.navigate(['updateTrain',id_train]);
  }

  AddNewTrain() {
    this.router.navigate(['createTrain'])
  }

  deleteTrain(id_train:number){
    this.trainService.deleteTrain(id_train).subscribe(data=>{
      this.getAllTrain();
      console.log(data);
    })
  }

  trainDetails(id_train:number){
    this.router.navigate(['trainone/',id_train]);
  }
}
