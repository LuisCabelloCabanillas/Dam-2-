import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-pago-denegado-ad',
  templateUrl: './pago-denegado-ad.page.html',
  styleUrls: ['./pago-denegado-ad.page.scss'],
  standalone: true,
  imports: [IonContent, CommonModule, FormsModule, IonButton, RouterLink]
})
export class PagoDenegadoAdPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
