from Expression import Expression


class ChildElement(Expression):
    '''Class representing objects at the bottom of the hierarchy tree.'''

    def __init__(self, *args):
            ''''Takes the first positional argument and assigns to member variable "name".'''
            self.name = args[0]

    def printDetails(self):
            '''Prints the name of the child element.'''
            print("\t", end = "")
            print(self.name)
