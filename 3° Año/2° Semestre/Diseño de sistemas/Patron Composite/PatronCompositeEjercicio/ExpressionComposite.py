from Expression import Expression


class ExpressionComposite(Expression):

    def __init__(self):
        self.expression_list = []

    def add(self, expression):
        self.expression_list.append(expression)

    def remove(self, expression):
        self.expression_list.remove(expression)

    def operation(self):
        raise ArithmeticError
