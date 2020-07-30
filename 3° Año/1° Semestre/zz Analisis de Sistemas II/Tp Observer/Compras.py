from ILibroMalEstado import ILibroMalEstado


class Compras(ILibroMalEstado):

    def __init__(self):
       ILibroMalEstado.__init__(self)

    def update(self):
        print("\nCompras:\n\tSolicito nueva cotizacion...")
