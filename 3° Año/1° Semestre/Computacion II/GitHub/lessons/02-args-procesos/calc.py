#!/usr/bin/python

import sys

#print sys.argv
operacion=False
if len (sys.argv) != 4:
    print "Error: uso " + sys.argv[0] + " nro [s|r|d|m] nro"
    sys.exit()

if  sys.argv[1].isdigit() != True:
    print "Error: uso " + sys.argv[0] + " nro [s|r|d|m] nro"
    sys.exit()
else:
    operador1 = int (sys.argv[1])
if  sys.argv[3].isdigit() != True:
    print "Error: uso " + sys.argv[0] + " nro [s|r|d|m] nro"
    sys.exit()
else:
    operador2 = int (sys.argv[3])
for letra in "s" "r" "m" "d":
    if letra == sys.argv[2]:
        operacion = True

if  operacion != True:
    print "Error: uso " + sys.argv[0] + " nro [s|r|d|m] nro"
    sys.exit()

if sys.argv[2] == "s":
    print (operador1 + operador2)
elif sys.argv[2] == "r":
    print (operador1 - operador2)
elif sys.argv[2] == "m":
    print (operador1 * operador2)
elif sys.argv[2] == "d":
    print (operador1 / operador2)

