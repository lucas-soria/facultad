export interface IPais {
  id?: number;
  nombre?: string;
  continente?: string | null;
}

export const defaultValue: Readonly<IPais> = {};
