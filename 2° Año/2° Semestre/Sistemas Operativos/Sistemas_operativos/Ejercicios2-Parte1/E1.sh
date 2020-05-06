#!/bin/bash

echo "Donde se encuentra el directorio de archivos a eliminar?"
read i
echo "Que extension posee el archivo?"
read b
x="$PWD"
find $x/$i/ -name "*.$b" -type f -size 0b -print
find $x/$i/ -name "*.$b" -type f -size 0b -delete
echo "Se han eliminado todos los archivos de tamaño nulo con extension .$b"