import { Injectable } from '@angular/core';
import { Http } from '@capacitor-community/http';
import { Evento } from '../models/evento';

@Injectable({ providedIn: 'root' })
export class ListaEventosService {

  private API = 'https://backend-q6zm.onrender.com';

  async obtenerEventos(): Promise<Evento[]> {
    const res = await Http.get({ url: `${this.API}/eventos/todos` });
    return res.data as Evento[];
  }

  async crearEventos(evento: Evento): Promise<Evento> {
    const res = await Http.post({ url: `${this.API}/eventos/crear`, data: evento });
    return res.data as Evento;
  }

  async editarEvento(id: number, evento: Evento): Promise<Evento> {
    const res = await Http.put({ url: `${this.API}/eventos/actualizar/${id}`, data: evento });
    return res.data as Evento;
  }

  async detalleEvento(id: number): Promise<Evento> {
    const res = await Http.get({ url: `${this.API}/eventos/buscar/${id}` });
    return res.data as Evento;
  }

  async eliminarEvento(id: number): Promise<void> {
    await Http.del({ url: `${this.API}/eventos/eliminar/${id}` });
  }
}
