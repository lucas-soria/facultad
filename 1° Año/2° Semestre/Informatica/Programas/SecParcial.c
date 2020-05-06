

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int main() {


	//Declaracion de variables
  int m[5][5],p[5],s[5],i,j,suma[5],h;
  srand(time(NULL));


	//Obtencion de informacion
  printf("Ingrese los valores de una matriz\n\n\n");
  for ( i = 0; i < 5; i++) {
    for ( j = 0; j < 5; j++) {
      printf("Ingrese un numero\n");
      //scanf("%d\n", &m[i][j]);
      m[i][j] = rand () % 4;


      //Diagonal principal
      if ( i == j ) {
        p[i] = m[i][j];
      }
    }
  }

  for (i = 0; i < 5; i++) {
    s[i] = m[i][4-i];
  }

  //Imprime matriz
  printf("\n\n\n");
  printf("La matriz 5x5:\n");
  for (i = 0; i <5; i++) {
    printf("|");
    for (j = 0; j <5; j++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");


  //Imprime el vector de diagonal principal
  printf("El vector de la diagonal principal:\n|");
  for (i = 0; i <5; i++) {
    printf(" %d |",p[i]);
  }
  printf("\n\n\n");


  //Imprime el vector de diagonal principal
  printf("El vector de la diagonal secundaria:\n|");
  for (i = 0; i <5; i++) {
    printf(" %d |",s[i]);
  }
  printf("\n\n\n");


  //Imprime la suma de ambos vectores
  printf("La suma de las diagonales:\n|");
  for ( i = 0; i < 5; i++) {
      suma[i] = p[i] + s[i];
      printf(" %d |",suma[i]);
  }
  printf("\n\n\n");

}
