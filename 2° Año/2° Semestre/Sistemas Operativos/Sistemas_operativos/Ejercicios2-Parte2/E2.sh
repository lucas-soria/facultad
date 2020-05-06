#!/bin/bash

echo "Ingrese el nombre de un archivo comprimido"
read x
pwd
if [[ ! -e $x.tar ]]; then
    tar -cvf $x.tar E2.sh
else
    p="$PWD"
    find $p/$x.tar -delete
fi