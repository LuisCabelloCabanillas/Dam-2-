import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Evento } from "../models/evento";

@Injectable({ providedIn: 'root' })
export class ListaEventosService {

  private API = "https://backend-q6zm.onrender.com";

  // eslint-disable-next-line @angular-eslint/prefer-inject
  constructor(private http: HttpClient) {}

  obtenerEventos(): Observable<Evento[]> {
    return this.http.get<Evento[]>(this.API + "/eventos/todos", { withCredentials: false });
  }

  crearEventos(evento: Evento): Observable<any> {
    return this.http.post(this.API + "/eventos/crear", evento, { withCredentials: false });
  }

  editarEvento(id: number, evento: Evento): Observable<any> {
    return this.http.put(this.API + "/eventos/actualizar/" + id, evento, { withCredentials: false });
  }

  detalleEvento(id: number): Observable<Evento> {
    return this.http.get<Evento>(this.API + "/eventos/buscar/" + id, { withCredentials: false });
  }

  eliminarEvento(id: number): Observable<any> {
    return this.http.delete(`${this.API}/eventos/eliminar/${id}`, { withCredentials: false });
  }

}
