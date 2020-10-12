class Figure:

    def __init__(self, name):
        self.name = name

    def get_name(self):
        return self.name

    def accept(self, figure_visitor):
        pass
