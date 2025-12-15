import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonContent,
  IonHeader,
  IonItem,
  IonLabel,
  IonList,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-not-rec-ad',
  templateUrl: './not-rec-ad.page.html',
  styleUrls: ['./not-rec-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, IonItem, IonLabel, IonList, RouterLink]
})
export class NotRecAdPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
