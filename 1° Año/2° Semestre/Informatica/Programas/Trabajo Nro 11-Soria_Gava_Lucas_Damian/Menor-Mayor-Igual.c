

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int num,cma=0,cme=0,cc=0,c=1;
	
	
	//Obtencion de informacion
	do {
		printf("Ingrese un numero entre 0 y 10\n");
		scanf("%d",&num);
		if (num>=0 && num<=10) {	
			if (num<=10 && num>=5){
				cma++;
			} else {
				if (num>0 && num<5) {
					cme++;
				} else {
					cc++;
				}
			}
			c++;
		}
	} while (c>=1 && c<=10);
	
	
	//Resultados
	printf("\n\n%d numeros son iguales a 0",cc);
	printf("\n%d numeros son menores a 5 y distintos de 0",cme);
	printf("\n%d numeros son mayores a 5",cma);
}

