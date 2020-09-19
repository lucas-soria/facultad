from TreeNumbers import TreeNumbers


class TreeNumbersComposite(TreeNumbers):
    def __init__(self, name):
        self.name = name
        self.expression_list = []

    def add(self, expression):
        self.expression_list.append(expression)

    def remove(self, expression):
        self.expression_list.remove(expression)

    def sum(self):
        result = self.expression_list[0].sum()
        for i in range(1, len(self.expression_list)):
            result += self.expression_list[i].sum()
        return result

    def higher(self):
        result = self.expression_list[0].higher()
        for i in range(1, len(self.expression_list)):
            num = self.expression_list[i].higher()
            if result < num:
                result = self.expression_list[i].higher()
        return result

    def number(self):
        return len(self.expression_list)

    def toString(self):
        return "[" + self.name + "]"
