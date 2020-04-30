

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
	int n, c=0, ra;	
	
	
	//Obtencion de informacion
	ra = rand() % 11;
	do {
		printf("Ingrese un numero entre el 0 y el 10\n");
		scanf("%d",&n);
		c++;
	} while (n != ra);
	
	//Mensaje
	printf("\n\nFelicitaciones, adivino el numero!!!\nY solo le tardo %d intentos\n\n",c);
}

