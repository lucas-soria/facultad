from Subject import Subject


class AlarmaLibro(Subject):

    def __init__(self):
        self.observadores = []

    def attach(self, observador):
        self.observadores.append(observador)

    def dettach(self, observador):
        self.observadores.remove(observador)

    def notify_observers(self):
        for i in self.observadores:
            i.update()
