import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ClientrailwayListComponent} from "./clientrailway-list/clientrailway-list.component";
import {BuyTicketComponent} from "./buy-ticket/buy-ticket.component";
import {TrainListComponent} from "./train-list/train-list.component";
import {UpdateClientComponent} from "./update-client/update-client.component";
import {ClientDetailsComponent} from "./client-details/client-details.component";
import {TrainCreateComponent} from "./train-create/train-create.component";
import {UpdateTrainComponent} from "./update-train/update-train.component";
import {TrainDetailsComponent} from "./train-details/train-details.component";
import {TrainResultfindComponent} from "./train-resultfind/train-resultfind.component";
import {TrainFindComponent} from "./train-find/train-find.component";
import {ContactUSComponent} from "./contact-us/contact-us.component";


const routes: Routes = [
  {path:'allClient',component:ClientrailwayListComponent},
  {path:'',redirectTo: 'allClient',pathMatch: 'full'},
  {path:'buyTicket/:id_train',component:BuyTicketComponent},
  {path:'allTrain',component:TrainListComponent},
  {path:'updateClient/:id_client',component:UpdateClientComponent},
  {path:'clientone/:id_client',component:ClientDetailsComponent},
  {path:'createTrain',component:TrainCreateComponent},
  {path:'updateTrain/:id_train',component:UpdateTrainComponent},
  {path:'trainone/:id_train',component:TrainDetailsComponent},
  {path:'resultfind/:arrivalDate/:arrivalStationFind/:departureDate/:departureStationFind',component:TrainResultfindComponent},
  {path:'trainfind', component:TrainFindComponent},
  {path:'contactus', component:ContactUSComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
