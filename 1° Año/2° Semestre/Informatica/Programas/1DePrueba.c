

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int num,i;
	float prom=0;
	
	
	//Obtencion de informacion
	for (i=0;i<=4;i++) {
		do {
			printf("Ingrese un numero entre 50 y 100\n");
			scanf("%d",&num);
		} while (num<50 || num>100);	
		
		
		//Calculo del promedio
		prom = prom + num;
	}	
	prom = prom / 5;
	
	printf("\n\nEl promedio es: %f\n",prom);
		
		
	//Es multiplo de 4?
	if (fmod(prom,4)==0) {
		printf("\nEl promedio es un multiplo de 4\n");
	}
	system("pause");
}

