from Visitable import Visitable


class Mage(Visitable):

    def __init__(self):
        self.__weapon = ""
        self.__incantation = ""
        self.__magic_level = 1

    def get_weapon(self):
        return self.__weapon

    def set_weapon(self, weapon):
        self.__weapon = weapon

    def get_incantation(self):
        return self.__incantation

    def set_incantation(self, incantation):
        self.__incantation = incantation

    def get_magic_level(self):
        return self.__magic_level

    def set_magic_level(self, magic_level):
        self.__magic_level = magic_level

    def accept(self, visitor):
        visitor.visit_mage(self)
