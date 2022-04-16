import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np


# constantes
radio = 2.8575  # 0.028575
masa = 0.163
cuarto_mesa = 63.5  # 0.635
angulo_colision = - (1/8 * np.pi) # este puede variar (ver despues)
distancia = 127 - 2 * (radio * np.cos(angulo_colision))  # 1.27 - 2 * (radio * np.cos(angulo_colision))
angulo_desvio = (1/2 * np.pi)

# variables
m1 = masa
m2 = masa
tick = 0.5
v1i = 100
a1i = 0
a2f = angulo_colision  # - (1/4 * np.pi)
if a2f < 2*np.pi:
    a1f = a2f + (1/2 * np.pi)
else:
    a1f = a2f - (1/2 * np.pi)

# ecuaciones de movimientos
# pre colision
primer_movimiento_x_m1 = v1i * tick
primer_movimiento_x_m2 = distancia
primer_movimiento_y_m1 = cuarto_mesa + 2 * (radio * np.sin(- angulo_colision))
primer_movimiento_y_m2 = cuarto_mesa + (radio * np.sin(- angulo_colision))
# post colision
v1f = v1i * (np.sin(a1i - a2f) / np.sin(a1f - a2f))
v2f = (m1 / m2) * v1i * ((np.sin(a1i) / np.sin(a2f)) - ((np.sin(a1f) / np.sin(a2f)) * (np.sin(a1i - a2f) / np.sin(a1f - a2f))))
segundo_movimiento_x_m1 = v1f * np.cos(a1f) * tick
segundo_movimiento_y_m1 = v1f * np.sin(a1f) * tick
segundo_movimiento_x_m2 = v2f * np.cos(a2f) * tick
segundo_movimiento_y_m2 = v2f * np.sin(a2f) * tick

i = 0

def animacion(index, ax1):
    global i
    x1 = cuarto_mesa + primer_movimiento_x_m1 * index
#    if index == 2:
#        plt.pause(3600)
    if x1 > distancia:
        x1 = segundo_movimiento_x_m1 * (index - i) + distancia
        x2 = segundo_movimiento_x_m2 * (index - i) + distancia
        y1 = segundo_movimiento_y_m1 * (index - i) + primer_movimiento_y_m1
        y2 = segundo_movimiento_y_m2 * (index - i) + primer_movimiento_y_m1
        ax1.scatter(x1, y1, color='red', s=radio*100)
        ax1.scatter(x2, y2, color='blue', s=radio*100)
        plt.annotate(index*tick, (x2,y2), textcoords="offset points", xytext=(0,10), ha='center')
    else:
        y1 = primer_movimiento_y_m1
        x2 = primer_movimiento_x_m2
        y2 = primer_movimiento_y_m2
        i = index
        ax1.scatter(x1, y1, color='red', s=radio*100)
        ax1.scatter(x2, y2, color='blue', s=radio*100)
    plt.annotate(index*tick, (x1,y1), textcoords="offset points", xytext=(0,10), ha='center')


def plot():
    fig = plt.figure()
    ax1 = fig.add_subplot()
    ani = FuncAnimation(fig, animacion, interval=1000*tick, fargs=(ax1,))
    plt.tight_layout()
    plt.axis('equal')
    plt.axis([0, cuarto_mesa * 4, 0, cuarto_mesa * 2])
    plt.show()

plot()
