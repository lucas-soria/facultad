from Element import Element


class ElementA(Element):
    def __init__(self):
        self.attributeA = "A"

    def getAttributeA(self):
        return self.attributeA

    def accept(self, Visitor):
        Visitor.visitElementA(self)
