import { Component, OnInit } from '@angular/core';
import {IonButton, IonContent, IonDatetime, IonHeader, IonRouterLink} from "@ionic/angular/standalone";
import {RouterLink} from "@angular/router";
import {HeaderUser1Component} from "../header-user1/header-user1.component";
import {CrearIniciarSesionBotsComponent} from "../crear-iniciar-sesion-bots/crear-iniciar-sesion-bots.component";

@Component({
  selector: 'app-calendario-evento',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.scss'],
  standalone: true,
  imports: [
    IonContent,
    IonDatetime,
    IonButton,
    IonRouterLink,
    RouterLink,
    HeaderUser1Component,
    IonHeader,
    CrearIniciarSesionBotsComponent,
  ]
})
export class CalendarioComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
