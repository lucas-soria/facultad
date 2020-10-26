#!/usr/bin/python3
import re


def caracter(exp):
    if re.match(r'[xy]', exp):
        if re.match(r'x', exp):
            return 0
        elif re.match(r'y', exp):
            return 1
    else:
        print("Incorrecto, caracter no valido")
        exit(-1)


# -------Main-------
# Tabla:
# Estado 0 --> 0
# Estado A --> 1
# Estado B --> 2
# Estado C --> 3

estado = 0
tabla = [[1, "E", "E"], [2, 3, "A"], [2, 3, "A"], [2, 3, "A"]]
exp = input("Dada la expresion regular x(x|y)*\nIngrese una expresion valida: ")

for x in exp:
    estado_sig = tabla[estado][caracter(x)]
    print(f"Estado: {estado}\tCaracter: {x}\tEstado siguiente: {estado_sig}")
    estado = estado_sig

print("\n")
if estado == "E":
    print("Incorrecto, expresion no valida")
elif tabla[estado][2] == "A":
    print("Correcto")
else:
    print("Incorrecto, expresion no valida")
