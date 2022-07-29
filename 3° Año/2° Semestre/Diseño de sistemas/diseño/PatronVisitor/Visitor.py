from abc import abstractmethod


class Visitor():
    @abstractmethod
    def visitElementA(self, elementA):
        pass

    @abstractmethod
    def visitElementB(self, elementB):
        pass

    @abstractmethod
    def visitElement(self, elements):
        pass
