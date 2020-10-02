from administracion import Administracion
from stock import Stock
from alarmaLibro import AlarmaLibro
from compras import Compras

class Biblioteca():

    @staticmethod
    def devolver_libro(libro):
        print("\n***Biblioteca\nSe recibio el libro {}\nProduciendo informe...".format(libro.getTitulo()))
        AlarmaLibro.attach(Administracion())
        AlarmaLibro.attach(Compras())
        AlarmaLibro.attach(Stock())
        AlarmaLibro.notificar_observers(libro)
        print("***Fin informe ")
