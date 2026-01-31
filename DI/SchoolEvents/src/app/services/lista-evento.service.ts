import { Injectable } from '@angular/core';
import { Http } from '@capacitor-community/http';
import { Evento } from '../models/evento';

@Injectable({ providedIn: 'root' })
export class ListaEventosService {

  private API = 'https://backend-q6zm.onrender.com';

  async obtenerEventos(): Promise<Evento[]> {
    const options = {
      url: `${this.API}/eventos/todos`,
      headers: { 'Content-Type': 'application/json' }
    };
    const response = await Http.request({ method: 'GET', ...options });
    return response.data as Evento[];
  }

  async crearEvento(evento: Evento): Promise<Evento> {
    const options = {
      url: `${this.API}/eventos/crear`,
      headers: { 'Content-Type': 'application/json' },
      data: evento
    };
    const response = await Http.request({ method: 'POST', ...options });
    return response.data as Evento;
  }

  async eliminarEvento(id: number): Promise<void> {
    const options = {
      url: `${this.API}/eventos/eliminar/${id}`,
      headers: { 'Content-Type': 'application/json' }
    };
    await Http.request({ method: 'DELETE', ...options });
  }

  async detalleEvento(id: number): Promise<Evento> {
    const options = {
      url: `${this.API}/eventos/buscar/${id}`,
      headers: { 'Content-Type': 'application/json' }
    };
    const response = await Http.request({ method: 'GET', ...options });
    return response.data as Evento;
  }

  async editarEvento(id: number, evento: Evento): Promise<Evento> {
    const options = {
      url: `${this.API}/eventos/actualizar/${id}`,
      headers: { 'Content-Type': 'application/json' },
      data: evento
    };
    const response = await Http.request({ method: 'PUT', ...options });
    return response.data as Evento;
  }
}
