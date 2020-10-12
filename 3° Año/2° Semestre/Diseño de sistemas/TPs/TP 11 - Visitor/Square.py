from Figure import Figure


class Square(Figure):

    def __init__(self, name, side):
        super().__init__(name)
        self.side = side

    def get_side(self):
        return self.side

    def accept(self, figure_visitor):
        figure_visitor.visit_square(self)
