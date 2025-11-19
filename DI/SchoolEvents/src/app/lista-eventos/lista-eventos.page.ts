import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonItem, IonList, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-lista-eventos',
  templateUrl: './lista-eventos.page.html',
  styleUrls: ['./lista-eventos.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, RouterLink, IonList]
})
export class ListaEventosPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
