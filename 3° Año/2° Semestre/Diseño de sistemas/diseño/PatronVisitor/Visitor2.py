from Visitor import Visitor


class Visitor2(Visitor):
    def __init__(self):
        self.acA = 0
        self.bs = 0

    def visitElementA(self, elementA):
        self.acA = self.acA + 1

    def visitElementB(self, elementB):
        self.bs = self.bs + 1

    def contador(self):
        return str("As: " + str(self.acA) + ", Bs: " + str(self.bs))

    def visitElement(self, elements):
        for element in elements:
            element.accept(self)
