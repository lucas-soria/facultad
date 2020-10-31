from Visitor import Visitor


class Membership(Visitor):

    def visit_kid(self, kid):
        kid.set_subscription(kid.get_subscription() * 0.85)

    def visit_adult(self, adult): pass

    def visit_elder(self, elder):
        elder.set_subscription(elder.get_subscription() * 0.75)

    def visit_people(self, people):
        for person in people:
            person.accept(self)
