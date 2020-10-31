from Visitor import Visitor


class GoldenMembership(Visitor):

    def visit_kid(self, kid):
        kid.set_subscription(kid.get_subscription() * 0.75)

    def visit_adult(self, adult):
        adult.set_subscription(adult.get_subscription() * 0.90)

    def visit_elder(self, elder):
        elder.set_subscription(elder.get_subscription() * 0.50)

    def visit_people(self, people):
        for person in people:
            person.accept(self)
