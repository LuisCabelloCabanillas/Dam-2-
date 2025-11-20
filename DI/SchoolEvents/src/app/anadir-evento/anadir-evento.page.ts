import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonDatetime, IonHeader, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-anadir-evento',
  templateUrl: './anadir-evento.page.html',
  styleUrls: ['./anadir-evento.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonDatetime, IonButton, RouterLink]
})
export class AnadirEventoPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
