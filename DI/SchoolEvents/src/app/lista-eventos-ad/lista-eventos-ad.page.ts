import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonList, IonToolbar} from '@ionic/angular/standalone';
import {Router, RouterLink} from "@angular/router";
import {Evento} from "../models/evento";
import {ToastController} from "@ionic/angular";
import {ListaEventosService} from "../services/lista-evento.service";
import {ViewWillEnter} from '@ionic/angular';

@Component({
  selector: 'app-lista-eventos-ad',
  templateUrl: './lista-eventos-ad.page.html',
  styleUrls: ['./lista-eventos-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, IonList, RouterLink]
})
export class ListaEventosAdPage implements ViewWillEnter, OnInit {

  eventos: Evento[] = [];

  constructor(
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private eventoService: ListaEventosService,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private router: Router,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private toastCtrl: ToastController,
    // eslint-disable-next-line @angular-eslint/prefer-inject
  ) { }

  ionViewWillEnter() {
    this.cargarEventos();
  }

  ngOnInit() {
  }

  cargarEventos() {
    this.eventoService.obtenerEventos().subscribe({
      next: (data) =>{
        console.log('Eventos recibidos:', data);
        this.eventos = data
      },
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

}
