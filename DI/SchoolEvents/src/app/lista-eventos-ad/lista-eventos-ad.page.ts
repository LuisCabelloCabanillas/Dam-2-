import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonContent, IonHeader, IonList, IonToolbar } from '@ionic/angular/standalone';
import { Router, RouterLink } from "@angular/router";
import { Evento } from "../models/evento";
import { ToastController } from "@ionic/angular";
import { ListaEventosService } from "../services/lista-evento.service";
import { ViewWillEnter } from '@ionic/angular';

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
    private eventoService: ListaEventosService,
    private router: Router,
    private toastCtrl: ToastController,
  ) { }

  ionViewWillEnter() {
    this.cargarEventos();
  }

  ngOnInit() {}

  async cargarEventos() {
    try {
      this.eventos = await this.eventoService.obtenerEventos(); // <-- async/await con Capacitor HTTP
      console.log('Eventos recibidos:', this.eventos);
    } catch (error: any) {
      const toast = await this.toastCtrl.create({
        message: error?.message || 'Error al cargar eventos',
        duration: 2000,
        color: 'danger'
      });
      toast.present();
    }
  }

}
