#!/bin/bash

echo "Ingrese el nombre de un archivo"
read x
if [[ -e $x ]]; then
    if [[ -f $x ]]; then
        chmod -c 710 $x
    fi
fi