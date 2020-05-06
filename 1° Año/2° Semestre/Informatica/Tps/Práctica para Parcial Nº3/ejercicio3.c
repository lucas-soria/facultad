#include <stdio.h>
#include <stdlib.h>

int main()
{
	int base, altura, result, opc;
	
	printf("Por favor ingrese el número de la operación deseada:\n");
	printf("1. Calcular Perímetro de un Rectángulo\n");
	printf("2. Calcular Superficie de un Rectángulo\n");
	printf("3. Calcular Superficie de un Triángulo\n");
	scanf("%d", &opc);
	
	printf("Ingrese la Base: ");
	scanf("%d", &base);
	printf("Ingrese la Altura: ");
	scanf("%d", &altura);
	
	switch(opc){
		case 1:
			result=(base+altura)*2;
			printf("El Perímetro del Rectángulo es: %d", result);
		break;
		case 2:
			result=base*altura;
			printf("La Superficie del Rectángulo es: %d", result);
		break;
		case 3:
			result=(base*altura)/2;
			printf("La Superficie del Triángulo es: %d", result);
		break;
	}

}

