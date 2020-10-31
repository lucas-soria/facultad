from Warrior import Warrior
from Mage import Mage
from EquipIncantation import EquipIncantation
from EquipWeapon import EquipWeapon


def main():
    w1 = Warrior()
    w2 = Warrior()
    m1 = Mage()
    m2 = Mage()

    m1.set_magic_level(3)
    m2.set_magic_level(7)

    characters = []
    characters.append(w1)
    characters.append(w2)
    characters.append(m1)
    characters.append(m2)

    ew = EquipWeapon()
    ew.visit_characters(characters)

    ei = EquipIncantation()
    ei.visit_characters(characters)

    print(f"\nWeapon of warrior 1: {w1.get_weapon()}")
    print(f"Weapon of warrior 2: {w2.get_weapon()}\n")
    print(f"Weapon of mage 1: {m1.get_weapon()}")
    print(f"Weapon of mage 1: {m2.get_weapon()}\n")
    print(f"Incantation of mage with magic level {m1.get_magic_level()}: {m1.get_incantation()}")
    print(f"Incantation of mage with magic level {m2.get_magic_level()}: {m2.get_incantation()}\n")


if __name__ == "__main__":
    main()
