

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int n1,n2,cn1=0,cn2=0,n=0,i=0;
	
	
	//Obtencion de informacion
	do {
		printf("Ingrese un numero entre 0 y 50\n");
		scanf("%d",&n1);
	} while (n1<0 || n1>50);
	do {
		printf("Ingrese un numero entre 51 y 100\n");
		scanf("%d",&n2);
	} while (n2<51 || n2>100);
	
	
	printf("\n\n\n");
	//Desarrollo del programa
	cn1 = n1;
	cn2 = n2;
	do {
		for (n1;n1<=n2;n1++) {
			printf("%d ",n1);
		}
		printf("\n");
		cn1++;
		n1 = cn1;
		for (n2;n2>=n1;n2--) {
			printf("%d ",n2);
		}
		printf("\n");
		cn2--;
		n2 = cn2;	
	} while (n1<=n2);
	
	printf("\n\n\n");
	
	system("pause");
}

