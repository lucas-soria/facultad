from Figure import Figure


class Circle(Figure):

    def __init__(self, name, radius):
        super().__init__(name)
        self.radius = radius

    def get_radius(self):
        return self.radius

    def accept(self, figure_visitor):
        figure_visitor.visit_circle(self)
