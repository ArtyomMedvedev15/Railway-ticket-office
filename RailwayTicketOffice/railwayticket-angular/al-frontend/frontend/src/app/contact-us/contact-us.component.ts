import { Component, OnInit } from '@angular/core';
import {ClientsService} from "../clients.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUSComponent implements OnInit {
  emailSend: string;
  subject: string;
  messages: string;
  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';
  constructor(private clientService: ClientsService, private router: Router) { }

  ngOnInit(): void {
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  send(): void {
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.clientService.sendmail(this.emailSend,this.subject,this.messages,this.currentFile).subscribe(
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
          });
      }
      this.selectedFiles = undefined;
    }
    this.router.navigate(['contactus']);

  }

}
