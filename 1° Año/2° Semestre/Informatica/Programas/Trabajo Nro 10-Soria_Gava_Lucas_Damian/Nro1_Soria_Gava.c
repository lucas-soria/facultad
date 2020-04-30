/*







NOTA: Este ejercicio anda con algunas fechas. Busque distintas formas pero todas tienen la misma falla (los años bisiestos)





*/

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	
	//Declaracion de variables
	int C,D,dia,mes,cdia,ano;
	
	
	//Obtencion de informacion
	printf("Ingrese su dia de nacimiento\n");
	scanf("%d",&dia);
	printf("\nIngrese el numero del mes de su nacimiento\n");
	printf("1.Enero\n2.Febrero\n3.Marzo\n4.Abril\n5.Mayo\n6.Junio\n7.Julio\n8.Agosto\n9.Septiembre\n10.Octubre\n11.Noviembre\n12.Diciembre\n");
	scanf("%d",&mes);
	printf("\nIngrese el ano de nacimiento (completo)\n");
	scanf("%d",&ano);
	
	
	//Codigo de mes
	if(mes == 1 || mes == 2){
		D = mes + 12;
	}
	
	
	//Calculo de año bisiesto
	if (((ano%4==0 && ano%100!=0) || ano%400==0) || mes==1 || mes==2) {
		C = 1;
	} else{
		C = 0;
	}

	
	//Dia de nacimiento con la formula de zeller o congruencia de zeller
	cdia = ((dia + (((D + 1) * 13) / 5) + (ano - C) + ((ano - C) / 4) - ((ano - C) / 100) + ((ano - C) /400)) % 7);
	
	
	//Resultado
	printf("\n\n\nUsted nacio el %d/%d/%d \nEse dia fue ",dia,mes,ano);
	switch (cdia) {
		case 1:
			printf("Domingo");
			break;
		case 2:
			printf("Lunes");
			break;
		case 3:
			printf("Martes");
			break;
		case 4:
			printf("Miercoles");
			break;
		case 5:
			printf("Jueves");
			break;
		case 6:
			printf("Viernes");
			break;
		case 0:
			printf("Sabado");
			break;
		default:
			printf("que flayeas?    %d", cdia);
			break;
	}		
}

