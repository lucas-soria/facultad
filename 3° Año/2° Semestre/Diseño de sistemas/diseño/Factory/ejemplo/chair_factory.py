from abc import ABCMeta, abstractmethod


# Factory
# Creator
class ChairFactory(metaclass=ABCMeta):
    @abstractmethod
    def create_chair(self):
        pass
