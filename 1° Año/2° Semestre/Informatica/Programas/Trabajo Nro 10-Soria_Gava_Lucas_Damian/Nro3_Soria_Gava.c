

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	
	//Declaracion de variables
	int num;
	
	
	//obtencion de informacion
	printf("Ingrese un numero entre 1 y 100\n");
	scanf("%d",&num);
	
	
	//Resultado
	if (num>0 && num<30) {
		printf("\n\nEl numero esta entre 0 y 30");
	} else {
		if (num>31 && num<60) {
			printf("\n\nEl numero esta entre 31 y 60");
		} else {
			printf("\n\nEl numero esta entre 61 y 100");
		}
	}
}
