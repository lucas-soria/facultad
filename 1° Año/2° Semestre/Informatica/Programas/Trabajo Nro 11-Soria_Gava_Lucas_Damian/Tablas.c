

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	
	//Declaracion de variables
	int num,a=0,mul;
	
	
	//Obtencion de informacion
	printf("Ingrese un numero \n");
	scanf("%d",&num);
	printf("\n\nLa tabla del %d es: \n",num);
	
	
	//Calculo de la tabla
	while (a<11) {
		mul = (a * num);
		printf("%d X %d = %d\n",num,a,mul);
		a++;
	}
}

