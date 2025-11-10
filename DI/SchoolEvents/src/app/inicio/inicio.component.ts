import { Component, OnInit } from '@angular/core';
import {IonContent} from "@ionic/angular/standalone";
import {IonicModule} from "@ionic/angular";
import {CalendarioComponent} from "../calendario-evento/calendario.component";
import {EventosInstitutoComponent} from "../eventos-instituto/eventos-instituto.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss'],
  standalone: true,
  imports: [
    IonicModule,
    CalendarioComponent,
    EventosInstitutoComponent,
    RouterLink,
  ]
})
export class InicioComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
