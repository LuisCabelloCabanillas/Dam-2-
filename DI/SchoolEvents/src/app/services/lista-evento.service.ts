import { Injectable } from '@angular/core';
import { Http } from '@capacitor-community/http';
import { Evento } from '../models/evento';
import { from, Observable } from 'rxjs'; // Necesario para el .subscribe() del TS

@Injectable({ providedIn: 'root' })
export class ListaEventosService {

  private API = 'https://backend-q6zm.onrender.com';

  constructor() {}

  guardarFoto(datos: { foto: string, id_eventos: number }): Observable<any> {
    const options = {
      url: `${this.API}/eventos/${datos.id_eventos}/galeria`,
      headers: { 'Content-Type': 'application/json' },
      data: {
        foto: datos.foto // Este nombre debe ser igual al del DTO en Java
      }
    };
    return from(Http.post(options));
  }

  async obtenerEventos(): Promise<Evento[]> {
    const options = { url: `${this.API}/eventos/todos` };
    const res = await Http.get(options);
    return res.data as Evento[];
  }

  async detalleEvento(id: number): Promise<Evento> {
    const options = { url: `${this.API}/eventos/buscar/${id}` };
    const res = await Http.get(options);
    return res.data as Evento;
  }

  async crearEventos(evento: Evento): Promise<Evento> {
    const options = {
      url: `${this.API}/eventos/crear`,
      headers: { 'Content-Type': 'application/json' },
      data: evento
    };
    const res = await Http.post(options);
    return res.data as Evento;
  }

  async editarEvento(id: number, evento: Evento): Promise<Evento> {
    const options = {
      url: `${this.API}/eventos/actualizar/${id}`,
      headers: { 'Content-Type': 'application/json' },
      data: evento
    };
    const res = await Http.put(options);
    return res.data as Evento;
  }

  async eliminarEvento(id: number): Promise<void> {
    const options = { url: `${this.API}/eventos/eliminar/${id}` };
    await Http.del(options);
  }
}
