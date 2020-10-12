from FigureVisitor import FigureVisitor
import math


class AreaVisitor(FigureVisitor):

    __total_area = 0

    def visit_circle(self, circle):
        AreaVisitor.__total_area += math.pi * circle.get_radius() * circle.get_radius()

    def visit_square(self, square):
        AreaVisitor.__total_area += square.get_side() * square.get_side()

    def visit_triangle(self, triangle):
        AreaVisitor.__total_area += triangle.get_base() * triangle.get_height() * 0.5

    def get_total_area(self):
        return AreaVisitor.__total_area
