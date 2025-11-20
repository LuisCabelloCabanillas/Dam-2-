import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonInput, IonTextarea, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-pagina-anadir',
  templateUrl: './pagina-anadir.page.html',
  styleUrls: ['./pagina-anadir.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonInput, IonTextarea, IonButton, RouterLink]
})
export class PaginaAnadirPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
