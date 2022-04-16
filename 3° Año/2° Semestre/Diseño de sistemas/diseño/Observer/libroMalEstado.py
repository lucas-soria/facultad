from abc import abstractmethod


# Observador
class libroMalEstado():
    @abstractmethod
    def update(self, libro):
        pass
