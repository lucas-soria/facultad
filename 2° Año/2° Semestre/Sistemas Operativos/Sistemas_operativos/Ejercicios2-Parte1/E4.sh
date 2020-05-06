#!/bin/bash

echo -e "SUMADOR: suma y resta enteros.

Los símbolos aceptados son: + y -

Debe haber espacio entre todos los operandos y operadores.

Ejemplo: 5 + 3 - 20 + 35

"

echo "Ingresar:"
read i
expr $i
if [ "$i" == "$(expr $i)" ]; then
    echo "La expresion es incorrecta"
fi
    
        