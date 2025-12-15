import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Evento } from "../models/evento";

@Injectable({ providedIn: 'root' })
export class ListaEventosService {

  private API = "/api/eventos";

  // eslint-disable-next-line @angular-eslint/prefer-inject
  constructor(private http: HttpClient) {}

  obtenerEventos(): Observable<Evento[]> {
    return this.http.get<Evento[]>(this.API + "/todos");
  }

  crearEventos(evento: Evento): Observable<any> {
    return this.http.post(this.API + "/crear", evento);
  }

  editarEvento(id: number, evento: Evento): Observable<any> {
    return this.http.put(this.API + "/actualizar/" + id, evento);
  }

  detalleEvento(id: number): Observable<Evento> {
    return this.http.get<Evento>(this.API + "/buscar/" + id);
  }

  eliminarEvento(id: number): Observable<any> {
    return this.http.delete(`${this.API}/eliminar/${id}`);
  }

}
