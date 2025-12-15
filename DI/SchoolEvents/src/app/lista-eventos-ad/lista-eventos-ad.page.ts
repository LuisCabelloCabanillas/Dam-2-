import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonList, IonToolbar} from '@ionic/angular/standalone';
import {Router, RouterLink} from "@angular/router";
import {Evento} from "../models/evento";
import {AlertController, ToastController} from "@ionic/angular";
import {ListaEventosService} from "../services/lista-evento.service";

@Component({
  selector: 'app-lista-eventos-ad',
  templateUrl: './lista-eventos-ad.page.html',
  styleUrls: ['./lista-eventos-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, IonList, RouterLink]
})
export class ListaEventosAdPage implements OnInit {

  eventos: Evento[] = [];

  constructor(
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private eventoService: ListaEventosService,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private router: Router,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private toastCtrl: ToastController,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private alertCtrl: AlertController
  ) { }

  ngOnInit() {
    this.cargarEventos();
  }

  cargarEventos() {
    this.eventoService.obtenerEventos().subscribe({
      next: (evento) => this.eventos = evento,
      error: async (error) => {
        const toast = await this.toastCtrl.create({
          message: error.message,
          duration: 2000,
          color: 'danger'
        });
        toast.present();
      }
    });
  }


  editarEvento(id?: number) {
    if (id != null) {
      this.router.navigate(['/pag-editar', id]);
    }
  }

}
