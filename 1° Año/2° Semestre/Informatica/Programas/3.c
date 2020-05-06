

#include <stdio.h>
#include <stdlib.h>


int main() {


	//Declaracion de variables
	int op;
	float b,h,c;
	
	//Obtencion de informacion
	printf("Ingrese el numero de lo que desea calcular\n\n1.Perimetro de un rectangulo\n2.Superficie de un rectangulo\n3.Superficie de un triangulo\n");
	scanf("%d",&op);
	
	
	printf("\n\nIngrese base y altura\n");
	scanf("%f %f",&b,&h);
	
	//Menu
	switch (op) {
		case 1:
			c = b * 2 + h * 2;
			break;
		case 2:
			c = b * h;
			break;
		case 3:
			c = (b * h)/2;
	}
	
	printf("\n\n\nEl resultado es: %f\n\n",c);
	
	system("pause");
}

