import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton,
  IonContent,
  IonDatetime, IonFooter,
  IonHeader, IonIcon,
  IonRouterLink,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import {RouterLink} from "@angular/router";
import {FooterComponent} from "../footer-user/footer.component";
import {HeaderUser1Component} from "../header-user1/header-user1.component";
import {CalendarioComponent} from "../calendario-evento/calendario.component";

@Component({
  selector: 'app-pag1-user',
  templateUrl: './pag1-user.page.html',
  styleUrls: ['./pag1-user.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButton, IonDatetime, IonRouterLink, RouterLink, IonIcon, FooterComponent, IonFooter, HeaderUser1Component, CalendarioComponent]
})
export class Pag1UserPage implements OnInit {
  menuOpen = false;

  constructor() { }

  ngOnInit(): void {
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
}
