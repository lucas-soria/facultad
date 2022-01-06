import { IPais } from 'app/shared/model/pais.model';

export interface IProvincia {
  id?: number;
  nombre?: string;
  codigoPostal?: string;
  pais?: IPais;
}

export const defaultValue: Readonly<IProvincia> = {};
