import sympy as sp
import numpy as np
import math


print('Las funcion disponible es Asen(x)+Bcos(x)')
print('Ingrese las constantes que modele su funcion')

a = float(input())
b = float(input())
x = sp.Symbol('x')
f = lambda x: a * math.sin(x)

print('Su funcion es:\n', a)
print('Aproximamos con un subespacio de polinomios de grado menor o igual a dos')

f1 = 1
f2 = x
f3 = x * x

print('Ingrese intervalo de integracion')

c = float(input())
d = float(input())

y1 = f1 * f1
y11 = sp.integrate(y1, (x, c, d))
y2 = f1 * f2
y22 = sp.integrate(y2, (x, c, d))
y3 = f1 * f3
y33 = sp.integrate(y3, (x, c, d))
y44 = y22
y5 = f2 * f2
y55 = sp.integrate(y5, (x, c, d))
y6 = f2 * f3
y66 = sp.integrate(y6, (x, c, d))
y7 = f1 * f3
y77 = sp.integrate(y7, (x, c, d))
y88 = y66
y9 = f3 * f3
y99 = sp.integrate(y9, (x, c, d))
y10 = f * f1
y100 = sp.integrate(y10, (x, c, d))
y111 = f * f2
y110 = sp.integrate(y11, (x, c, d))
y12 = f * f3
y112 = sp.integrate(y12, (x, c, d))

e = np.array([y11, y22, y33], [y44, y55, y66], [y77, y88, y99])
f = np.array([y100, y111, y112])
z = np.linalg.solve(e, f)

print(z)
