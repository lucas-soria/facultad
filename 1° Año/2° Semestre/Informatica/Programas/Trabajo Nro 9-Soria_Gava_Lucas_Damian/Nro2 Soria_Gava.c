

/*

	Lucas Damian Soria Gava

*/


#include <stdio.h>

void main () {
	
	
	//Declaracion de variables
	char nom[15],ape[15];
	int edad;
	
	
	//Obtencion de informacion
	printf("Buenos dias\n\n\n");
	printf("Cual es su nombre?\n");
	scanf("%s",&nom);
	fflush(stdin);
	printf("\nY su apellido?\n");
	scanf("%s",&ape);
	fflush(stdin);
	printf("\nCuantos anos tiene?\n");
	scanf("%d",&edad);
	
	
	//Texto
	printf("\n\nGracias!!\n\n\n\n\n\nBuenos dias %s %s, usted tiene %d anos\n\n\n\n",nom,ape,edad);	
}
