from libroMalEstado import libroMalEstado

class Stock(libroMalEstado):
    def update(self,libro):
        if libro.estado.upper()=="DESTRUIDO":
            print("\nSTOCK\nDoy de baja el libro {}, por devolucion en mal estado".format(libro.getTitulo()))