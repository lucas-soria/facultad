#!/bin/bash

cd
echo -e "\nUsted se encuentra en la siguiente ubicacion: "
pwd
echo -e "\nIngrese un directorio:"
read d
cd $d
ficheros=$(ls)
g=0
e=0
r=0

for i in $ficheros; do

    if [ -d $i ]; then
        let g=g+1
    fi
    if [ -x $i ]; then
        let e=e+1

    fi
    if [ -f $i ]; then
        let r=r+1
    fi
    
done;

echo "Los archivos regulares son $r"
echo "Los archivos ejecutables son $e"
echo "Los directorios son $g"
