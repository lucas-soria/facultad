from abc import ABC, abstractmethod


class Televisor(ABC):
    @abstractmethod
    def funcion_televisor(self):
        pass
