from product import IChair


# Concreate Product
class BigChair(IChair):
    def __init__(self):
        self.height = 80
        self.width = 80
        self.depth = 80

    def get_dimenisons(self):
        return {'height': self.height, 'width': self.width, 'depth': self.depth}


class MediumChair(IChair):
    def __init__(self):
        self.height = 40
        self.width = 40
        self.depth = 40

    def get_dimenisons(self):
        return {'height': self.height, 'width': self.width, 'depth': self.depth}
