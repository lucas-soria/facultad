from Adult import Adult
from Kid import Kid
from Elder import Elder
from Membership import Membership
from GoldenMembership import GoldenMembership


def main():
    k1 = Kid()
    k2 = Kid()

    a1 = Adult()
    a2 = Adult()

    e1 = Elder()
    e2 = Elder()

    people_m = [k1, a1, e1]
    people_g = [k2, a2, e2]

    m = Membership()
    m.visit_people(people_m)

    g = GoldenMembership()
    g.visit_people(people_g)

    print(f"\nMembership suscription price: \nKid: {k1.get_subscription()}\nAdult: {a1.get_subscription()}\nElder: {e1.get_subscription()}\n")
    print(f"Golden membership suscription price: \nKid: {k2.get_subscription()}\nAdult: {a2.get_subscription()}\nElder: {e2.get_subscription()}\n")


if __name__ == "__main__":
    main()
