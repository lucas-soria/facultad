from abc import abstractmethod, ABCMeta


class Volador(metaclass=ABCMeta):
    @abstractmethod
    def volar(self):
        pass

    @abstractmethod
    def aterrizar(self):
        pass

    def despegar(self):
        print("Tomando impulso")
