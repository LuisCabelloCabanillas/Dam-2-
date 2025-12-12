import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonList, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-lista-eventos-ad',
  templateUrl: './lista-eventos-ad.page.html',
  styleUrls: ['./lista-eventos-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButton, IonList, RouterLink]
})
export class ListaEventosAdPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
