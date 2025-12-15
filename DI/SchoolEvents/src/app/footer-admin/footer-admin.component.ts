import { Component, OnInit } from '@angular/core';
import {IonButton, IonFooter, IonIcon} from "@ionic/angular/standalone";
import {RouterLink} from "@angular/router";

@Component({
    selector: 'app-footer-admin',
    templateUrl: './footer-admin.component.html',
    styleUrls: ['./footer-admin.component.scss'],
    imports: [
        IonButton,
        IonFooter,
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
