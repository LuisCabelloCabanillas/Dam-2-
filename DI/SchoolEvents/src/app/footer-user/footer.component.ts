import { Component, OnInit } from '@angular/core';
import {IonButton, IonContent, IonFooter, IonIcon} from "@ionic/angular/standalone";
import {RouterLink} from "@angular/router";

@Component({
    selector: 'app-footer-user',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.scss'],
  imports: [
    IonButton,
    IonIcon,
    IonFooter,
    IonContent,
    RouterLink
  ]
})
export class FooterComponent  implements OnInit {

  menuOpen = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  ngOnInit() {}

}
