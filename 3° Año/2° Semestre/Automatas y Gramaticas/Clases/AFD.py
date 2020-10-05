#!/usr/bin/python
# -*- coding: utf-8 -*-
 
# www.pythondiario.com
 
import re
 
#Definimos la funcion caracter 
def caracter(character):
    global simbolo
    simbolo=""
    global Fin
    Fin=""
    digito="[0-9]"
    operador="(\+|\-|\*|\/)"
     
    #comparamos si es digito o operador
    if(re.match(digito,character)):
        simbolo=" Digito "
        return 0
    else:
        if(re.match(operador,character)):
            simbolo="Operador"
            return 1
        else:
            if(character==Fin):
                return 2
         
        #si no es ni un digito ni un operador entonces es un caracter no validp
        print("Error el ",character,"no es valido")
        exit()
 
#definimos al la funcion  encabezado
def encabezado():
    print("""|  Edo. Actual |Caracter |  Simbolo  |Edo. Siguiente |""")
    body()
 
#definimos la funcion contenido donde guarda cada valor despues de encontrarlo en un ciclo
def contenido(estadosig,character,simbolo,estado):
    print("|     ",estadosig,"      |  ",character,"    |",simbolo," |     ",estado,"       |")
    body()
 
#solo muestra la linea que se repetira cada vez que la mandemos a llamar
def body():
    print("+--------------+---------+-----------+---------------+")
 
#MAIN
#Este es la tabla de transiciones del automata AFD creado
tabla=[[1,"E","E"],[1,2,"E"],[3,"E","E"],[3,2,"A"]]
estado = 0
 
print ("""+-------------------------------------+
|    Ingrese una cadena a evaluar:    |
+-------------------------------------+""")
cadena = input()
body()
encabezado()
 
#ciclo para recorrer la cadena
for  character in cadena:
    estadosig=estado
     
    #llamamos al metodo para saber si es un caracter valido y el valor retornado se guarda en charcaracter
    charcaracter= caracter(character)
     
    #guardamos en estado el valor obtenido en la tabla segun las cordenadas que recibio anteriormente
    estado=tabla[estado][charcaracter]
     
    #Si el valor obtenido es una E imprimimos cadena no valida
    if (estado=="E"):
        print("|     ",estadosig,"      |  ",character,"    |",simbolo," |     ",estado,"       |")
        body()
        print("""|              Cadena No Valida :(                   |
+----------------------------------------------------+""")
        exit()
    contenido(estadosig,character,simbolo,estado)
 
#al concluir si el estado no es 3 que es el de aceptacion imprimimos cadena no valida    
if(estado!=3):
        print("""|              Cadena No Valida :(                   |
+----------------------------------------------------+""")
 
#si el estado es 3 es una cadena de aceptacion
if(estado==3):
    print("|     ",estado,"      |         |    FND    |               |")
    body()
    print("""|                Cadena Valida :)                    |
+----------------------------------------------------+""")
