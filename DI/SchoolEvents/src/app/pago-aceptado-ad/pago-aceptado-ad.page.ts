import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-pago-aceptado-ad',
  templateUrl: './pago-aceptado-ad.page.html',
  styleUrls: ['./pago-aceptado-ad.page.scss'],
  standalone: true,
  imports: [IonContent, CommonModule, FormsModule, IonButton, RouterLink]
})
export class PagoAceptadoAdPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
