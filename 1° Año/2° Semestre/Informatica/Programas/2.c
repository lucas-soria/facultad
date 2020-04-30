

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int i,l1,l2,p=0;


	//Obtencion de informacion
	printf("Ingrese la medida de ambos lados\n");
	scanf("%d %d",&l1,&l2);


	//Calculo del perimetro
	p = l1 * 2 + l2 * 2;


	printf("\n\nA continuacion se mostraran todos los numeros entre 0 y el perimetro\n");
	for (i=0;i<=p;i++) {
		printf("%d\n",i);
	}

	system("pause");
}
