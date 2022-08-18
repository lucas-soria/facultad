from abc import ABC, abstractmethod


class Fabrica(ABC):
    @abstractmethod
    def crear_televisor(self):
        pass

    @abstractmethod
    def crear_celular(self):
        pass
