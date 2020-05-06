#!/bin/bash

echo "Ingrese un nombre correcto de archivo:"
read i
until [[ -e $i ]]; do
    echo "Ingreselo nuevamente:"
    read i
done
echo "El archivo existe"