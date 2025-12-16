import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonSpinner, IonToolbar} from '@ionic/angular/standalone';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ListaEventosService} from "../services/lista-evento.service";
import {AlertController, ToastController} from "@ionic/angular";
import {Evento} from "../models/evento";
import {Location} from "@angular/common";

@Component({
  selector: 'app-evento1-ad',
  templateUrl: './evento1-ad.page.html',
  styleUrls: ['./evento1-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, RouterLink, IonSpinner]
})
export class Evento1AdPage implements OnInit {

  evento?: Evento;
  id!: number;

  constructor(
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private eventoService: ListaEventosService,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private toastCtrl: ToastController,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private alertCtrl: AlertController,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private route: ActivatedRoute,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private router: Router,
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private location: Location
  ) { }

  ngOnInit() {
    const paramId = this.route.snapshot.paramMap.get('id');
    if (!paramId) {
      this.mostrarToast('No se ha recibido id del evento', 'danger');
      this.router.navigate(['/lista-eventos-ad']);
      return;
    }

    this.id = Number(paramId);
    this.cargarEvento();
  }

  cargarEvento() {
    this.eventoService.detalleEvento(this.id).subscribe({
      next: (data) => {
        console.log('Evento cargado:', data);
        this.evento = data;
      },
      error: async (err) => {
        console.error('Error al cargar evento:', err);
        await this.mostrarToast('Error al cargar el evento', 'danger');
        this.router.navigate(['/lista-eventos-ad']);
      }
    });
  }

  async eliminarEvento() {
    if (!this.evento) {
      await this.mostrarToast('No hay evento cargado para eliminar', 'danger');
      return;
    }

    const alert = await this.alertCtrl.create({
      header: 'Confirmar eliminación',
      message: '¿Está seguro de eliminar el evento?',
      buttons: [
        { text: 'Cancelar', role: 'cancel' },
        { text: 'Eliminar', role: 'destructive', handler: () => {
            this.eventoService.eliminarEvento(this.id).subscribe({
              next: async () => {
                await this.mostrarToast('Evento eliminado', 'success');
                this.location.back();
              },
              error: async (err) => {
                if (err.status === 404) {
                  await this.mostrarToast('Evento ya eliminado', 'warning');
                }
                this.location.back();
              }
            });
          }
        }
      ]
    });

    await alert.present();
  }


  async mostrarToast(message: string, color: string) {
    const toast = await this.toastCtrl.create({ message, duration: 2000, color });
    toast.present();
  }

  getFechaFormateada(fecha: string):string {
    return new Date(fecha).toLocaleDateString();
  }
}
