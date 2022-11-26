import { IProducto } from 'app/shared/model/microProductos/producto.model';

export interface IDistribuidor {
  id?: number;
  nombre?: string | null;
  descripcion?: string | null;
  habilitado?: boolean | null;
  productos?: IProducto[] | null;
}

export const defaultValue: Readonly<IDistribuidor> = {
  habilitado: false,
};
