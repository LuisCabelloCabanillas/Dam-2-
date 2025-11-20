import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-pago-aceptado',
  templateUrl: './pago-aceptado.page.html',
  styleUrls: ['./pago-aceptado.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButton, RouterLink]
})
export class PagoAceptadoPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
