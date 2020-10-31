import abc


class Visitor(metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def visit_kid(self, kid): pass

    @abc.abstractmethod
    def visit_adult(self, adult): pass

    @abc.abstractmethod
    def visit_elder(self, elder): pass

    @abc.abstractmethod
    def visit_people(self, people): pass
