from pajaro import Pajaro
from superman import Superman
from volador import Volador


class Main():
    def main(self):
        pajaro = Pajaro()
        pajaro.volar()
        pajaro.aterrizar()
        pajaro.despegar()
        superman = Superman()
        superman.volar()
        superman.aterrizar()
        superman.despegar()
        # volador = Volador()
        # volador.despegar()


if __name__ == '__main__':
    main = Main()
    main.main()
