

/*

	Lucas Damian Soria Gava

*/


#include <stdio.h>
#include <math.h>

void main () {
	
	
	//Declaracion de variables
	float c1,c2,h=0;
	
	
	//Obtencion de informacion
	printf("Ingrese la medida de los catetos\n");
	scanf("%f%f",&c1,&c2);
	
	
	//Hipotenusa
	h=(sqrt(pow(c1,2)+pow(c2,2)));
	
	printf("\n\nLa hipotenusa mide: %f\n\n",h);
}
