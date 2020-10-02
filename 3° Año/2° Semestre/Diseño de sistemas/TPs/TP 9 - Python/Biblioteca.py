class Biblioteca:

    def __init__(self, alarma):
        self.alarma = alarma

    def devuelve_libro(self, libro):
        if libro.get_estado() == "MALO":
            self.alarma.notify_observers()
