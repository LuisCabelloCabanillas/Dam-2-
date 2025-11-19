import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonContent,
  IonHeader,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-not-rec',
  templateUrl: './not-rec.page.html',
  styleUrls: ['./not-rec.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonItem, IonLabel, IonList, IonButton, RouterLink]
})
export class NotRecPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
