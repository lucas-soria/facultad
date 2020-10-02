from libroMalEstado import libroMalEstado

class Administracion(libroMalEstado):

    def update(self,libro):
        if libro.estado.upper()=="DESTRUIDO":
            print("\nADMINISTRACION\nEnvio una queja formal por libro {}, por devolucion en mal estado".format(libro.getTitulo()))
