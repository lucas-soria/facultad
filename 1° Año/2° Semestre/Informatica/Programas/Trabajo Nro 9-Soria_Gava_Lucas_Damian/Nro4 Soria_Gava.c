

/*

	Lucas Damian Soria Gava

*/


#include <stdio.h>

void main () {
	
	
	//Declaracion de variables
	float al,ba,pr,v=0;
	
	
	//Obtencion de informacion
	printf("Ingrese altura, luego base y por ultimo profundidad en metros\n");
	scanf("%f%f%f",&al,&ba,&pr);
	
	
	//Volumen
	v=(al*ba*pr);
	
	printf("\n\nEl volumen es de: %f metros cubicos\n\n",v);
}
