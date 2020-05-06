#!/bin/bash

#Lucas Damián Soria Gava 58156

x=0
while [ $x -ne 6 ]
do
	printf "Seleccione una Opcion:\n1) Marco - Polo\n2) Crear un directorio\n3) Checkear si existe un directorio\n4) Querido diario\n5) Clear\n6) Exit\n\n"
	read responde
	case $responde in
		#Juego de marco polo
		1)
			read Marco
			if [ $Marco == "marco" ]; then
				printf "\nPOLO :D"
			else
				printf "\nSeguí participando :/"
			fi
			;;
		#Opcion para crear un directorio
		2)
			printf "Ingrese el nombre del directorio a crear: "
			read Dire
			mkdir -v $Dire
			;;
		#Checkear existencia de un directorio
		3)
			printf "Nombre del directorio para checkear su existencia: "
			read Exdir
			if [ -d "$Exdir" ]; then
				printf "El directorio exite y su tamaño es de: "
				du -shb0BK $Exdir
			else
				printf "El directorio no existe"
			fi
			;;
		#Log de sentimientos
		4)
			printf "Ingrese su nombre: "
			read nombre
			printf "¿Cómo estás?\n"
			read -r animo
			if [ -z "$animo" ]; then
				printf "Estás mal\n"
				animo="Estás mal"
			fi
			if [ ! -d "$nombre" ]; then
				mkdir -v $nombre
			fi
			printf "Hoy: " >> $nombre/sentimiento
			date >> $nombre/sentimiento
			printf "\n	Me siento: $animo \n\n" >> $nombre/sentimiento
			;;
		#Solo para hacer un clear de la terminal
		5)
			clear
			;;
		#Salida
		6)
			printf "Chauu!!!\n\n"
			x=$((6))
			;;
		*)
			printf "Que escribiste?\n\n"
	esac
	printf "\n\n"
done
