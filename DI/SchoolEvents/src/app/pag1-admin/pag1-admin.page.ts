import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonContent,
  IonDatetime,
  IonFooter,
  IonHeader,

} from '@ionic/angular/standalone';
import {HeaderUser1Component} from "../header-user1/header-user1.component";
import {RouterLink} from "@angular/router";
import {FooterComponent} from "../footer-admin/footer-admin.component";

@Component({
  selector: 'app-pag1-admin',
  templateUrl: './pag1-admin.page.html',
  styleUrls: ['./pag1-admin.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, CommonModule, FormsModule, FooterComponent, HeaderUser1Component, IonButton, IonDatetime, IonFooter, RouterLink, FooterComponent]
})
export class Pag1AdminPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
