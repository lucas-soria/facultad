

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int n1,n2;
	
	
	//Obtencion de datos
	printf("Ingrese el valor de 2 numeros\n");
	scanf("%d %d",&n1,&n2);
	
	
	//Comparacion
	if (n1<n2) {
		printf("\n\n\n%d es mayor que %d",n2,n1);
	} else {
		if (n2<n1) {
			printf("\n\n\n%d es mayor que %d",n1,n2);
		} else {
			printf("\n\n\nLos numeros son iguales");
		}
	}
	
}

