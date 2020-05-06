

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int op;
	float n1,n2,s,r,d,m;
	
	
	//Obtencion de datos
	printf("Ingrese el valor de 2 numeros\n");
	scanf("%f %f",&n1,&n2);
	printf("\n\nElija una opcion:\n1.Suma\n2.Resta\n3.Division\n4.Multiplicacion\n");
	scanf("%d",&op);

	
	//Resultado
	switch (op) {
		case 1:
			//Suma
			s = n1 + n2;
			
			printf("\n\nEl resultado de la suma es: %f",s);
			break;
		case 2:
			//Resta
			r = n1 - n2;
			
			printf("\n\nEl resultado de la resta es: %f",r);
			break;
		case 3:
			//Division
			d = n1 / n2;
			
			printf("\n\nEl resultado de la division es: %f",d);
			break;
		case 4:
			//Multiplicacion 
			m = n1 * n2;
			
			printf("\n\nEl resultado de la multiplicacion es: %f",m);
			break;
	}
}

