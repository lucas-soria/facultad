from Expression import Expression


class ExpressionLeaf(Expression):

    def __init__(self, number):
        self.number = number

    def add(self, expression):
        # Do nothing because it's a leaf
        pass

    def remove(self, expression):
        # Do nothing because it's a leaf
        pass

    def operation(self):
        return self.number
