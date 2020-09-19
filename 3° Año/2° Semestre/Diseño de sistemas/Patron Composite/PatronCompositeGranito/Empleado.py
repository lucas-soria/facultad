from ISueldo import ISueldo


class Empleado(ISueldo):

    def __init__(self, nomber_completo, cargo, sueldo):
        self.set_sueldo(sueldo)
        self.set_nombreCompleto(nomber_completo)
        self.set_cargo(cargo)

    def get_sueldo(self):
        return self.sueldo

    def set_cargo(self, cargo):
        self.cargo = cargo

    def set_nombreCompleto(self, nombreCompleto):
        self.nombreCompleto = nombreCompleto

    def set_sueldo(self, sueldo):
        self.sueldo = sueldo
