#!/bin/bash

echo "Ingrese un numero entre 20 y 30:"
read i
until [[ $i=>20 && $i<=30 ]]; do
    echo -e "\nEl numero ingresado es incorrecto, Ingrese un numero nuevamente:"
    read i
done

echo "Numero correcto!"
