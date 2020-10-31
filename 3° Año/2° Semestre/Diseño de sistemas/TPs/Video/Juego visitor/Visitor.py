import abc


class Visitor(metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def visit_mage(self, mage): pass

    @abc.abstractmethod
    def visit_warrior(self, warrior): pass

    @abc.abstractmethod
    def visit_characters(self, characters): pass
