#!/usr/bin/python
import matplotlib.pyplot as plt

def histograma():
    vals = []
    val = ''
    i = 0
    mayor = 0
    with open('datos.csv') as datos:
        lectura = list(datos.readline().strip('\n'))
    print ('Valores leidos: ',lectura)
    plt.hist(lectura, bins=20)
    plt.xticks(range(10))
    plt.title("Histograma valores leidos")
    plt.xlabel("Numeros")
    plt.ylabel("Veces")
    plt.show()

if __name__ == "__main__":
    histograma()
