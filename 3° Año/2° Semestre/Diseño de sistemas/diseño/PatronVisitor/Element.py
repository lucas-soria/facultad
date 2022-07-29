from abc import abstractmethod


class Element():
    @abstractmethod
    def accept(self, visitor):
        pass
