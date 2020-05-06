

#include <stdio.h>
#include <stdlib.h>
##include "time.h"


int main() {


	//Declaracion de variables
	int v[10],i,max=0,min=0,posm,posma;

	//srand (time(NULL));


	//Obtencion de informacion
	for (i = 0; i <10 ; i++) {
		printf("Ingrese un numero\n");
		scanf("%d",&v[i]);
		//v[i]=rand () % 11;

		//Mayor y menor
		if (i == 0) {
			max = v[i];
			min = v[i];
		}
		if (max<v[i]) {
			max = v[i];
			posma = i;
		} else {
			if (min>v[i]) {
				min = v[i];
				posm = i;
			}
		}

	}

	printf("\n\n\n\n");

	//Mostrar vector
	for (i = 0; i <10; i++) {
		printf("%d, ",v[i] );
	}

	//El resto
	printf("\nEl menor valor es %d y esta en la posicion %d\n",min,posm+1);
	printf("El menor valor es %d y esta en la posicion %d\n",max,posma+1);


}
