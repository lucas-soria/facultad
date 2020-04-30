

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int c=0,max=0,num,cm=0;
	
	
	//Obtencion de informacion
	do {
		printf("Ingrese un numero\n");
		scanf("%d",&num);
		
		//Contador
		c++;
		
		//Maximo
		if (c==1) {
			max =num;
		}
		if (num>max) {
			max = num;
		}
		
		//Multiplos de 5
		if (num % 5 == 0) {
			cm++;
		}
		
		
		//Imprime numeros	
		printf("%d\n",num);
	} while (num>0);
	
	printf("\n\n\nUsted ingreso %d numeros",c);
	printf("\nDe esos %d numeros, %d fueron multiplos de 5",c,cm);
	printf("\nEl mayor de todos ellos fue el %d\n\n\n",max);
	
	
	system("pause");
}

