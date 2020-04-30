

/*

	Lucas Damian Soria Gava

*/


#include <stdio.h>

void main () {
	
	
	//Declaracion de variables
	float num1,num2,s=0,r=0,d=0,m=0;
	
	
	//Obtencion de informacion
	printf("Ingrese dos numeros\n");
	scanf("%f%f",&num1,&num2);
	
	
	//Calculos
	//Suma
	s=(num1+num2);
		
	//Resta
	r=(num1-num2);
	
	//Division
	d=(num1/num2);
	
	//Multiplicacion
	m=(num1*num2);
	
	printf("El resultado de la suma de ambos numeros es: %f\n\n",s);
	printf("El resultado de la resta de ambos numeros es: %f\n\n",r);
	printf("El resultado de la division de ambos numeros es: %f\n\n",d);
	printf("El resultado de la multiplicacion de ambos numeros es: %f\n\n",m);
}
