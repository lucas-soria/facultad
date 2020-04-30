

#include <stdio.h>
#include <stdlib.h>


int main() {
	
	
	//Declarar variables
	int dia, mes, ano, M, S, DS, DA;
	
	
	//Obtencion de informacion
	printf("Ingrese su dia de nacimiento\n");
	scanf("%d",&dia);
	printf("\nIngrese el numero del mes de su nacimiento\n");
	printf("1.Enero\n2.Febrero\n3.Marzo\n4.Abril\n5.Mayo\n6.Junio\n7.Julio\n8.Agosto\n9.Septiembre\n10.Octubre\n11.Noviembre\n12.Diciembre\n");
	scanf("%d",&mes);
	printf("\nIngrese el ano de nacimiento (completo)\n");
	scanf("%d",&ano);
	
	
	//Calculo del mes
	if (mes<3){ 
		M = mes + 12;
	} else {
		M = mes;
	}
	
	
	//Siglo
	S = (ano/100) % 100;
	
	
	//Dia en que comenzo el siglo
	DS = (S/4)+ 5 * S;
	
	
	//Dia de la semana que empezo el ano
	DS = DS + S + ( S / 4);
	
	
	//Dia de la semana que comenzo el mes
	DS = DS + ((M + 1) * 26) / 10;
	
	
	//Dia de la semana en que cae
	DS = ((((DS + dia) % 7) + 5) % 7 ) + 1;
	
	
	//Resultado
	printf("\n\n\nUsted nacio el %d/%d/%d \nEse dia fue ",dia,mes,ano);
	switch (DS) {
		case 1:
			printf("Sabado");
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
		case 7:
			printf("Domingo");
			break;
	}
}

