import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonContent, IonFooter,
  IonHeader,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-opiniones-ad',
  templateUrl: './opiniones-ad.page.html',
  styleUrls: ['./opiniones-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, IonCard, IonCardContent, IonFooter, RouterLink]
})
export class OpinionesAdPage implements OnInit {

  constructor() { }


  bubbleVisible = false;
  nombre = '';
  comentario = '';

  toggleBubble() {
    this.bubbleVisible = !this.bubbleVisible;
  }

  enviarOpinion() {
    console.log("Opinion enviada:", this.nombre, this.comentario);

    this.bubbleVisible = false;

    this.nombre = '';
    this.comentario = '';
  }
  ngOnInit() {
  }

}
