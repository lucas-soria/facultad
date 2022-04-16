from abc import ABCMeta, abstractmethod


# Product
class IChair(metaclass=ABCMeta):
    @abstractmethod
    def get_dimenisons(self):
        pass
