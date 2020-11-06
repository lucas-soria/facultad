simbolos = {
    "i": 0,
    "+": 1,
    "-": 2,
    "(": 3,
    ")": 4,
    "$": 5
}

terminales = {
    "E": 0,
    "E'": 1,
    "T": 2
}

tabla = [["E->TE'", "", "", "E->TE'", "", ""],
         ["", "E'->+TE'", "E'->-TE'", "", "E'->e", "E'->e"],
         ["T->i", "", "", "T->(E)", "", ""]]

espacio = 40


def obtener_columna(simbolo_entrada):
    return simbolos[simbolo_entrada]


def obtener_fila(no_terminal):
    return terminales[no_terminal]


class Pila:
    def __init__(self):
        self.items = []

    def esta_vacia(self):  # verificar si la pila está vacía
        return self.items == []

    def insertar(self, item):  # inserta elemento en la pila (cima)
        self.items.append(item)

    def extraer(self):  # extrae elemento de la pila (cima)
        return self.items.pop()

    def inspeccionar(self):  # devuelve el elemento de la cima de la pila
        return self.items[-1]

    def tamano(self):  # devuelve el tamaño de la pila
        return len(self.items)

    def contenido(self):  # devuelve el contenido de la pila
        return (self.items)


def calcular(lista):
    resultado = 0
    for x in range(len(lista)):
        if type(lista[x]) == str:
            if lista[x] not in ["+", "-"]:
                if x == 0:
                    resultado += int(lista[x])
                else:
                    if lista[x-1] == "+":
                        resultado += int(lista[x])
                    else:
                        resultado -= int(lista[x])
        else:
            calculo_parcial = calcular(lista[x])
            if x != 0:
                if lista[x-1] == "+":
                    resultado += calculo_parcial
                else:
                    resultado -= calculo_parcial
            else:
                resultado += calculo_parcial
    return resultado


def analizador_sintactico(p, entrada, numeros):
    entrada_2 = entrada.copy()
    enlistar = False
    lista = []
    salida = ""
    indice = 0
    print("\nAnalizador sintactico:\n")
    print("PILA" + " " * (espacio-4) + "ENTRADA" + " " * (espacio-7) + "SALIDA")
    print(str(p.contenido()) + " " * (espacio-len(str(p.contenido()))) +
          str(entrada_2) + " " * (espacio-len(str(entrada_2))) + str(salida))
    for simbolo_entrada in entrada:
        cima_pila = p.inspeccionar()
        while(cima_pila != simbolo_entrada):
            col = obtener_columna(simbolo_entrada)
            fil = obtener_fila(cima_pila)
            salida = tabla[fil][col]
            if(salida != ""):
                p.extraer()
                posicion = salida.find(">")
                produccion = salida[posicion+1:]
                produccion_pila = []
                for simbolo in produccion:
                    if(simbolo != "'"):
                        posicion_2 = produccion.find(simbolo)
                        if(produccion[posicion_2+1:posicion_2+2] == "'"):
                            produccion_pila.append(simbolo + "'")
                        else:
                            produccion_pila.append(simbolo)
                for simbolo in reversed(produccion_pila):
                    if(simbolo != "e"):
                        p.insertar(simbolo)
            print(str(p.contenido()) + " " * (espacio-len(str(p.contenido()))) +
                  str(entrada_2) + " " * (espacio-len(str(entrada_2))) + str(salida))
            cima_pila = p.inspeccionar()
        if(simbolo_entrada == "$" and p.inspeccionar() == "$"):
            print("\nAnalisis Finalizado!\n")
        else:
            sacado = p.extraer()
            entrada_2.pop(0)
            if sacado not in ["E", "E'", "T"]:
                if sacado == "(":
                    enlistar = True
                    num = []
                elif sacado == ")":
                    enlistar = False
                    lista.append(num)
                if sacado not in ["(", ")"]:
                    if enlistar:
                        if sacado == "i":
                            sacado = numeros[indice]
                            indice += 1
                        num.append(sacado)
                    else:
                        if sacado == "i":
                            sacado = numeros[indice]
                            indice += 1
                        lista.append(sacado)
            print(str(p.contenido()) + " " * (espacio-len(str(p.contenido()))) + str(entrada_2))
    return calcular(lista)


def arbol_sintactico(p, entrada, salida):
    entrada_2 = entrada.copy()
    print("\nArbol de sintáctico:\n")
    for simbolo_entrada in entrada:
        cima_pila = p.inspeccionar()
        while(cima_pila != simbolo_entrada):
            col = obtener_columna(simbolo_entrada)
            fil = obtener_fila(cima_pila)
            salida = tabla[fil][col]
            if(salida != ""):
                print(p.extraer())
                posicion = salida.find(">")
                produccion = salida[posicion+1:len(salida)]
                produccion_pila = []
                for simbolo in produccion:
                    if(simbolo != "'"):
                        posicion_2 = produccion.find(simbolo)
                        if(produccion[posicion_2+1:posicion_2+2] == "'"):
                            produccion_pila.append(simbolo + "'")
                        else:
                            produccion_pila.append(simbolo)
                for simbolo in reversed(produccion_pila):
                    if(simbolo != "e"):
                        p.insertar(simbolo)
                    else:
                        print(simbolo)
            cima_pila = p.inspeccionar()
        if(simbolo_entrada == "$" and p.inspeccionar() == "$"):
            print("\nArbol finalizado!\n")
        else:
            print(p.extraer())
            entrada_2.pop(0)


def calcular_expresion(entrada):
    lista = []
    numeros = ""
    entrada_2 = []
    for x in entrada:
        if x not in ["+", "-", "(", ")"]:
            numeros += x
        else:
            if numeros != "":
                lista.append(numeros)
                entrada_2.append("i")
            entrada_2.append(x)
            numeros = ""
    lista.append(numeros)
    if x != ")":
        entrada_2.append("i")
    entrada_2.append("$")
    return entrada_2, lista


if __name__ == "__main__":
    pila_analizador = Pila()
    pila_analizador.insertar("$")
    pila_analizador.insertar("E")
    pila_arbol = Pila()
    pila_arbol.insertar("$")
    pila_arbol.insertar("E")
    entrada = input("Ingrese la expresion a analizar: ")
    entrada, numeros = calcular_expresion(entrada)
    resultado = analizador_sintactico(pila_analizador, entrada, numeros)
    arbol_sintactico(pila_arbol, entrada, "")
    print(f"\n\nEl resultado de la expresion es: {resultado}\n")
