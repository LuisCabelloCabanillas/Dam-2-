import { Component, OnInit } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonTitle,
  IonToolbar
} from "@ionic/angular/standalone";

@Component({
  selector: 'app-not-rec',
  templateUrl: './not-rec.component.html',
  styleUrls: ['./not-rec.component.scss'],
  imports: [
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonList,
    IonItem,
    IonLabel,
    IonIcon
  ]
})
export class NotRecComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
