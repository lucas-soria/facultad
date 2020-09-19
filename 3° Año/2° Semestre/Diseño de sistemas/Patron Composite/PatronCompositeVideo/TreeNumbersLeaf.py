from TreeNumbers import TreeNumbers


class ExpressionLeaf(TreeNumbers):

    def __init__(self, value):
        self.value = value

    def add(self, expression):
        # Do nothing because it's a leaf
        pass

    def remove(self, expression):
        # Do nothing because it's a leaf
        pass

    def sum(self):
        return self.value

    def higher(self):
        return self.value

    def number(self):
        return 1

    def toString(self):
        return "Leaf[" + self.value + "]"
