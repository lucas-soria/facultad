import dayjs from 'dayjs';
import { ICarroCompra } from 'app/shared/model/carro-compra.model';
import { IUser } from 'app/shared/model/user.model';

export interface IVenta {
  id?: number;
  fecha?: string | null;
  total?: number | null;
  carro?: ICarroCompra | null;
  cliente?: IUser | null;
}

export const defaultValue: Readonly<IVenta> = {};
