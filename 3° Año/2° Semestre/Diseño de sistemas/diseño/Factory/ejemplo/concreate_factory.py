from chair_factory import ChairFactory
from concreate_product import BigChair, MediumChair


# Concreate Creator
class BigChairFactory(ChairFactory):
    def create_chair(self):
        return BigChair()


class MediumChairFactory(ChairFactory):
    def create_chair(self):
        return MediumChair()
