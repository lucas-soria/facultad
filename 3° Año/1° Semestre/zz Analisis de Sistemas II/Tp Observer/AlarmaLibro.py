from Subject import Subject
from Administracion import Administracion
from Compras import Compras
from Stock import Stock


class AlarmaLibro(Subject):

    def __init__(self):
        self.observadores = [Administracion(), Compras(), Stock()]

    def attach(self, observador):
        self.observadores.append(observador)
    
    def dettach(self, observador):
        self.observadores.pop(observador)
    
    def notifyObservers(self):
        for i in range(len(self.observadores)):
            self.observadores[i].update()
