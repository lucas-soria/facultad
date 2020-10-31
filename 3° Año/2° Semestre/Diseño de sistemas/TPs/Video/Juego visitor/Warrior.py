from Visitable import Visitable


class Warrior(Visitable):

    def __init__(self):
        self.__weapon = ""

    def get_weapon(self):
        return self.__weapon

    def set_weapon(self, weapon):
        self.__weapon = weapon

    def accept(self, visitor):
        visitor.visit_warrior(self)
