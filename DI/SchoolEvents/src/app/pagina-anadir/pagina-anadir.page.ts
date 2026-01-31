import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {
  IonButton,
  IonContent,
  IonHeader,
  IonInput,
  IonSelect, IonSelectOption,
  IonTextarea,
  IonToolbar
} from '@ionic/angular/standalone';
import {Router, RouterLink} from "@angular/router";
import {Evento} from "../models/evento";
import {TipoCategoria} from "../enums/TipoCategoria";
import {ListaEventosService} from "../services/lista-evento.service";
import {ToastController} from "@ionic/angular";

@Component({
  selector: 'app-pagina-anadir',
  templateUrl: './pagina-anadir.page.html',
  styleUrls: ['./pagina-anadir.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonInput, IonTextarea, IonButton, RouterLink, IonSelect, IonSelectOption]
})
export class PaginaAnadirPage implements OnInit {

  TipoCategoria = TipoCategoria;

  evento: Evento = {
    nombre: '',
    lugar: '',
    requisitos: '',
    fecha: '',
    consiste: '',
    categoria: TipoCategoria.otros
  };

  constructor(
    private router: Router,
    private eventoService: ListaEventosService,
    private toastCtrl: ToastController
  ) { }

  ngOnInit() {}

  async crearEvento() {
    try {
      const data = await this.eventoService.crearEvento(this.evento);
      await this.mostrarToast('Evento creado correctamente', 'success');
      this.evento = data;
      await this.router.navigate(['/lista-eventos-ad']);
    } catch (err) {
      await this.mostrarToast('Error al crear el evento', 'danger');
      await this.router.navigate(['/anadir-evento']);
    }
  }

  async mostrarToast(message: string, color: string) {
    const toast = await this.toastCtrl.create({ message, duration: 2000, color });
    toast.present();
  }
}
