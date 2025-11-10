import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonContent,
  IonHeader, IonInput,
  IonItem,
  IonLabel,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-crear-sesion',
  templateUrl: './crear-sesion.page.html',
  styleUrls: ['./crear-sesion.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonCard, IonCardContent, IonItem, IonLabel, IonInput, IonButton, RouterLink]
})
export class CrearSesionPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
