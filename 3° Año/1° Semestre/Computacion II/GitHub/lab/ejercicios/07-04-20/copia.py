#!/usr/bin/python

archivo_origen=raw_input("archivo origen: ")
archivo_destino=raw_input("archivo destino: ")

fdo = open( archivo_origen, "r" )
fdd = open( archivo_destino, "w")

#for linea in fdo:
#    fdd.write(linea)
#    #print linea
#    print ("for " + str(len(linea)))
#
#fdo.close()
#fdd.close()

fdd = open( archivo_destino, "w")
with open(archivo_origen, 'r') as archi:
    data = archi.read()
    fdd.write(data)
    print ( "whith " + str(len(data)))

fdo.close()
fdd.close()

