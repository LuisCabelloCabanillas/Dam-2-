import { Component, OnInit } from '@angular/core';
import {IonButton, IonContent} from "@ionic/angular/standalone";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-crear-iniciar-sesion-bots',
  templateUrl: './crear-iniciar-sesion-bots.component.html',
  styleUrls: ['./crear-iniciar-sesion-bots.component.scss'],
  imports: [
    IonContent,
    IonButton,
    RouterLink
  ]
})
export class CrearIniciarSesionBotsComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
