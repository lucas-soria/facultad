import { IPais } from 'app/shared/model/pais.model';

export interface IProvincia {
  id?: number;
  nombre?: string | null;
  codigoPostal?: string | null;
  pais?: IPais | null;
}

export const defaultValue: Readonly<IProvincia> = {};
