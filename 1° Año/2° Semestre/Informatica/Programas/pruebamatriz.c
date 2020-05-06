

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int main() {


	//Declaracion de variables
  float m[3][3];
  int i,j;


	//Obtencion de informacion
  for ( i = 0; i < 3; i++) {
    for ( j = 0; j < 3; j++) {
      scanf("%f", &m[i][j]);
    }
  }
      //Imprime matriz
      printf("\n\n\n");
      printf("La matriz 5x5:\n");
      for (i = 0; i <3; i++) {
        printf("|");
        for (j = 0; j <3; j++) {
          printf(" %f |",m[i][j]);
        }
        printf("\n");
      }
      printf("\n\n\n\n");

}
