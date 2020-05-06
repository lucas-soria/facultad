#!/bin/bash

echo "Ingrese el nombre de un directorio:"
read i 
if [ -e $i ]; then
    if [ -d $i ];then
        echo "El archivo existe y es un directorio"
        #Agregarlo a PATH
        if [ ":$PATH:" != *"$i"* ]; then
            export PATH=$PATH:$i  
            echo "El directorio no estaba en la variable PATH"
        else
            echo "El directorio ya estaba en la variable PATH"
        fi
    else 
        echo "el archivo existe pero no es un directorio"
    fi
else
    echo "El directorio no existe"
fi
printf "\n\nLa variable PATH contiene: \n"
echo $PATH