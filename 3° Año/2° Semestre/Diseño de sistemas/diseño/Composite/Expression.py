from abc import abstractmethod


class Expression:
    @abstractmethod
    def appendChild(self, child):
        pass

    @abstractmethod
    def printDetails(self):
        pass

    @abstractmethod
    def removeChild(self, child):
        pass
