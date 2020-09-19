from ExpressionComposite import ExpressionComposite


class ExpressionCompositeDiv(ExpressionComposite):

    def __init__(self):
        super(ExpressionCompositeDiv, self).__init__()

    def operation(self):
        result = self.expression_list[0].operation()
        for i in range(1, len(self.expression_list)):
            result /= self.expression_list[i].operation()
        return result
