import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np
from multiprocessing import Process
import time
from itertools import count


class Graph:

    def __init__(self, frecuencia, velocidad, angulo=90):
        self.x_bola_posiciones = []  # POSICIONES DE LA BOLA EN X
        self.y_bola_posiciones = []  # POSICIONES DE LA BOLA EN Y
        self.largo_hasta_pino = 18.29 # LARGO HASTA EL PRIMER PINO EN METROS
        self.largo_hasta_fin = 19.16  # LARGO HASTA EL FINAL DE LA LINEA EN METROS
        self.angulo = angulo * np.pi / 180  # ANGULO EXPRESADO EN RADIANES
        self.velocidad_y_bola = velocidad * np.sin(self.angulo)  # VELOCIDAD DE LA BOLA EN EL EJE Y EXPRESADO EN M/S
        self.velocidad_x_bola = velocidad * np.cos(self.angulo)  # VELOCIDAD DE LA BOLA EN EL EJE X EXPRESADO EN M/S
        self.frecuencia = frecuencia

    def func_animate(self, index, ax):
        self.x_bola_posiciones.append(self.velocidad_x_bola * index)
        self.y_bola_posiciones.append(self.velocidad_y_bola * index)
        print(self.x_bola_posiciones, self.y_bola_posiciones)
        ax.plot(self.x_bola_posiciones, self.y_bola_posiciones)
        # ax.scatter(self.x_bola_posiciones, self.y_bola_posiciones)
    
    def plot(self):
        fig = plt.figure()
        ax = fig.add_subplot()
        ani = FuncAnimation(fig, self.func_animate, interval=1000, fargs=(ax,))
        plt.tight_layout()
        plt.axis('equal')
        plt.show()

graph = Graph(0.1, 10)
graph.plot()
