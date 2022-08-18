from fabrica import Fabrica
from samsung_televisor import SamsungTelevisor
from samsung_celular import SamsungCelular


class SamsungFabrica(Fabrica):
    def crear_televisor(self):
        return SamsungTelevisor()

    def crear_celular(self):
        return SamsungCelular()
