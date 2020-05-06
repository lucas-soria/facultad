

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
  int m1[5][5],m2[5][5],m3[5][5],i,j;
  srand(time(NULL));


	//Obtencion de informacion
  printf("Ingrese los valores de la primera matriz\n");
  for (j = 0; j <5; j++) {
    for (i = 0; i <5; i++) {
      printf("Ingrese un numero\n");
      //scanf("%d", &m1[i][j]);
      m1[i][j] = rand () % 4;
    }
  }
  printf("Ingrese los valores de la segunda matriz\n");
  for (j = 0; j <5; j++) {
    for (i = 0; i <5; i++) {
      printf("Ingrese un numero\n");
      //scanf("%d", &m2[i][j]);
      m2[i][j] = rand () % 4;
      m3[i][j] = m1[i][j] + m2[i][j];
    }
  }


  //Imprime la primer matriz
  printf("La primer matriz 5x5:\n");
  for (j = 0; j <5; j++) {
    printf("|");
    for (i = 0; i <5; i++) {
      printf(" %d |",m1[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");


  //Imprime la segunda matriz
  printf("La segunda matriz 5x5:\n");
  for (j = 0; j <5; j++) {
    printf("|");
    for (i = 0; i <5; i++) {
      printf(" %d |",m2[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");


  //Imprime la tercer matriz
  printf("La tercer matriz 5x5:\n");
  for (j = 0; j <5; j++) {
    printf("|");
    for (i = 0; i <5; i++) {
      printf(" %d |",m3[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");

}
