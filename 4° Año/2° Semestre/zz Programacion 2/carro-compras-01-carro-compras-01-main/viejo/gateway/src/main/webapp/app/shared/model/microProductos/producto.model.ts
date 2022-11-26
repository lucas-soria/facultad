import { IDistribuidor } from 'app/shared/model/microProductos/distribuidor.model';

export interface IProducto {
  id?: number;
  nombre?: string | null;
  descripcion?: string | null;
  precio?: number | null;
  cantidadVendidos?: number | null;
  habilitado?: boolean | null;
  distribuidor?: IDistribuidor | null;
}

export const defaultValue: Readonly<IProducto> = {
  habilitado: false,
};
