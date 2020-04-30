

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	
	//Declaracion de variables
	char nom;
	int pass;
	
	
	//Obtencion de informacion y autenticacion
	do {
		printf("Ingrese el Nombre de Usuario\n");
		scanf("%c",&nom);
		fflush(stdin);
		printf("\nIngrese el Password\n");
		scanf("%d",&pass);
		printf("\n\n");
		fflush(stdin);
	} while ((nom != 'a') || (pass != 1234));
	printf("Bienvenido al sistema");
}

