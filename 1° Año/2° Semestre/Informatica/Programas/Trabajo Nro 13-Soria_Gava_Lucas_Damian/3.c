/*

Juan Ruiz
Lucas Soria
Santiago Graffigna


*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int main() {


	//Declaracion de variables



	//Obtencion de informacion
  int m[6][6],i,j,mt[6][6];
  srand(time(NULL));


  //Creacion de la matriz
  for (i=0;i<6;i++) {
    for (j=0;j<6;j++) {
      m[i][j] = rand () % (10 - 1) + 1;
    }
  }

  //Imprime matriz original
  printf("La matriz 6x6:\n");
  for (i = 0; i <6; i++) {
    printf("|");
    for (j = 0; j <6; j++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");


  //Matriz triangular superior
  for (i=0;i<6;i++) {
    for (j=0;j<6;j++) {
      mt[i][j] = m[i][j];
      if (i>j) {
        m[i][j] = 0;
      }
    }
  }


  //Imprime matriz triangular superior
  printf("La matriz triangular superior 6x6:\n");
  for (i = 0; i <6; i++) {
    printf("|");
    for (j = 0; j <6; j++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");



}
