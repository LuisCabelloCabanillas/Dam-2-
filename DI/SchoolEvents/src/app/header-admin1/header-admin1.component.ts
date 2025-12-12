import { Component, OnInit } from '@angular/core';
import {IonButton, IonHeader, IonToolbar} from "@ionic/angular/standalone";
import {RouterLink} from "@angular/router";

@Component({
    selector: 'app-header-admin1',
    templateUrl: './header-admin1.component.html',
    styleUrls: ['./header-admin1.component.scss'],
    imports: [
        IonButton,
        IonHeader,
        IonToolbar,
        RouterLink
    ]
})
export class HeaderAdmin1Component  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
