import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import numpy as np
import matplotlib.gridspec as gridspec
import pandas as pd
import random


pd.set_option("display.max_rows", None, "display.max_columns", None)
"""Variables que describen propiedades de la pelota"""
vi = 25  # Velocidad inicial
g = -9.8  # Aceleracion de la gravedad
alpha = 1/4 * np.pi  # Angulo de golpe en plano XZ
teta = 1/16 * np.pi  # Angulo de golpe en plano XY
r = 0.11  # Radio de la pelota en metros
mass = 0.430  # Masa de la pelota en kg

"""Variables del modelo"""
tick = 0.1  # Medida de los tock en segundos
number_curves = 10  # Cantidad de curvas a generar

"""Valores del tiro parabolico teorico"""
total_time = (2 * vi * np.sin(alpha)) / -g  # Tiempo total de vuelo
hmax = (vi**2 * np.sin(alpha)**2) / (2 * -g)  # Altura maxima teorica
h23 = 2/3 * hmax  # 2/3 de la altura maxima teorica

"""Variables y estructuras de datos necesarias para el ploteo de las curvas"""
ticks = []  # Vector de tiempo transcurrido
prev_frame = None  # Soluciona el problema de animate() que genera 2 frames == 0
moments = []  # Vector de con los valores de los dias seleccionados
curve_families = {}  # Diccionario con las familias de curvas generadas a partir de los distintos valores de moments
df_filtered = {}  # Diccionario con las familias a generar
more_dots = False  # Para evitar que haga puntos innecesarios
# Vectores de posiciones del modelo teorico
xlt = []
ylt = []
zlt = []
# Fuerzas que afectan a la pelota
air_density_sea_level = 1.229  # kg/m3
air_mass = (np.pi * r**2) * air_density_sea_level
Fgrab = mass * g
Fnet_z = Fgrab


"""Funciones"""
def plot_family(frame, ax1, ax2, ax3):
    for i in range(number_curves):
        global more_dots
        maxtime, wind, angle = curve_families[i]['wind']
        color_point = 'red'
        color_curve = 'blue'
        mx = curve_families[i]['mxl'][-1]
        my = curve_families[i]['myl'][-1]
        mz = curve_families[i]['mzl'][-1] + Fnet_z * tick
        z = curve_families[i]['zl'][-1] + mz/mass * tick
        if z > 0:
            if z >= h23 and curve_families[i]['time'] < maxtime:
                # Fair = masa del aire (area que afecta * densidad aire nivel mar) [m2 * kg/m3] * aceleracion (velocidad al cuadrado) [m2/s2] => [kg/m] * [m2/s2] => [kg*m/s2]
                mx += air_mass * ((wind * np.cos(angle))**2) * tick
                my += air_mass * ((wind * np.sin(angle))**2) * tick
                curve_families[i]['time'] += tick
                color_point = 'yellow'
                color_curve = 'yellow'
                more_dots = True
            y = curve_families[i]['yl'][-1] + my/mass * tick
            x = curve_families[i]['xl'][-1] + mx/mass * tick
            curve_families[i]['mxl'].append(mx)
            curve_families[i]['myl'].append(my)
            curve_families[i]['mzl'].append(mz)
            curve_families[i]['xl'].append(x)
            curve_families[i]['yl'].append(y)
            curve_families[i]['zl'].append(z)
            curve_families[i]['ticks'].append(curve_families[i]['ticks'][-1] + tick)
            ax1.plot(curve_families[i]['xl'][-2:], curve_families[i]['zl'][-2:], color=color_curve)
            ax2.plot(curve_families[i]['xl'][-2:], curve_families[i]['yl'][-2:], curve_families[i]['zl'][-2:], color=color_curve)
            if more_dots:
                ax2.scatter(x, y, z, color=color_point, s=r*100, zdir='z')
                pass
            elif i == 0:
                ax2.scatter(x, y, z, color=color_point, s=r*100, zdir='z')
                pass
        else:
            ax3.scatter(curve_families[i]['xl'][-1], curve_families[i]['yl'][-1], color='blue', s=r*1000)

def animate(frame, ax1, ax2, ax3):
    # ax1.clear()  # descomentar para mas fps
    # ax2.clear()  # descomentar para mas fps
    global prev_frame
    if frame != prev_frame:
        z = vi * np.sin(alpha) * (frame * tick) + 1/2 * g * (frame * tick)**2
        if z < 0:
            ticks.append(total_time)
            xlt.append(vi * np.cos(alpha) * total_time)
            ylt.append(vi * np.sin(teta) * total_time)
            zlt.append(vi * np.sin(alpha) * total_time + 1/2 * g * total_time**2)
            ax3.scatter(xlt[-1], ylt[-1], color='pink', s=r*1000)
            plt.pause(120)
            plt.close()
        else:
            x = vi * np.cos(alpha) * (frame * tick)
            y = vi * np.sin(teta) * (frame * tick)
            xlt.append(x)
            ylt.append(y)
            zlt.append(z)
            ticks.append(frame * tick)
        plot_family(frame, ax1, ax2, ax3)
        ax1.plot(xlt[-2:], zlt[-2:], color='pink')
        ax2.plot(xlt[-2:], ylt[-2:], zlt[-2:], color='pink')
    prev_frame = frame

def gen_random_moments(df):
    while True:
        rand = random.randint(0, len(df.index)-1)
        if rand not in moments:
            return rand

def gen_moment_by_input(df):
    while True:
        rand = int(input(f"Ingrese un numero entre 0 y {len(df.index)-1}: "))
        if rand in moments:
            print("Valor ya seleccionado, seleccione otro")
        elif rand < 0 or rand > len(df.index)-1:
            print("Valor no valido, seleccione otro")
        else:
            return rand

def gen_moments(gen):
    """Se puede seleccionar: 'gen_random_moments' || 'gen_moment_by_input'"""
    global df_filtered
    with open('26048998_METGRAM.txt', 'r') as file:
        columns = ['hour', 'pressure', 'wind_direction', 'wind_speed_kts']
        df = pd.DataFrame()
        s = pd.Series(file)
        df = s.str.extractall(r'[\s|+](\d*\.\d*)\s').unstack().reset_index()[0].copy()
        df.columns = columns
        df['wind_speed_ms'] = df['wind_speed_kts'].apply( lambda a: float(a) / 1.944)
    print("Valores seleccionables:")
    print(df)
    while len(moments) != number_curves:
        moments.append(gen(df))
    df_filtered = df.iloc[moments].reset_index()
    print("\nLos momentos seleccionados fueron:")
    print(df_filtered.sort_values(by='index', ascending=True))

def add_wind(i):
    return [random.random(), float(df_filtered['wind_speed_ms'][i]), np.radians(float(df_filtered['wind_direction'][i]))]

def create_families():
    for i in range(number_curves):
        wind = add_wind(i)
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

def plot():
    fig = plt.figure(figsize=(14,7))
    gs1 = gridspec.GridSpec(2, 2)
    ax1 = fig.add_subplot(gs1[0, :])
    ax2 = fig.add_subplot(gs1[1, 0], projection='3d')
    ax2.view_init(30, 300)
    ax3 = fig.add_subplot(gs1[1, 1])
    ax1.set_title("Trayectoria 2d")
    ax2.set_title("Trayectoria 3d")
    ax3.set_title("Mapa de impacto aproximado")
    gs1.tight_layout(fig, rect=[0.01, 0, 1, 1])
    ax1.axhline(y=0, color='black')
    ani = FuncAnimation(fig, animate, interval=1000*tick*0, fargs=(ax1, ax2, ax3), cache_frame_data=False)
    ax1.axis('equal')
    ax1.set_xlabel("X")
    ax1.set_ylabel("Z")
    ax2.set_xlabel("X")
    ax2.set_ylabel("Y")
    ax2.set_zlabel("Z")
    ax3.set_xlabel("X")
    ax3.set_ylabel("Y")
    plt.show()

def save():
    dic = {'t (s)':ticks, 'x (m)':xlt,'y (m)':ylt, 'z (m)':zlt}
    for i in range(number_curves):
        if len(curve_families[i]['xl']) < len(xlt):
            for _ in range(len(xlt) - len(curve_families[i]['xl'])):
                curve_families[i]['ticks'].append(None)
                curve_families[i]['xl'].append(None)
                curve_families[i]['yl'].append(None)
                curve_families[i]['zl'].append(None)
        dic[f't[{df_filtered["index"][i]}] (s)'] = curve_families[i]['ticks']
        dic[f'x[{df_filtered["index"][i]}] (m)'] = curve_families[i]['xl']
        dic[f'y[{df_filtered["index"][i]}] (m)'] = curve_families[i]['yl']
        dic[f'z[{df_filtered["index"][i]}] (m)'] = curve_families[i]['zl']
    df_excel = pd.DataFrame.from_dict(dic)
    df_excel.to_excel('Soria - football - TP4.xlsx', header=True, index=False)

"""Ejecucion del programa"""
gen_moments(gen_random_moments)  # gen_random_moments || gen_moment_by_input
create_families()
plot()
save()
