#!/usr/bin/python3
import re


def diferenciador(x, estado):
    if re.match(r'r', x):
        return 0
    elif re.match(r'e', x):
        return 1
    elif re.match(r'g', x):
        return 2
    elif re.match(r'x', x):
        return 3
    elif re.match(r'.', x):
        return 4


def imprimir(estado, x, char, estado_sig):
    if char == 0:
        char = "r"
    if char == 1:
        char = "e"
    if char == 2:
        char = "g"
    if char == 3:
        char = "x"
    if char == 4:
        char = "."
    print(f"|      {estado}      |     {x}     |    {char}    |      {estado_sig}      |")


if __name__ == "__main__":
    # tabla [r, e, g, x, ., blanco o fin de tira]
    # opciones: estado siguiente o error o aceptacion
    tabla = [[1, 0, 0, 0, 0, "E"], [1, 2, 0, 0, 0, "E"], [1, 0, 3, 0, 0, "E"],
             [1, 4, 0, 0, 0, "E"], [1, 0, 0, 5, 0, "E"], [5, 5, 5, 5, 5, 6],
             ["E", "E", "E", "E", "E", "A"]]
    exp = input("Ingrese la expresion a comprobar: ")
    estado = 0
    print("\n\n+-------------+-----------+---------+-------------+\n| Estado Act. | Character | Simbolo | Estado Sig. |\n+-------------+-----------+---------+-------------+")
    for x in exp:
        char = diferenciador(x, estado)
        estado_sig = tabla[estado][char]
        imprimir(estado, x, char, estado_sig)
        estado = estado_sig
        print("+-------------+-----------+---------+-------------+")

    print("\n+-------------+-----------+---------+-------------+")
    if estado == "E":
        print("|                 Cadena INVALIDA                 |")
    else:
        estado_sig = tabla[estado][5]
        if estado_sig == "E":
            print("|                 Cadena INVALIDA                 |")
        elif tabla[estado_sig][5] == "E":
            print("|                 Cadena INVALIDA                 |")
        elif tabla[estado_sig][5] == "A":
            print("|                  Cadena VALIDA                  |")
    print("+-------------+-----------+---------+-------------+\n\n")
