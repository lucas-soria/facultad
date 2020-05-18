from ILibroMalEstado import ILibroMalEstado


class Stock(ILibroMalEstado):

    def __init__(self):
       ILibroMalEstado.__init__(self)

    def update(self):
        print("\nStock:\n\tLe doy de baja...")
