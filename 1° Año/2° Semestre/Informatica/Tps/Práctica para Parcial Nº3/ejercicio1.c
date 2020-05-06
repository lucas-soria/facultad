#include <stdio.h>
#include <stdlib.h>

int main()
{
	int num, prom, i;
	
	for(i=0;i<5;i++){
		do{
			printf("Ingrese un valor entre 50 y 100: ");
			scanf("%d", &num);
		}while(num<50 || num>100);
		
		prom=prom+num;
	}
	
	prom=prom/5;
	
	if(prom%4==0){
		printf("El Número %d es múltiplo de 4", prom);
	}
	else
	{
		printf("El Número %d no es múltiplo de 4", prom);
	}

}

