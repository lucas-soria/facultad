import sympy as sp


x = sp.Symbol('x')
y = 3 * x
print(sp.integrate(y, (x, 5, 7)))
