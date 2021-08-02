import {Component, AfterViewInit, OnInit, ViewChild} from '@angular/core';
import {Train} from '../train';
import {TrainService} from '../train.service';
import {Router} from '@angular/router';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {TrainCreateComponent} from "../train-create/train-create.component";
import {MatDialog} from '@angular/material/dialog';
import {UpdateTrainComponent} from "../update-train/update-train.component";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {MatMenuModule} from '@angular/material/menu';


@Component({
  selector: 'app-train-list',
  templateUrl: './train-list.component.html',
  styleUrls: ['./train-list.component.css']
})

export class TrainListComponent implements OnInit {
  displayedColumns: string[] = ['Number train', 'Name train', 'Type train', 'Departure station', 'Arrival station',
  'Date and time departure', 'Date and time arrival', 'Available tickets', 'Total tickets', 'Price', 'Delete', 'Edit', 'Details'];
  dataSource: MatTableDataSource<Train>;
  train_list: Train[];
  trainUpdate:Train;
  selectedFilesExcel?: FileList;
  selectedFilesXml?: FileList;

  currentFile?: File;
  message = '';
  errorMsg = '';
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private trainService: TrainService, private router: Router, private dialog: MatDialog) {
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


   getAllTrain(){
    this.trainService.getAllTrain().subscribe((data: Train[]) => {
      this.dataSource.data = data;
    });
  }

  editTrain(id_train: number){
    const dialogRef = this.dialog.open(UpdateTrainComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        },
        id_train: id_train.toString()
      },
    });
    this.dialog.afterAllClosed.subscribe(data => {
      this.getAllTrain();
    });
  }

  AddNewTrain() {
    const dialogRef = this.dialog.open(TrainCreateComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        }
      },
    });
    this.dialog.afterAllClosed.subscribe(data => {
      this.getAllTrain();
    });
   }

  deleteTrain(id_train: number){
    this.trainService.deleteTrain(id_train).subscribe(data => {
      this.getAllTrain();
      console.log(data);
    });
  }

  trainDetails(id_train: number){
    this.router.navigate(['trainone/', id_train]);
  }

  exportToExcel():void{
    this.trainService.ExportTrainsToExcel();
  }

  exportToXml():void{
    this.trainService.ExportTrainsToXml();
  }

  selectFileExcel(event: any): void {
    this.selectedFilesExcel = event.target.files;
  }

  uploadExcel(): void {
    this.errorMsg = '';

    if (this.selectedFilesExcel) {
      const file: File | null = this.selectedFilesExcel.item(0);

      if (file) {
        this.currentFile = file;

        this.trainService.ImportTrainFromExcel(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              console.log(Math.round(100 * event.loaded / event.total));

            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;
            }
            this.getAllTrain();
          },
          (err: any) => {
            console.log(err);

            if (err.error && err.error.responseMessage) {
             } else {
             }

            this.currentFile = undefined;
            this.getAllTrain();
          });
      }
      this.selectedFilesExcel = undefined;
    }
  }

  selectFileXml(event: any): void {
    this.selectedFilesXml = event.target.files;
  }

  uploadXml(): void {
    this.errorMsg = '';

    if (this.selectedFilesXml) {
      const file: File | null = this.selectedFilesXml.item(0);

      if (file) {
        this.currentFile = file;

        this.trainService.ImportTrainFromXml(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              console.log(Math.round(100 * event.loaded / event.total));

            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;
            }
            this.getAllTrain();
          },
          (err: any) => {
            console.log(err);

            if (err.error && err.error.responseMessage) {
             } else {
             }

            this.currentFile = undefined;
            this.getAllTrain();
          });
      }
      this.selectedFilesXml = undefined;
    }
  }

}
