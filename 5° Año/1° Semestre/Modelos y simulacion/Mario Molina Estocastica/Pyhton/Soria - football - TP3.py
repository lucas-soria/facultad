import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np
import matplotlib.gridspec as gridspec
import pandas as pd
import random


# Estructuras de datos
familia_curvas = []
xl = []
yl = []
zl = []
ticks = []
xlw = {}
ylw = {}
zlw = {}

# VARIABLES
vi = 25  # Velocidad inicial
h = 0  # Altura inicial
g = 9.8  # Aceleracion de la gravedad (valor absoluto)
alpha = 1/4 * np.pi  # Angulo de golpe en plano XZ
teta = 1/16 * np.pi  # Angulo de golpe en plano XY
r = 0.11  # Radio de la pelota en metros
mass = 0.430  # Masa de la pelota en kg
tick = 0.1  # Medida de los tock en segundos
curvas = 5  # Cantidad de curvas MAXIMAS a generar
ttotal = (2 * vi * np.sin(alpha)) / g
hmax = (vi**2 * np.sin(alpha)**2) / (2 * g)
h23 = 2/3 * hmax


def add_wind():
    return [random.randint(0, 30) * random.random(), np.pi * random.randint(0, 360) / 180, np.pi * random.randint(0, 360) / 180]

def plot_family(ax1, ax2, x0, y0, vx0, vy0):
    index = 0
    for i in range(len(familia_curvas)):
        wind, anglexz, angley = familia_curvas[i]
        z = h + vi * np.sin(alpha) * (index * tick) - 1/2 * g * (index * tick)**2
        if z >= h23:
            x = x0 + vx0 * (index * tick) + wind * np.cos(anglexz) * (index * tick) 
            y = y0 + vy0 * (index * tick) + wind * np.sin(angley) * (index * tick)
            if i in xlw:
                xlw[i].append(x)
                ylw[i].append(y)
                zlw[i].append(z)
            else:
                xlw[i] = [x]
                ylw[i] = [y]
                zlw[i] = [z]
        if z < 0:
            xlw[i].append(5)
            ylw[i].append(5)
            zlw[i].append(5)
            continue
        ax1.plot(xlw[i], zlw[i], color='pink')
        ax2.plot(xlw[i], ylw[i], zlw[i], color='pink')
        index += 1

def animate(index, ax1, ax2):
    global familia_curvas
    z = h + vi * np.sin(alpha) * (index * tick) - 1/2 * g * (index * tick)**2
    if z < 0:
        plt.pause(60)
        plt.close()
    else:
        y = vi * np.sin(teta) * (index * tick)
        x = vi * np.cos(alpha) * (index * tick)
        xl.append(x)
        yl.append(y)
        zl.append(z)
        ticks.append(index * tick)
        ax1.plot(xl, zl, color='blue', linewidth=5)
        ax2.plot(xl, yl, zl, color='blue', linewidth=5)
        ax2.scatter(x, y, z, color='red', s=r*1000, zdir='z')
        plot_family(index, ax1, ax2)

def plot():
    fig = plt.figure(figsize=(7,7))
    gs1 = gridspec.GridSpec(2, 1)
    ax1 = fig.add_subplot(gs1[0])
    ax2 = fig.add_subplot(gs1[1], projection='3d')
    ax1.set_title("Trayectoria 2d")
    ax2.set_title("Trayectoria 3d")
    gs1.tight_layout(fig, rect=[0.01, 0, 1, 1])
    ani = FuncAnimation(fig, animate, interval=1000*tick, fargs=(ax1, ax2))
    # ax1.axis('equal')
    ax1.set_xlabel("X")
    ax1.set_ylabel("Y")
    ax2.set_xlabel("X")
    ax2.set_ylabel("Y")
    ax2.set_zlabel("Z")
    # ax1.set_ylim(bottom=0)
    plt.show()


for _ in range(curvas):
    wind = add_wind()
    if wind != [0, 0, 0]:
        familia_curvas.append(wind)
plot()


dic = {'t (s)':ticks, 'x (m)':xl,'y (m)':yl, 'z (m)':zl}
for i in range(len(familia_curvas)):
    if len(xlw[i]) < len(xl):
        for _ in range(len(xl) - len(xlw[i])):
            xlw[i].append(None)
            ylw[i].append(None)
            zlw[i].append(None)
    dic[f'x[{i}] (m)'] = xlw[i]
    dic[f'y[{i}] (m)'] = ylw[i]
    dic[f'z[{i}] (m)'] = zlw[i]
df = pd.DataFrame.from_dict(dic)
df.to_excel('Soria - football - TP3.xlsx', header=True, index=False)

# TODO: forzar el z = 0

"""
el viento debe tener los siguientes parametros 
{ 
    esta a 2/3 de la altura del vuelo
    random duracion entre 0 y 1 seg
    random velocidad entre 0 y 30 m/s
    (distribucionn a discutir) angulo entre 0 y 360° (paralelo al piso) => el norte son 0 grados y es antihorario
}
Despues se busca un coeficiente de dispercion
"""
