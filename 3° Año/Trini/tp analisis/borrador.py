import numpy as np
import sympy as sp


x = sp.Symbol('x')
print('Las funciones disponible son:\n\t1) Asen(x)+ Bcos(x)')
print('\t2) Ae^bx+cln(mx)')
print('Escriba el intervalo de aproximacion de la funcion:')
c = float(input())
d = float(input())
print('Elija la opcion de su funicon:')
op = int(input())
if op == 1:
    print('Elija las constantes que modelan su funicon:')
    a = float(input())
    b = float(input())
    print('Su función tiene la forma:\n\tf(x)={a}sen(x)+{b}cos(x)')
    print('Elija alguno de los subespacios disponibles pra la aproximacion')
    print('s1: Polinomios de grado menor o igual a dos:\n\tf1(x)=1' +
          '\n\tf2(x)=x\n\tf3(x)=x^2')
    print('s2: Base trigonométrica:\n\tf1(x)=cos(pix)\n\t' +
          'f2(x)=sen(pix)\n\tf3(x)=1')
    op2 = int(input())
    if op2 == 1:
        # rellenar la matriz con productos escalares entre funciones
        matriz = []
        for i in range(3):
            matriz.append([])
            for j in range(3):
                matriz[i].append([])
        # resolver sistema
        a1 = np.array([[matriz[1][1], matriz[1][2], matriz[1][3]],
                       [matriz[2][1], matriz[2][2], matriz[2][3]],
                       [matriz[3][1], matriz[3][2], matriz[3][3]]])
        a2 = np.array([])
        # columna de terminos indepednientes del sistema producto escalar
        # entre funcion f y cada base del subespacio
        z = np.linalg.solve(a1, a2)
        print('La solución del sistema asociado al problemas es: {z}')
        print('La mejor aproximacion a la funcion ingresada es:\n\t' +
              'f*(x)={z1}+{z2}x+{z3}x^2')
    else:
        # rellenar la matriz con productos escalares entre funciones
        matriz = []
        for i in range(3):
            matriz.append([])
            for j in range(3):
                matriz[i].append([])
        # resolver sistema
        a1 = np.array([[matriz[1][1], matriz[1][2], matriz[1][3]],
                       [matriz[2][1], matriz[2][2], matriz[2][3]]
                       [matriz[3][1], matriz[3][2], matriz[3][3]]])
        a2 = np.array([])
        # columna de terminos indepednientes del sistema producto
        # escalar entre funcion f y cada base del subespacio])
        z = np.linalg.solve(a1, a2)
        print('La solución del sistema asociado al problemas es: {z}')
        print('La mejor aproximacion a la funcion ingresada es:\n\t' +
              'f*(x)={z1}cos(pix)+{z2}sen(pix)x+{z3}')
else:
    print('Elija las constantes que modelan su funicon:')
    a = float(input())
    b = float(input())
    m = float(input())
    print('Ae^bx+cln(mx)')
    print('Su función tiene la forma:\n\tf(x){a}e^{b}x+cln({m}x)')
    print('Elija alguno de los subespacios disponibles pra la aproximacion:')
    print('s1: Polinomios de grado menor o igual a dos:\n\t' +
          'f1(x)=1\n\tf2(x)=x\n\tf3(x)=x^2')
    print('s2: Base trigonométrica:\n\tf1(x)=cos(pix)\n\t' +
          'f2(x)=sen(pix)\n\tf3(x)=1')
    op2 = int(input())
    if op2 == 1:
        # rellenar la matriz con productos escalares entre funciones
        matriz = []
        for i in range(3):
            matriz.append([])
            for j in range(3):
                matriz[i].append([])
        # resolver sistema
        a1 = np.array([[matriz[1][1], matriz[1][2], matriz[1][3]],
                       [matriz[2][1], matriz[2][2], matriz[2][3]],
                       [matriz[3][1], matriz[3][2], matriz[3][3]]])
        a2 = np.array([])
        # columna de terminos indepednientes del sistema producto escalar
        # entre funcion f y cada base del subespacio
        z = np.linalg.solve(a1, 12)
        print('La solución del sistema asociado al problemas es: {z}')
        print('La mejor aproximacion a la funcion ingresada es:\n\t' +
              'f*(x)={z1}+{z2}x+{z3}x^2')
    else:
        # rellenar la matriz con productos escalares entre funciones
        matriz = []
        for i in range(3):
            matriz[i] = ([])
            for j in range(3):
                matriz[j] = ()
    # resolver sistema
    a1 = np.array([[matriz[1][1], matriz[1][2], matriz[1][3]],
                   [matriz[2][1], matriz[2][2], matriz[2][3]],
                   [matriz[3][1], matriz[3][2], matriz[3][3]]])
    a2 = np.array([])
    # columna de terminos indepednientes del sistema producto escalar entre
    # funcion f y cada base del subespacio])
    z = np.linalg.solve(a1, 12)
    print('La solución del sistema asociado al problemas es: {z}')
    print('La mejor aproximacion a la funcion ingresada es:\n\t' +
          'f*(x)={z1}cos(pix)+{z2}sen(pix)x+{z3}')
