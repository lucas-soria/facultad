import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np
import pandas as pd


# VARIABLES
vi = 25
h = 0
g = 9.8
alpha = 1/4 * np.pi
teta = 1/4 * np.pi# 1/60 * np.pi
r = 0.11 # m
mass = 0.430 # kg
tick = 0.1
x1 = []
z1 = []
ticks = []

def animate(index, ax):
    x = vi * np.cos(alpha) * (index * tick)
    z = h + vi * np.sin(alpha) * (index * tick) - 1/2 * g * (index * tick)**2
    if z < 0:
        plt.pause(3600)
    else:
        x1.append(x)
        z1.append(z)
        ticks.append(index * tick)
        ax.plot(x1, z1, color='red')
        ax.scatter(x, z, color='blue')


def plot():
    fig = plt.figure()
    ax = fig.gca()
    ani = FuncAnimation(fig, animate, interval=1000*tick, fargs=(ax,))
    plt.tight_layout()
    # plt.axis('equal')
    fig.suptitle('Tiro de una pelota de football')
    plt.show()

plot()

df = pd.DataFrame.from_dict({'x (m)':x1,'y (m)':z1, 't (s)':ticks})
df.to_excel('Soria - football - TP1.xlsx', header=True, index=False)
