

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int main() {


	//Declaracion de variables
  int a[6][5],q[6],i=0,j=0,r,c;
  srand(time(NULL));


	//Obtencion de informacion
  printf("Ingrese los numeros de la primer fila de  la matriz:\n\n");
  for ( i = 0; i < 6; i++) {
    for ( j = 0; j < 5; j++) {
      if (j==0) {
        printf("Ingrese un numero:\n");
        //scanf("%d\n", &a[j][i]);
        a[i][j] = rand () % (15 - 1) + 1;
      }
      if (j != 0) {
        a[i][j] = a[i][j-1] * 2;
      }
      if (a[i][j]>20 && c == 0) {
        r = j+1;
        c++;
      } else {
        r = 0;
      }
    }
    q[i] = r;
    c = 0;
  }


    //Imprime matriz
    printf("La matriz 6x5:\n");
    for (i = 0; i <6; i++) {
      printf("|");
      for (j = 0; j <5; j++) {
        printf(" %d |",a[i][j]);
      }
      printf("\n");
    }
    printf("\n\n\n\n");


    //Imprime el vector
    for ( i = 0; i < 6; i++) {
      printf("%d\n",q[i]);
    }


  }
