from Expression import Expression


class CompositeElement(Expression):
        # Class representing objects at any level of the hierarchy tree except for the bottom level.
        # Maintains the child objects by adding and removing them from the tree structure.
        def __init__(self, *args):
                '''Takes the first positional argument and assigns to member variable "name". Initializes a list of children elements.'''
                self.name = args[0]
                self.children = []

        def appendChild(self, child):
                '''Adds the supplied child element to the list of children elements "children".'''
                self.children.append(child)

        def removeChild(self, child):
                '''Removes the supplied child element from the list of children elements "children".'''
                self.children.remove(child)

        def printDetails(self):
                '''Prints the details of the component element first. Then, iterates over each of its children, prints their details by calling their printDetails() method.'''
                print(self.name)
                for child in self.children:
                        print("\t", end = "")
                        child.printDetails()
