import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import { AppComponent } from './app.component';
import { ClientrailwayListComponent } from './clientrailway-list/clientrailway-list.component';
import {AppRoutingModule} from "./app-routing.module";
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';
import { TrainListComponent } from './train-list/train-list.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { UpdateClientComponent } from './update-client/update-client.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { TrainCreateComponent } from './train-create/train-create.component';
import { UpdateTrainComponent } from './update-train/update-train.component';
import { TrainDetailsComponent } from './train-details/train-details.component';
import { TrainFindComponent } from './train-find/train-find.component';
import { TrainResultfindComponent } from './train-resultfind/train-resultfind.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatSelectModule} from "@angular/material/select";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatSortModule} from "@angular/material/sort";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatDialogModule} from "@angular/material/dialog";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from "@angular/material/card";
import {MatDividerModule} from "@angular/material/divider";
import {NgxMatFileInputModule} from "@angular-material-components/file-input";
import { ContactUSComponent } from './contact-us/contact-us.component';
import {MatMenuModule} from "@angular/material/menu";

@NgModule({
  declarations: [
    AppComponent,
    ClientrailwayListComponent,
    BuyTicketComponent,
    TrainListComponent,
    UpdateClientComponent,
    ClientDetailsComponent,
    TrainCreateComponent,
    UpdateTrainComponent,
    TrainDetailsComponent,
    TrainFindComponent,
    TrainResultfindComponent,
    ContactUSComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        MatPaginatorModule,
        MatTableModule,
        MatSelectModule,
        MatIconModule,
        MatButtonModule,
        MatSortModule,
        MatSidenavModule,
        MatDialogModule,
        MatDatepickerModule,
        ReactiveFormsModule,
        MatInputModule,
        MatCardModule,
        MatDividerModule,
        NgxMatFileInputModule,
        MatMenuModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
