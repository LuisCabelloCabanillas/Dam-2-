import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonButton, IonContent, IonHeader, IonInput, IonTextarea, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-pag-editar',
  templateUrl: './pag-editar.page.html',
  styleUrls: ['./pag-editar.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonToolbar, CommonModule, FormsModule, IonButton, IonInput, IonTextarea, RouterLink]
})
export class PagEditarPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
