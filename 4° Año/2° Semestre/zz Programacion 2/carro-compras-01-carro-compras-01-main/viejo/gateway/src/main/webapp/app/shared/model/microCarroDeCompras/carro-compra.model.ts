import { IProductoSeleccionado } from 'app/shared/model/microCarroDeCompras/producto-seleccionado.model';

export interface ICarroCompra {
  id?: number;
  cliente?: string | null;
  total?: number | null;
  finalizado?: boolean | null;
  vendido?: boolean | null;
  productoSeleccionados?: IProductoSeleccionado[] | null;
}

export const defaultValue: Readonly<ICarroCompra> = {
  finalizado: false,
  vendido: false,
};
