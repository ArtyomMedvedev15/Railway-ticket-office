import {Component, OnInit, ViewChild} from '@angular/core';
import {Clientrailway} from "../clientrailway";
import {ClientsService} from "../clients.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {Train} from "../train";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {TrainCreateComponent} from "../train-create/train-create.component";
import {UpdateClientComponent} from "../update-client/update-client.component";
import {HttpEventType, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-clientrailway-list',
  templateUrl: './clientrailway-list.component.html',
  styleUrls: ['./clientrailway-list.component.css']
})
export class ClientrailwayListComponent implements OnInit {
  displayedColumns: string[] = ['Id client', 'Number train', 'Name', 'Soname', 'Date of purchase',
    'Phone', 'Edit', 'Delete', 'Details'];
  dataSource: MatTableDataSource<Clientrailway>;
  clientsrailway_list : Clientrailway[];
  name_client : string;
  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private   clientService: ClientsService,private router:Router,private dialog:MatDialog) {
    this.dataSource = new MatTableDataSource(this.clientsrailway_list);
  }

  ngOnInit(): void {
    this.getAllClients();

    this.dialog.afterAllClosed.subscribe(data => {
      this.getAllClients();
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  private getAllClients(){
    this.clientService.getAllClients().subscribe((data: Clientrailway[]) => {
      this.dataSource.data = data;
    });
  }

  editClient(id_client:number){
    const dialogRef = this.dialog.open(UpdateClientComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        },
        id_client: id_client.toString()
      },
    });
    this.dialog.afterAllClosed.subscribe(data => {
      this.getAllClients();
    });
  }

  deleteClient(id_client: number) {
      this.clientService.deleteClient(id_client).subscribe(data => {
        this.getAllClients();
       });
  }

  clientById(id_client:number){
    this.router.navigate(['clientone/',id_client]);
  }

  findclientbyname(name:string){
    if(name!=="") {
      this.clientService.findClientByName(name).subscribe( (data: Clientrailway[]) => {
        this.dataSource.data = data;
      });
    }else{
      this.getAllClients();
    }
  }

  ExportToExcel(){
    this.clientService.exportToExcel();
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.errorMsg = '';

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.clientService.importFromExcel(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              console.log(Math.round(100 * event.loaded / event.total));

            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;
            }
          },
          (err: any) => {
            console.log(err);

            if (err.error && err.error.responseMessage) {
              this.errorMsg = err.error.responseMessage;
            } else {
              this.errorMsg = 'Error occurred while uploading a file!';
            }

            this.currentFile = undefined;
            this.getAllClients();
          });
      }
      this.selectedFiles = undefined;
    }
  }

}
