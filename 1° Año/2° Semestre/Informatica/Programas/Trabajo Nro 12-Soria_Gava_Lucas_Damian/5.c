

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
  int m[8][5],i,j,v1[8],v2[5],n;
  srand (time(NULL));


	//Obtencion de informacion
  printf("Ingrese los valores de la matriz\n");
  for (j = 0; j <5; j++) {
    for (i = 0; i <8; i++) {
      printf("Ingrese un numero\n");
      //scanf("%d", &m[i][j]);
      m[i][j] = rand () % 10;
    }
  }


  //Da valores a v2
  for (i = 0; i <8; i++) {
    n = 0;
    for (j = 0; j <5; j++) {
      n = n + m[i][j];
    }
    v2[i] = n/5;
  }

  //Da valores a v1
  for (i = 0; i <5; i++) {
    n = 0;
    for (j = 0; j <8; j++) {
      n = n + m[i][j];
    }
    v1[i] = n/8;
  }


  //Imprime la matriz
  printf("La matriz 8x5:\n");
  for (j = 0; j <5; j++) {
    printf("|");
    for (i = 0; i <8; i++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");


  //Imprime el vector columnas
  printf("El vector del promedio de las columnas resulto ser:\n");
  for (i = 0; i <8; i++) {
		printf("%d, ",v2[i] );
	}
  printf("\n\n\n\n");


  //Imprime el vector filas
  printf("El vector del promedio de las filas resulto ser:\n");
  for (i = 0; i <5; i++) {
		printf("%d, ",v1[i] );
	}
  printf("\n\n\n\n");
}
