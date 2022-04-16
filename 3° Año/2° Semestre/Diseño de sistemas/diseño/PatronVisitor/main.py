from ElementA import ElementA
from ElementB import ElementB
from Visitor1 import Visitor1
from Visitor2 import Visitor2


class Main():
    def __init__(self):
        self.elementList = []
        self.elementA = ElementA()
        self.elementB = ElementB()
        self.elementList.append(self.elementA)
        self.elementList.append(self.elementB)
        self.elementList.append(self.elementA)
        self.elementList.append(self.elementB)
        self.elementList.append(self.elementB)
        self.visitor1 = Visitor1()
        self.visitor2 = Visitor2()

    def main(self):
        self.visitor1.visitElement(self.elementList)
        self.visitor2.visitElement(self.elementList)
        print(self.visitor2.contador())


if __name__=="__main__":
    hola = Main()
    hola.main()
