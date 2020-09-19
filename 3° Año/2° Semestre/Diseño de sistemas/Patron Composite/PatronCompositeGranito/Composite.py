from ISueldo import ISueldo


class Composite(ISueldo):

    def __init__(self):
        self.empleados = []

    def get_sueldo(self):
        sumador = 0
        for i in range(len(self.empleados)):
            sumador += self.empleados[i].get_sueldo()
        return sumador

    def agrega(self, empleado):
        self.empleados.append(empleado)
