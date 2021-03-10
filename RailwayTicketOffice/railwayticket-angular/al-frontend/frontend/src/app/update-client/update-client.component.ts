import {Component, Inject, OnInit} from '@angular/core';
import {ClientsService} from "../clients.service";
import {Clientrailway} from "../clientrailway";
import {ActivatedRoute, Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {

  clientUpdate:Clientrailway = new Clientrailway();

  updateClient: FormGroup;

  constructor(private clientService:ClientsService,private router:ActivatedRoute,private route:Router,
   @Inject(MAT_DIALOG_DATA) public data: any,
  private dialog:MatDialog,private formBuilder: FormBuilder) {

    const MOBILE_PATTERN = '[\+]?[(]?[3][7][5][)]?[-\s\.]?[(]?[0-9]{2}?[)][-\s\.]?[0-9]{4,8}';

    this.updateClient = this.formBuilder.group({
      clientName: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      clientSoname: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      clientPhone: ['', [Validators.required, Validators.pattern(MOBILE_PATTERN)]],
    });
  }

  ngOnInit(): void {
    this.clientService.getClientById(this.data.id_client).subscribe(data=>{
      this.clientUpdate=data;
    });
  }

  editClient() {
       this.clientService.editClient(this.clientUpdate).subscribe(data =>{
      this.dialog.closeAll();
    },error => console.log(error));
   }
}
