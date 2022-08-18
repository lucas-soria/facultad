from composite import CompositeElement
from leaf import ChildElement


class Menu():
    def main(self):
        ### CREATING THE MENU i.e. TREE DATA STRUCTURE ###
        topLevelMenu = CompositeElement("TopLevelMenu")
        subMenuItem1 = CompositeElement("SubMenuItem1")
        subMenuItem2 = CompositeElement("SubMenuItem2")
        subMenuItem11 = ChildElement("SubMenuItem11")
        subMenuItem12 = ChildElement("SubMenuItem12")
        subMenuItem1.appendChild(subMenuItem11)
        subMenuItem1.appendChild(subMenuItem12)
        topLevelMenu.appendChild(subMenuItem1)
        topLevelMenu.appendChild(subMenuItem2)
        topLevelMenu.printDetails()


if __name__=='__main__':
    menu = Menu()
    menu.main()
