from client import Cliente
from concreate_factory import BigChairFactory, MediumChairFactory


class Main():
    def main(self):
        c = Cliente()
        print("Size Big Chair: ")
        c.codigo_cliente(BigChairFactory())
        print("Size Medium Chair: ")
        c.codigo_cliente(MediumChairFactory())


if __name__ == '__main__':
    m = Main()
    m.main()
