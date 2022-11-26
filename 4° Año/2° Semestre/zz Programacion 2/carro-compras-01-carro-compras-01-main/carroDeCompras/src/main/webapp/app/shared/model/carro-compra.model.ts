import { IUser } from 'app/shared/model/user.model';

export interface ICarroCompra {
  id?: number;
  total?: number | null;
  finalizado?: boolean | null;
  vendido?: boolean | null;
  cliente?: IUser | null;
}

export const defaultValue: Readonly<ICarroCompra> = {
  finalizado: false,
  vendido: false,
};
