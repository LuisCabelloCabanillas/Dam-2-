import { Component, OnInit } from '@angular/core';
import {IonButton, IonHeader, IonToolbar} from "@ionic/angular/standalone";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-header-user1',
  templateUrl: './header-user1.component.html',
  styleUrls: ['./header-user1.component.scss'],
  imports: [
    IonToolbar,
    IonHeader,
    IonButton,
    RouterLink
  ]
})
export class HeaderUser1Component  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
