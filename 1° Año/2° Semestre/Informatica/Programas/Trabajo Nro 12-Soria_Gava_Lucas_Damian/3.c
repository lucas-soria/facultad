

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
  int m[3][7],j,i,t[7][3];
  srand (time(NULL));


	//Obtencion de informacion
  printf("Ingrese los valores de la matriz\n");
  for (j = 0; j <7; j++) {
    for (i = 0; i <3; i++) {
      printf("Ingrese un numero\n");
      //scanf("%d", &m[i][j]);
      m[i][j] = rand () % 10;
    }
  }

printf("\n\n\n\n");


//Mostrar matriz
  for (j = 0; j <7; j++) {
    printf("|");
    for (i = 0; i <3; i++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
printf("\n\n\n\n");


  //Crear traspuesta
  for (j = 0; j <7; j++) {
    for (i = 0; i <3; i++) {
      t[j][i] = m[i][j];
    }
  }


  //Mostrar matriz traspuesta
    for (j = 0; j <3; j++) {
      printf("|");
      for (i = 0; i <7; i++) {
        printf(" %d |",t[i][j]);
      }
      printf("\n");
    }
}
