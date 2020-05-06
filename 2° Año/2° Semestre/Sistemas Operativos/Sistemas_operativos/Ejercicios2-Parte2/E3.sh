#!/bin/bash

echo "Ingrese el nombre de un archivo: "
read x

if [ -r $x ]; then
    echo "El archivo se puede leer"
fi
if [ -w $x ]; then
    echo "El archivo se puede escribir"
fi
if [ -x $x ]; then
    echo "El archivo se puede ejecutar"
fi
