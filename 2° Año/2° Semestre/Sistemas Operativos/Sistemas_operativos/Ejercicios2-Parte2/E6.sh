#!/bin/bash

echo "Escriba el nombre de un animal"
read responde
case $responde in
		"caballo")
			echo "Es un animal de 4 patas"
			;;
		"perro")
			echo "Es un animal de 4 patas"
			;;
		"gato")
			echo "Es un animal de 4 patas"
			;;
		"canguro")
			echo "Es un animal de 2 patas"
			;;
		"gallina")
			echo "Es un animal de 2 patas"
            ;;
        *)
            echo "Perdon, no se cuantas patas tiene el animal"
	esac