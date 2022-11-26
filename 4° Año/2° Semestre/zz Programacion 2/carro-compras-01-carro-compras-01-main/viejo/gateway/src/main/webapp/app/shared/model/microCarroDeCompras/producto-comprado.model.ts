import { IVenta } from 'app/shared/model/microCarroDeCompras/venta.model';

export interface IProductoComprado {
  id?: number;
  producto?: number | null;
  precio?: number | null;
  cantidad?: number | null;
  venta?: IVenta | null;
}

export const defaultValue: Readonly<IProductoComprado> = {};
