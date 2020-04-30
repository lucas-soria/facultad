

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	
	//Declaracion de variables
	int n1,n2;
	
	
	//Obtencion de informacion
	printf("Ingrese la cota inferior\n");
	scanf("%d",&n1);
	printf("Ingrese la cota superior\n");
	scanf("%d",&n2);
	
	
	//Cual es multiplo de 3?
	for (n1; n1 <= n2; n1++) {
		if (n1 % 3 == 0) {
			printf("\n%d es multiplo de 3", n1);
		}	
	}
}

