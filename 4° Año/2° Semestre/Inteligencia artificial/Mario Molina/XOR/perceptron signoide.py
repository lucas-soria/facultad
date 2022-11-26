import random
import math


def inicializacion_ws(xs):
    ws = []
    for _ in range(len(xs[0])):
        ws.append(random.uniform(-1, 1))
    return ws

def regla_aprendizaje_perceptron(w, c, r, s, x):
    sig = (s - r) * r * (1 - r)
    w += c * sig * x
    return w

def dinamica_perceptron(ws, xs):
    r = 0
    for i in range(len(ws)):
        r += (ws[i] * xs[i])
    return (1/(1+pow(math.e, -r)))
    #if r >= 0:
    #    return 1
    #return 0

def error_cuadrado(rs, ss):
    e = 0
    for i in range(len(ss)):
        e += pow((ss[i] - rs[i]), 2)
    return e/2

def iteracion(xs, ws, ss, c):
    rs = []
    rsb = []
    for i in range(len(xs)):
        r = dinamica_perceptron(ws, xs[i])
        for j in range(len(ws)):
            ws[j] = regla_aprendizaje_perceptron(ws[j], c, r, ss[i], xs[i][j])
        if r >= 0.5:
            rsb.append(1)
        else:
            rsb.append(0)
        rs.append(r)
    print('respuesta obtenida', rs)
    print('respuesta binaria', rsb)
    print('nuevos pesos', ws)
    return rs

# OR
# xs = [[1, 0, 0], [1, 0, 1], [1, 1, 0], [1, 1, 1]]
# ss = [0, 1, 1, 1]
# AND
xs = [[1, 0, 0], [1, 0, 1], [1, 1, 0], [1, 1, 1]]
ss = [0, 0, 0, 1]
# XOR
# xs = [[1, 0, 0], [1, 0, 1], [1, 1, 0], [1, 1, 1]]
# ss = [0, 1, 1, 0]
c = 0.1  # puede ser cualquier cosa
ws = inicializacion_ws(xs)
# ws = [0.2, -0.3, 0.7]
ea = 0.1
it = 1

while True:
    print(f'\nITERACION N° {it}')
    rs = iteracion(xs, ws, ss, c)
    e = error_cuadrado(rs, ss)
    print('error cuadrado', e)
    if e < ea:
        break
    it += 1
print(rs)
