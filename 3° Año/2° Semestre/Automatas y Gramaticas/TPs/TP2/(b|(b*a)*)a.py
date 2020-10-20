#!/usr/bin/python3
import re


def caracter(exp):
    if re.match(r'[ab]', exp):
        if re.match(r'a', exp):
            return 0
        elif re.match(r'b', exp):
            return 1
    else:
        print("Incorrecto, caracter no valido")
        exit(-1)


# -------Main-------
# Tabla:
# Estado A --> 0
# Estado B --> 1
# Estado C --> 2

estado = 0
tabla = [[1, 2, "E"], [1, 2, "A"], [1, 2, "E"]]
exp = input("Dada la expresion regular (b|(b*a)*)a\nIngrese una expresion valida: ")

for x in exp:
    estado_sig = tabla[estado][caracter(x)]
    print(f"Estado: {estado}\tCaracter: {x}\tEstado siguiente: {estado_sig}")
    estado = estado_sig

print("\n")
if tabla[estado][2] == "A":
    print("Correcto")
else:
    print("Incorrecto, expresion no valida")
