import dayjs from 'dayjs';
import { ICarroCompra } from 'app/shared/model/microCarroDeCompras/carro-compra.model';
import { IProductoComprado } from 'app/shared/model/microCarroDeCompras/producto-comprado.model';

export interface IVenta {
  id?: number;
  cliente?: string | null;
  fecha?: string | null;
  total?: number | null;
  carro?: ICarroCompra | null;
  productoComprados?: IProductoComprado[] | null;
}

export const defaultValue: Readonly<IVenta> = {};
