from Composite import Composite


class SectorAdministracion(Composite):

    def get_cantidadAdministradores(self):
        return self.cantidadAdministradores

    def set_cantidadAdministradores(self, cantidadAdministradores):
        self.cantidadAdministradores = cantidadAdministradores
