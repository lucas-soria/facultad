#!/usr/bin/python
import os

while True:
    leido = os.read(0, 1024)
    #parte en lineas
    for renglon in leido.splitlines():
        #parte linea en palabras
        for palabra in renglon.split():
            #muestra al reves palabra[::-1] agrega espacio entre cada una
            os.write(1,palabra[::-1]+" ")
        #agrega enter entre cada renglon
        os.write(1,"\n")    
    #valida que termino de leer el stdin   EOF 
    if len(leido) != 1024:
        break
