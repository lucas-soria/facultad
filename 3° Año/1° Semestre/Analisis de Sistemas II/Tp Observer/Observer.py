from AlarmaLibro import AlarmaLibro
from Compras import Compras
from Administracion import Administracion
from Stock import Stock
from Libro import Libro
from Biblioteca import Biblioteca


class Observer:

    def main(self):
        a = AlarmaLibro()
        a.attach(Compras())
        a.attach(Administracion())
        a.attach(Stock())

        libro = Libro()
        libro.setEstado("MALO")

        b = Biblioteca()
        b.devuelveLibro(libro)


if __name__ == "__main__":
    Observer = Observer()
    Observer.main()
