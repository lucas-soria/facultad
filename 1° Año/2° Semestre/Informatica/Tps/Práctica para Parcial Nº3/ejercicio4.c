#include <stdio.h>
#include <stdlib.h>

int main()
{
	int num1, num2, i;
	
	do{
		printf("Ingrese un número entre 0 y 50: ");
		scanf("%d",&num1);
	}while(num1<0 || num1>50);
	
	do{
		printf("Ingrese un número entre 51 y 100: ");
		scanf("%d",&num2);
	}while(num2<51 || num2>100);
	
	do{
		for(i=num1;i<=num2;i++){
			printf("%d ",i);
		}
		printf("\n");
		num1++;
		for(i=num2;i>=num1;i--){
			printf("%d ",i);
		}
		num2--;
		printf("\n");
	}while(num1<num2);
}

