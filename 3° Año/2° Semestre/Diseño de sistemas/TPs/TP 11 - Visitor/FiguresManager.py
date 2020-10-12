from AreaVisitor import AreaVisitor
from NumberOfSidesVisitor import NumberOfSidesVisitor


class FiguresManager:

    def __init__(self):
        self.figures = []

    def add(self, figure):
        self.figures.append(figure)

    def total_area(self):
        area_visitor = AreaVisitor()
        area_visitor.process(self.figures)
        return area_visitor.get_total_area()

    def total_number_of_sides(self):
        number_of_sides_visitor = NumberOfSidesVisitor()
        number_of_sides_visitor.process(self.figures)
        return number_of_sides_visitor.get_total_number_of_sides()
