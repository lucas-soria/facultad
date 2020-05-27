from AlarmaLibro import AlarmaLibro


class Biblioteca:

    def devuelveLibro(self, libro):
        if libro.getEstado() == "MALO":
            a = AlarmaLibro()
            a.notifyObservers()
