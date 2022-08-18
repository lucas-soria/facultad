from cortador import Cortador
from freidor import Freidor
from hervidor import Hervidor


class Cocinar(object):
    def preparaComida(self):
        self.cortador = Cortador()
        self.cortador.cortarVegetales()
        self.hervidor = Hervidor()
        self.hervidor.hervirVegetales()
        self.freidor = Freidor()
        self.freidor.freir()
