from Composite import Composite


class SectorContaduria(Composite):

    def get_cantidadContadores(self):
        return self.cantidadContadores

    def set_cantidadContadores(self, cantidadContadores):
        self.cantidadContadores = cantidadContadores
