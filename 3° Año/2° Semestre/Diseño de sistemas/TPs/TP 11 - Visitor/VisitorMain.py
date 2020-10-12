from FiguresManager import FiguresManager
from Circle import Circle
from Square import Square
from Triangle import Triangle


class VisitorMain:

    def run(self):
        man1 = FiguresManager()
        man1.add(Triangle("triangle", 2, 2))
        print(man1.total_area())
        print(man1.total_number_of_sides())
        man2 = FiguresManager()
        man2.add(Square("square", 3))
        print(man2.total_area())
        print(man2.total_number_of_sides())
        man = FiguresManager()
        man.add(Circle("circle", 4))
        print(man.total_area())
        print(man.total_number_of_sides())


VisitorMain().run()
