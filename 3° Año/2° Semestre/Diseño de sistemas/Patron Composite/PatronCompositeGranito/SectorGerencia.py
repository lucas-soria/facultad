from Composite import Composite


class SectorGerencia(Composite):

    def get_cantidadGerentes(self):
        return self.cantidadGerentes

    def set_cantidadGerentes(self, cantidadGerentes):
        self.cantidadGerentes = cantidadGerentes
