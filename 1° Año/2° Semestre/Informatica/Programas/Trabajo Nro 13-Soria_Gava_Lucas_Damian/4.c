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
  int m[6][6],i,j;
  srand(time(NULL));


  //Creacion de la matriz
  for (i=0;i<2;i++) {
    for (j=0;j<6;j++) {
      m[i][j] = rand () % (5 - 1) + 1;
    }
  }
  for (i=2; i<6;i++) {
    for (j=0;j<6;j++) {
      m[i][j] = m[i-2][j] + m[i-1][j];
    }
  }


  //Imprime matriz
  printf("La matriz 6x6:\n");
  for (i = 0; i <6; i++) {
    printf("|");
    for (j = 0; j <6; j++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");
}
