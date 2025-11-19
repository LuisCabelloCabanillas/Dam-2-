import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonContent, IonFooter,
  IonHeader,
  IonIcon,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-opiniones',
  templateUrl: './opiniones.page.html',
  styleUrls: ['./opiniones.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonCard, IonCardContent, IonButton, RouterLink, IonFooter]
})
export class OpinionesPage implements OnInit {

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
