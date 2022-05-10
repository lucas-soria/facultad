import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np
import matplotlib.gridspec as gridspec
import pandas as pd
import random


"""Variables de la pelota"""
vi = 25  # Velocidad inicial
g = -9.8  # Aceleracion de la gravedad
alpha = 1/4 * np.pi  # Angulo de golpe en plano XZ
teta = 1/16 * np.pi  # Angulo de golpe en plano XY
r = 0.11  # Radio de la pelota en metros
mass = 0.430  # Masa de la pelota en kg

"""Variables del modelo modificado"""
tick = 0.1  # Medida de los tock en segundos
number_curves = 10  # Cantidad de curvas a generar

"""Variables del tiro parabolico teorico"""
total_time = (2 * vi * np.sin(alpha)) / -g  # Tiempo total de vuelo
hmax = (vi**2 * np.sin(alpha)**2) / (2 * -g)  # Altura maxima teorica
h23 = 2/3 * hmax  # 2/3 de la altura maxima teorica

"""Variables y estructuras de datos necesarias para el ploteo de las curvas"""
# Vectores de posiciones del modelo teorico
xlt = []
ylt = []
zlt = []

def add_wind():
    return [random.random(), random.randint(0, 30) * random.random(), np.pi * random.randint(0, 360) / 180]

curve_families = {}
for i in range(number_curves):
    wind = add_wind()
    if wind != [0, 0, 0]:
        curve_families[i] = {}
        curve_families[i]['wind'] = wind
        curve_families[i]['time'] = 0
        curve_families[i]['ticks'] = [0]
        # Vectores de posiciones del modelo "real" inicializado con la posicion inicial
        curve_families[i]['xl'] = [0]
        curve_families[i]['yl'] = [0]
        curve_families[i]['zl'] = [0]
        # Vectores de momentos (p = v * m)
        curve_families[i]['mxl'] = [mass * vi * np.sin(alpha)]
        curve_families[i]['myl'] = [mass * vi * np.sin(teta)]
        curve_families[i]['mzl'] = [mass * vi * np.cos(alpha)]

# Fuerzas que afectan a la pelota
Fgrab = mass * g
Fnet = Fgrab
ticks = [0]  # Vector de tiempo inicializado en con el tiempo inicial

def plot_family(frame, ax1, ax2, ax3):
    for i in range(number_curves):
        wind, angle, maxtime = curve_families[i]['wind']
        color = 'red'
        global time
        mx = curve_families[i]['mxl'][-1]
        my = curve_families[i]['myl'][-1]
        mz = curve_families[i]['mzl'][-1] + Fnet * tick
        z = curve_families[i]['zl'][-1] + mz/mass * tick
        if z > 0:
            if z >= h23 and curve_families[i]['time'] < maxtime:
                mx += wind * np.cos(angle) * mass
                my += wind * np.sin(angle) * mass
                curve_families[i]['time'] += tick
                color = 'yellow'
            y = curve_families[i]['yl'][-1] + my/mass * tick
            x = curve_families[i]['xl'][-1] + mx/mass * tick
            curve_families[i]['mxl'].append(mx)
            curve_families[i]['myl'].append(my)
            curve_families[i]['mzl'].append(mz)
            curve_families[i]['xl'].append(x)
            curve_families[i]['yl'].append(y)
            curve_families[i]['zl'].append(z)
            curve_families[i]['ticks'].append(frame * tick)
            ax1.plot(curve_families[i]['xl'], curve_families[i]['zl'], color='blue')
            ax2.plot(curve_families[i]['xl'], curve_families[i]['yl'], curve_families[i]['zl'], color='blue')
            ax2.scatter(x, y, z, color=color, s=r*100, zdir='z')
        else:
            ax3.scatter(curve_families[i]['xl'][-1], curve_families[i]['yl'][-1], color='blue', s=r*1000)

def animate(frame, ax1, ax2, ax3):
    z = vi * np.sin(alpha) * (frame * tick) + 1/2 * g * (frame * tick)**2
    if z < 0:
        ylt.append(vi * np.sin(teta) * total_time)
        xlt.append(vi * np.cos(alpha) * total_time)
        zlt.append(vi * np.sin(alpha) * total_time + 1/2 * g * total_time**2)
        ax3.scatter(xlt[-1], ylt[-1], color='pink', s=r*1000)
        plt.pause(30)
        plt.close()
    else:
        y = vi * np.sin(teta) * (frame * tick)
        x = vi * np.cos(alpha) * (frame * tick)
        ticks.append(frame * tick)
        zlt.append(z)
        xlt.append(x)
        ylt.append(y)
        ax1.plot(xlt, zlt, color='pink')
        ax2.plot(xlt, ylt, zlt, color='pink')
    plot_family(frame, ax1, ax2, ax3)

def plot():
    fig = plt.figure(figsize=(14,7))
    gs1 = gridspec.GridSpec(2, 2)
    ax1 = fig.add_subplot(gs1[0, :])
    ax2 = fig.add_subplot(gs1[1, 0], projection='3d')
    ax3 = fig.add_subplot(gs1[1, 1])
    ax1.set_title("Trayectoria 2d")
    ax2.set_title("Trayectoria 3d")
    ax3.set_title("Mapa de impacto aproximado")
    gs1.tight_layout(fig, rect=[0.01, 0, 1, 1])
    ani = FuncAnimation(fig, animate, interval=1000*tick, fargs=(ax1, ax2, ax3), cache_frame_data=False)
    ax1.axis('equal')
    ax1.set_xlabel("X")
    ax1.set_ylabel("Z")
    ax2.set_xlabel("X")
    ax2.set_ylabel("Y")
    ax2.set_zlabel("Z")
    ax3.set_xlabel("X")
    ax3.set_ylabel("Y")
    plt.show()

plot()

dic = {'t (s)':ticks, 'x (m)':xlt,'y (m)':ylt, 'z (m)':zlt}
for i in range(number_curves):
    if len(curve_families[i]['xl']) < len(xlt):
        for _ in range(len(xlt) - len(curve_families[i]['xl'])):
            curve_families[i]['ticks'].append(None)
            curve_families[i]['xl'].append(None)
            curve_families[i]['yl'].append(None)
            curve_families[i]['zl'].append(None)
    dic[f't[{i}] (s)'] = curve_families[i]['ticks']
    dic[f'x[{i}] (m)'] = curve_families[i]['xl']
    dic[f'y[{i}] (m)'] = curve_families[i]['yl']
    dic[f'z[{i}] (m)'] = curve_families[i]['zl']
df = pd.DataFrame.from_dict(dic)
df.to_excel('Soria - football - TP3.xlsx', header=True, index=False)
