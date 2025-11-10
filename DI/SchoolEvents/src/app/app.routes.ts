import { Routes } from '@angular/router';
import {InicioComponent} from "./inicio/inicio.component";

export const routes: Routes = [

  {path:'', component: InicioComponent},
  {
    path: 'crear-sesion',
    loadComponent: () => import('./crear-sesion/crear-sesion.page').then( m => m.CrearSesionPage)
  },
  {
    path: 'iniciar-sesion',
    loadComponent: () => import('./iniciar-sesion/iniciar-sesion.page').then( m => m.IniciarSesionPage)
  },
  {
    path: 'pag1-user',
    loadComponent: () => import('./pag1-user/pag1-user.page').then( m => m.Pag1UserPage)
  },
];
