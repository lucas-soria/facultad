from Visitor import Visitor
import datetime


class Visitor1(Visitor):
    def visitElementA(self, elementA):
        response = str(datetime.datetime.now()) + " Visitador 1 --> elemento: " + elementA.getAttributeA()
        print(response)

    def visitElementB(self, elementB):
        response = str(datetime.datetime.now()) + " Visitador 1 --> elemento: " + elementB.getAttributeB()
        print(response)

    def visitElement(self, elements):
        for element in elements:
            element.accept(self)
