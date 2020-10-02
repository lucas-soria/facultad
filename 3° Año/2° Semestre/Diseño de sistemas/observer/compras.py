from libroMalEstado import libroMalEstado

class Compras (libroMalEstado):

    def update(self,libro):
            if libro.estado.upper()=="DESTRUIDO":
                print("\nCOMPRAS\nSolicito nueva cotizacion para el libro {}".format(libro.getTitulo()))
