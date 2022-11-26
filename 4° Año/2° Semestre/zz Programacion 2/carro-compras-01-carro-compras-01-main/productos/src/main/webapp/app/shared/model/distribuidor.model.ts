export interface IDistribuidor {
  id?: number;
  nombre?: string | null;
  descripcion?: string | null;
  habilitado?: boolean | null;
}

export const defaultValue: Readonly<IDistribuidor> = {
  habilitado: false,
};
