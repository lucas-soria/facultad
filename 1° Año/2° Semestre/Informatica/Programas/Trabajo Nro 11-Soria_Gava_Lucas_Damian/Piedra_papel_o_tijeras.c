

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
	int e,gj=0,gc=0,ra;
	srand(time(NULL));	
	
	
	do {
		//Generacion de un numero random
		ra = rand() % 3 + 1;

		//Eleccion del usuario
		do {
			printf("Elija una opcion:\n1.Piedra\n2.Papel\n3.Tijera\n");
			scanf("%d",&e);	
		} while (e<1 || e>3);
		
		//Comparacion con eleccion
		switch (ra) {
			case 1:
				if ( e == 2) {
					printf("\nGanaste\n\n\n");
					gj++;	
				} else {
					if ( e == 3) {
						printf("\nPerdiste\n\n\n");
						gc++;
					} else {
						printf("\nFue un empate\n\n\n");
					}
				}
				break;
			case 2:
				if ( e == 3) {
					printf("\nGanaste\n\n\n");
					gj++;	
				} else {
					if ( e == 1) {
						printf("\nPerdiste\n\n\n");
						gc++;
					} else {
						printf("\nFue un empate\n\n\n");
					}
				}
				break;
			case 3:
				if ( e == 1) {
					printf("\nGanaste\n\n\n");
					gj++;	
				} else {
					if ( e == 2) {
						printf("\nPerdiste\n\n\n");
						gc++;
					} else {
						printf("\nFue un empate\n\n\n");
					}
				}
				break; 
		}
	} while (gj<2 && gc<2);
	
	//Reconocimiento del ganador
	if (gj<gc) {
		printf("\n\n   **********************");
		printf("\n**  Ups! Para la proxima  **");
		printf("\n   **********************\n\n\n");
	} else {
		printf("\n\n   ******************");
		printf("\n**  Bien!!! ganaste!  **");
		printf("\n   ******************\n\n\n");
	}
}

