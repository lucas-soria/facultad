class FigureVisitor:

    def visit_circle(self, circle):
        pass

    def visit_square(self, square):
        pass

    def visit_triangle(self, triangle):
        pass

    def process(self, figures):
        for figure in figures:
            figure.accept(self)
