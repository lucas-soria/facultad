from Visitor import Visitor


class EquipWeapon(Visitor):
    def visit_mage(self, mage):
        mage.set_weapon("Wand")

    def visit_warrior(self, warrior):
        warrior.set_weapon("Sword")

    def visit_characters(self, characters):
        for character in characters:
            character.accept(self)
