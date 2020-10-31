from Visitable import Visitable


class Elder(Visitable):

    def __init__(self):
        self.__subscription = 200

    def get_subscription(self):
        return self.__subscription

    def set_subscription(self, subscription):
        self.__subscription = subscription

    def accept(self, visitor):
        visitor.visit_elder(self)
