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
  },
  {
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
  },
  {
    path: 'pag-editar/:id',
    loadComponent: () => import('./pag-editar/pag-editar.page').then( m => m.PagEditarPage)
  },
  {
    path: 'not-rec-ad',
    loadComponent: () => import('./not-rec-ad/not-rec-ad.page').then( m => m.NotRecAdPage)
  },
  {
    path: 'evento1-ad/:id',
    loadComponent: () => import('./evento1-ad/evento1-ad.page').then( m => m.Evento1AdPage)
  },
  {
    path: 'evento2-ad',
    loadComponent: () => import('./evento2-ad/evento2-ad.page').then( m => m.Evento2AdPage)
  },
  {
    path: 'lista-eventos-ad',
    loadComponent: () => import('./lista-eventos-ad/lista-eventos-ad.page').then( m => m.ListaEventosAdPage)
  },
  {
    path: 'opiniones-ad',
    loadComponent: () => import('./opiniones-ad/opiniones-ad.page').then( m => m.OpinionesAdPage)
  },
  {
    path: 'pago-ad',
    loadComponent: () => import('./pago-ad/pago-ad.page').then( m => m.PagoAdPage)
  },
  {
    path: 'pago-aceptado-ad',
    loadComponent: () => import('./pago-aceptado-ad/pago-aceptado-ad.page').then( m => m.PagoAceptadoAdPage)
  },
  {
    path: 'pago-denegado-ad',
    loadComponent: () => import('./pago-denegado-ad/pago-denegado-ad.page').then( m => m.PagoDenegadoAdPage)
  }


];
