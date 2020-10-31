from Visitor import Visitor


class EquipIncantation(Visitor):
    def visit_mage(self, mage):
        if mage.get_magic_level() <= 5:
            mage.set_incantation("Firebolt")
        else:
            mage.set_incantation("Ice lightning")

    def visit_warrior(self, warrior):
        pass

    def visit_characters(self, characters):
        for character in characters:
            character.accept(self)
