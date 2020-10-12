from Figure import Figure


class Triangle(Figure):

    def __init__(self, name, base, height):
        super().__init__(name)
        self.base = base
        self.height = height

    def get_base(self):
        return self.base

    def get_height(self):
        return self.height

    def accept(self, figure_visitor):
        figure_visitor.visit_triangle(self)
