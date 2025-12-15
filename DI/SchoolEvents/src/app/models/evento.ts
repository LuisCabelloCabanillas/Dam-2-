import {TipoCategoria} from "../enums/TipoCategoria";

export interface Evento {
  nombre: string;
  lugar: string;
  requisitos?: string;
  fecha: string;
  consiste:string;
  categoria: TipoCategoria;
  id?: number;
}
