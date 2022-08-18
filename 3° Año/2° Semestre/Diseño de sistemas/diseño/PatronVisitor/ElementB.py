from Element import Element


class ElementB(Element):
    def __init__(self):
        self.attributeB = "B"

    def getAttributeB(self):
        return self.attributeB

    def accept(self, Visitor):
        Visitor.visitElementB(self)
