import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonContent, IonHeader, IonInput, IonSelect, IonSelectOption, IonTextarea, IonToolbar } from '@ionic/angular/standalone';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ListaEventosService } from "../services/lista-evento.service";
import { Evento } from "../models/evento";
import { TipoCategoria } from "../enums/TipoCategoria";
import { ToastController } from "@ionic/angular";

@Component({
  selector: 'app-pag-editar',
  templateUrl: './pag-editar.page.html',
  styleUrls: ['./pag-editar.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, IonInput, IonTextarea, RouterLink, IonSelect, IonSelectOption]
})
export class PagEditarPage implements OnInit {

  TipoCategoria = TipoCategoria;
  evento: Evento = {
    nombre: '',
    lugar: '',
    requisitos: '',
    fecha: '',
    consiste: '',
    categoria: TipoCategoria.otros
  };
  id: number | null = null;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private eventoService: ListaEventosService,
    private toastCtrl: ToastController
  ) { }

  ngOnInit() {
    const paramId = this.route.snapshot.paramMap.get('id');
    this.id = paramId ? Number(paramId) : null;
    if (this.id) this.cargarEvento(this.id);
  }

  async cargarEvento(id: number) {
    try {
      this.evento = await this.eventoService.detalleEvento(id);
    } catch (err) {
      console.error('Error al cargar evento', err);
    }
  }

  async guardarCambios() {
    if (!this.id) return;
    try {
      await this.eventoService.editarEvento(this.id, this.evento);
      await this.mostrarToast('Evento editado', 'success');
      this.router.navigate(['/lista-eventos-ad']);
    } catch (err) {
      console.error('Error al editar evento', err);
    }
  }

  async mostrarToast(message: string, color: string) {
    const toast = await this.toastCtrl.create({ message, duration: 2000, color });
    toast.present();
  }
}
