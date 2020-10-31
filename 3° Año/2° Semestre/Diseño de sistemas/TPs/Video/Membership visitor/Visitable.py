import abc


class Visitable(metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def accept(self, visitor): pass
