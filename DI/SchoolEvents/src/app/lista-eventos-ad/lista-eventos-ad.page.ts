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
    private eventoService: ListaEventosService,
    private router: Router,
    private toastCtrl: ToastController,
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

  verDetalle(id?: number) {
    if (id != null) {
      this.router.navigate(['/evento1', id]);
    }
  }

  editarEvento(id?: number) {
    if (id != null) {
      this.router.navigate(['/pag-editar', id]);
    }
  }

  async eliminarEvento(id?: number) {
    if (id == null) return;

    const alert = await this.alertCtrl.create({
      header: 'Confirmar eliminación',
      message: '¿Esta seguro de eliminar el evento?',
      buttons: [
        { text: 'Cancelar', role: 'cancel' },
        { text: 'eliminar',
          handler: () => {
          this.eventoService.eliminarEvento(id).subscribe({
            next: () => {
              this.eventos = this.eventos.filter(evento => evento.id !== id);
              },
            error: async () => {
              const toast = await this.toastCtrl.create({
                message: 'Error al eliminar el evento',
                duration: 2000,
                color: 'danger'
              });
              toast.present();
            }
          });
        }
        }
        ]
      });

      await alert.present();
      }
}
