from Composite import Composite


class SectorCajas(Composite):

    def get_cantidadCajeros(self):
        return self.cantidadCajeros

    def set_cantidadCajeros(self, cantidadCajeros):
        self.cantidadCajeros = cantidadCajeros
