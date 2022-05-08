import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np
import matplotlib.gridspec as gridspec
import pandas as pd


# VARIABLES
vi = 25
h = 0
g = 9.8
alpha = 1/4 * np.pi
teta = 1/16 * np.pi# 1/60 * np.pi
r = 0.11 # m
mass = 0.430 # kg
tick = 0.25
xl = []
yl = []
zl = []
ticks = []
ttotal = (2 * vi * np.sin(alpha)) / g


def animate(index, ax1, ax2):
    z = h + vi * np.sin(alpha) * (index * tick) - 1/2 * g * (index * tick)**2
    if z < 0:
        xl.append(vi * np.cos(alpha) * (ttotal))
        yl.append(vi * np.sin(teta) * (ttotal))
        zl.append(h + vi * np.sin(alpha) * (index * tick) - 1/2 * g * (ttotal)**2)
        plt.pause(30)
        plt.close()
    else:
        y = vi * np.sin(teta) * (index * tick)
        x = vi * np.cos(alpha) * (index * tick)
        xl.append(x)
        yl.append(y)
        zl.append(z)
        ticks.append(index * tick)
        ax1.plot(xl, zl, color='green')
        ax2.plot(xl, yl, zl, color='blue')
        ax2.scatter(x, y, z, color='red', s=r*1000, zdir='z')


def plot():
    fig = plt.figure(figsize=(7,7))
    gs1 = gridspec.GridSpec(2, 1)
    ax1 = fig.add_subplot(gs1[0])
    ax2 = fig.add_subplot(gs1[1], projection='3d')
    ax1.set_title("Trayectoria 2d")
    ax2.set_title("Trayectoria 3d")
    gs1.tight_layout(fig, rect=[0.01, 0, 1, 1])
    ani = FuncAnimation(fig, animate, interval=1000*tick, fargs=(ax1, ax2))
    ax1.axis('equal')
    ax1.set_xlabel("X")
    ax1.set_ylabel("Y")
    ax2.set_xlabel("X")
    ax2.set_ylabel("Y")
    ax2.set_zlabel("Z")
    plt.show()

plot()

df = pd.DataFrame.from_dict({'x (m)':xl,'y (m)':yl, 'z (m)':zl, 't (s)':ticks})
df.to_excel('Soria - football - TP2.xlsx', header=True, index=False)
