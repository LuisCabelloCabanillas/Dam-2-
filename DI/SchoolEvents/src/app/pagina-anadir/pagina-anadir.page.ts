import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonInput, IonTextarea, IonToolbar} from '@ionic/angular/standalone';
import {Router, RouterLink} from "@angular/router";
import {Evento} from "../models/evento";
import {TipoCategoria} from "../enums/TipoCategoria";

@Component({
  selector: 'app-pagina-anadir',
  templateUrl: './pagina-anadir.page.html',
  styleUrls: ['./pagina-anadir.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonInput, IonTextarea, IonButton, RouterLink]
})
export class PaginaAnadirPage implements OnInit {

  constructor(
    // eslint-disable-next-line @angular-eslint/prefer-inject
    private router: Router
  // eslint-disable-next-line @angular-eslint/prefer-inject
  private eventoService: ListaEventosService,
  // eslint-disable-next-line @angular-eslint/prefer-inject
  private toastCtrl: ToastController
  ) { }

  ngOnInit() {
  }

  TipoCategoria = TipoCategoria
  evento: Evento = {
    nombre: '',
    lugar: '',
    requisitos: '',
    fecha: '',
    consiste: '',
    categoria: TipoCategoria.otros
  }

  crearEvento() {
   this
  }


}
