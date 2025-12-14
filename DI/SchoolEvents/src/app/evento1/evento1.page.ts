import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonToolbar} from '@ionic/angular/standalone';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Evento} from "../models/evento";
import {ListaEventosService} from "../services/lista-evento.service";

@Component({
  selector: 'app-evento1',
  templateUrl: './evento1.page.html',
  styleUrls: ['./evento1.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, RouterLink]
})
export class Evento1Page implements OnInit {

  evento?: Evento;

  constructor(
    private route: ActivatedRoute,
    private eventoService: ListaEventosService
  ) { }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.eventoService.detalleEvento(id).subscribe(evento => this.evento = evento);
    }
  }

}
