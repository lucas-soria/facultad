from AlarmaLibro import AlarmaLibro
from Compras import Compras
from Administracion import Administracion
from Stock import Stock
from Libro import Libro
from Biblioteca import Biblioteca


if __name__ == "__main__":
    alarma = AlarmaLibro()
    alarma.attach(Compras())
    alarma.attach(Administracion())
    alarma.attach(Stock())

    libro = Libro()
    libro.set_estado("MALO")

    biblioteca = Biblioteca(alarma)
    biblioteca.devuelve_libro(libro)
