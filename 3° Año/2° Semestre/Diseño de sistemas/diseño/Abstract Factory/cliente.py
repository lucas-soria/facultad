from sony_fabrica import SonyFabrica
from samsung_fabrica import SamsungFabrica


class Cliente:
    def codigo_cliente(self, fabrica):
        televisor = fabrica.crear_televisor()
        celular = fabrica.crear_celular()
        print(televisor.funcion_televisor())
        print(celular.funcion_celular())


if __name__ == "__main__":
    c = Cliente()
    print("Fabrica Sony: ")
    c.codigo_cliente(SonyFabrica())
    print("\n")
    print("Fabrica Samsung: ")
    c.codigo_cliente(SamsungFabrica())
