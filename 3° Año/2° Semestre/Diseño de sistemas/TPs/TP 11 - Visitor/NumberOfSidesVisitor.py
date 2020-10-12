from FigureVisitor import FigureVisitor
import math


class NumberOfSidesVisitor(FigureVisitor):

    __total_number_of_sides = 0

    def visit_circle(self, circle):
        NumberOfSidesVisitor.__total_number_of_sides += math.inf

    def visit_square(self, square):
        NumberOfSidesVisitor.__total_number_of_sides += 4

    def visit_triangle(self, triangle):
        NumberOfSidesVisitor.__total_number_of_sides += 3

    def get_total_number_of_sides(self):
        return NumberOfSidesVisitor.__total_number_of_sides
