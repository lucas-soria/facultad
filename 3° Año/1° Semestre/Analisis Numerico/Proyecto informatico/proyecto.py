#!/usr/bin/python3
import csv


class LetraError(Exception):
    pass


class LargoError(Exception):
    pass


class ArchivoError(Exception):
    pass


class hError(Exception):
    pass


def derivada(x, dif):
    der = 0
    for i in range(len(dif)):
        der += (1/(x[1]-x[0]))*(((-1)**i)*(1/(i+1))*(dif[i]))
    return der


def diferencias(y):
    m = []
    dif = []
    for i in range(len(y)):
        m.append([])
        for j in range(len(y)):
            if j == 0:
                m[i].append(y[i])
            else:
                if i == 0 or m[i-1][j-1] == "-":
                    m[i].append("-")
                if i != 0 and m[i-1][j-1] != "-":
                    m[i].append(m[i][j-1]-m[i-1][j-1])
                if j == i:
                    dif.append(m[i][j])
    return dif


def check_letras(x, y):
    try:
        if len(x) != len(y):
            raise LargoError
        for i in range(len(x)):
            x[i] = float(x[i])
            y[i] = float(y[i])
        ha = None
        for i in range(len(x)):
            if i != 0:
                h = x[i] - x[i-1]
                if i != 1:
                    e = (h - ha) / h
                    if abs(e) > 0.000001:
                        raise hError
                ha = h
    except hError:
        print("\n\nLos valores de abscisas no tienen todos las mismas "
              "distancias entre si\n\n")
        exit(-1)
    except (LetraError, ValueError):
        print("\n\nSe ingresaron datos no numericos\n\n")
        exit(-1)
    except (LargoError, TypeError):
        print("\n\nLos vectores de X e Y no tienen el mismo largo\n\n")
        exit(-1)
    return x, y


def leer_archivo(x, y):
    try:
        archivo = input("\nIngrese el PATH del archivo csv: ")
        if ".csv" not in archivo:
            raise ArchivoError
        with open(archivo) as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                x.append(row["x"])
                y.append(row["y"])
    except (ArchivoError, KeyError):
        print("\n\nEl nombre del archivo ingresado no posee formato .csv o"
              " hay un error con sus datos\n\n")
        exit(-1)
    except (FileNotFoundError):
        print("\n\nEl archivo no existe\n\n")
        exit(-1)
    return check_letras(x, y)


def leer_manual(x, y):
    print("\nPrimero se ingresaran los valores del eje de las abscisas.\nTodos"
          " los valores deben ser ingresados a la vez separados por comas")
    x = input("X: ").replace(" ", "").split(",")
    print("\nAhora se ingresaran los valores del eje de las Ordenadas.\nTodos "
          "los valores deben ser ingresados a la vez separados por comas")
    y = input("Y: ").replace(" ", "").split(",")
    return check_letras(x, y)


def main():
    x = []
    y = []
    response = 0
    print("\nEl siguiente programa puede calcular la derivada de una funcion "
          "en un punto x0 aplicando el Metodo de diferencias hacia adelante\n")
    while response != "1" and response != "2":
        response = "1"
        response = input("\nSeleccionar la opcion de ingreso de datos:\n"
                         "1. Desde un archivo .csv\n2. Puntos de forma "
                         "manual\n")
        if response == "1":
            x, y = leer_archivo(x, y)
        elif response == "2":
            x, y = leer_manual(x, y)
        else:
            print("\nLa opcion ingresada es incorrecta, intente de nuevo\n")
    dif = diferencias(y)
    der = derivada(x, dif)
    print("\nDatos:\nX  Y")
    for i in range(len(x)):
        print(x[i], y[i])
    print("\n\nResultado: ", der)


if __name__ == "__main__":
    main()
