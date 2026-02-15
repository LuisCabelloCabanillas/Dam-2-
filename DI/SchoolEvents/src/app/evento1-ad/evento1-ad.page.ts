import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonContent, IonHeader, IonIcon, IonSpinner, IonToolbar, AlertController, ToastController } from '@ionic/angular/standalone';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { ListaEventosService } from "../services/lista-evento.service";
import { Evento } from "../models/evento";
import { Location } from "@angular/common";

// IMPORTACIONES PARA LA CÁMARA
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { addIcons } from 'ionicons';
import { camera } from 'ionicons/icons';

@Component({
  selector: 'app-evento1-ad',
  templateUrl: './evento1-ad.page.html',
  styleUrls: ['./evento1-ad.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, RouterLink, IonSpinner, IonIcon]
})
export class Evento1AdPage implements OnInit {

  evento?: Evento;
  id!: number;

  // Variable para que la imagen cambie en la pantalla
  imagenSeleccionada: string | undefined;

  constructor(
    private eventoService: ListaEventosService,
    private toastCtrl: ToastController,
    private alertCtrl: AlertController,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) {
    // Registramos el icono de la cámara
    addIcons({ camera });
  }

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

  // --- LÓGICA DE LA CÁMARA PARA TU IPHONE ---
  async subirFoto(idEvento: number) {
    try {
      const image = await Camera.getPhoto({
        quality: 50, // Calidad media para no saturar PostgreSQL en Render
        allowEditing: false,
        resultType: CameraResultType.Base64, // Obtenemos el string para el HTML y la BD
        source: CameraSource.Prompt // Esto permite elegir Cámara o Galería en Safari
      });

      if (image.base64String) {
        // 1. Actualizamos la vista previa inmediatamente
        this.imagenSeleccionada = image.base64String;

        // 2. Preparamos el objeto para enviar a Spring Boot
        const datosFoto = {
          foto: image.base64String,
          id_eventos: idEvento
        };

        this.eventoService.guardarFoto(datosFoto).subscribe({
          next: () => {
            this.mostrarToast('Foto guardada correctamente en el evento', 'success');
          },
          error: (err: any) => {
            console.error('Error al guardar en la base de datos:', err);
            this.mostrarToast('Error al subir la foto a la base de datos', 'danger');
          }
        });
      }
    } catch (error) {
      console.log('El usuario canceló la captura');
    }
  }

  async cargarEvento() {
    try {
      this.evento = await this.eventoService.detalleEvento(this.id);
    } catch (err) {
      console.error(err);
      await this.mostrarToast('Error al cargar el evento', 'danger');
      this.router.navigate(['/lista-eventos-ad']);
    }
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
        { text: 'Eliminar', role: 'destructive', handler: async () => {
            try {
              await this.eventoService.eliminarEvento(this.id);
              await this.mostrarToast('Evento eliminado', 'success');
              this.location.back();
            } catch (err: any) {
              console.error(err);
              if (err?.status === 404) {
                await this.mostrarToast('Evento ya eliminado', 'warning');
              }
              this.location.back();
            }
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

  getFechaFormateada(fecha: string): string {
    return new Date(fecha).toLocaleDateString();
  }
}
