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
  {
    path: 'not-rec',
    loadComponent: () => import('./not-rec/not-rec.page').then( m => m.NotRecPage)
  },
  {
    path: 'opiniones',
    loadComponent: () => import('./opiniones/opiniones.page').then( m => m.OpinionesPage)
  },
  {
    path: 'lista-eventos',
    loadComponent: () => import('./lista-eventos/lista-eventos.page').then( m => m.ListaEventosPage)
  },
  {
    path: 'evento1',
    loadComponent: () => import('./evento1/evento1.page').then( m => m.Evento1Page)
  },  {
    path: 'pago',
    loadComponent: () => import('./pago/pago.page').then( m => m.PagoPage)
  },
  {
    path: 'evento2',
    loadComponent: () => import('./evento2/evento2.page').then( m => m.Evento2Page)
  },
  {
    path: 'pago-aceptado',
    loadComponent: () => import('./pago-aceptado/pago-aceptado.page').then( m => m.PagoAceptadoPage)
  },
  {
    path: 'pago-denegado',
    loadComponent: () => import('./pago-denegado/pago-denegado.page').then( m => m.PagoDenegadoPage)
  },
  {
    path: 'pag1-admin',
    loadComponent: () => import('./pag1-admin/pag1-admin.page').then( m => m.Pag1AdminPage)
  },
  {
    path: 'anadir-evento',
    loadComponent: () => import('./anadir-evento/anadir-evento.page').then( m => m.AnadirEventoPage)
  },
  {
    path: 'pagina-anadir',
    loadComponent: () => import('./pagina-anadir/pagina-anadir.page').then( m => m.PaginaAnadirPage)
  }


];
