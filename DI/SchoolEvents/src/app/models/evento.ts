import {TipoCategoria} from "../enums/TipoCategoria";

export interface Evento {
  id?: number;
  nombre: string;
  lugar: string;
  fecha: string;
  consiste:string;
  requisitos?: string;
  categoria: TipoCategoria;

}
