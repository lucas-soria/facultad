import { ICarroCompra } from 'app/shared/model/microCarroDeCompras/carro-compra.model';

export interface IProductoSeleccionado {
  id?: number;
  producto?: number | null;
  precio?: number | null;
  cantidad?: number | null;
  carro?: ICarroCompra | null;
}

export const defaultValue: Readonly<IProductoSeleccionado> = {};
