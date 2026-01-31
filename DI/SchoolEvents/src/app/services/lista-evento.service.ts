import { Injectable } from '@angular/core';
import { Http } from '@capacitor-community/http';
import { Evento } from '../models/evento';

@Injectable({ providedIn: 'root' })
export class ListaEventosService {

  private API = 'https://backend-q6zm.onrender.com';

  constructor() {}

  async obtenerEventos(): Promise<Evento[]> {
    const response = await Http.get({ url: `${this.API}/eventos/todos` });
    return response.data as Evento[];
  }

  async detalleEvento(id: number): Promise<Evento> {
    const response = await Http.get({ url: `${this.API}/eventos/buscar/${id}` });
    return response.data as Evento;
  }

  async crearEventos(evento: Evento) {
    const response = await Http.post({ url: `${this.API}/eventos/crear`, data: evento });
    return response.data;
  }

  async editarEvento(id: number, evento: Evento) {
    const response = await Http.put({ url: `${this.API}/eventos/actualizar/${id}`, data: evento });
    return response.data;
  }

  async eliminarEvento(id: number) {
    const response = await Http.request({
      method: 'DELETE',
      url: `${this.API}/eventos/eliminar/${id}`
    });
    return response.data;
  }
}
