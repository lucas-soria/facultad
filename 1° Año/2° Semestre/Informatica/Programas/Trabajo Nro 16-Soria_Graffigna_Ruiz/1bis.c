
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int m[10][10][10];
int camb (int i, int j, int z[3], int w);


int main() {


	//Declaracion de variables
  int i, j, k, q, w, z[3];
  srand(time(NULL));


	//Genera las naves
  for (i = 0; i < 10; i++) {
    for (j = 0; j < 10; j++) {
      for (k = 0; k < 10; k++) {
        m[i][j][k] = rand () % 10 + 1;
        if (m[i][j][k] <= 7) {
          m[i][j][k] = 0;
        } else {
          m[i][j][k] = 1;
        }
      }
    }
  }


  //Pedir los 3 puntos
  for ( w = 0; w < 3; w++) {
    //Pedir el punto
    for ( q = 0; q < 3; q++) {
      printf("Ingrese la posicion ");
      switch (q) {
        case 0:
          printf("x:\n");
          scanf("%d", &i);
          break;
        case 1:
          printf("y:\n");
          scanf("%d", &j);
          break;
        case 2:
          printf("z:\n");
          scanf("%d", &z[w]);
          break;
      }
      printf("\n\n");
    }


    //Llamado de la funcion que cambia a m[][][]
    k = camb (i, j, z, w);
    if (k == 0) {
      printf("No hubo impacto\n");
    } else {
      printf("Si hubo impacto\n");
    }
  }


  //Imprime las rodajas
  system ("cls");
    //--------------------------------------------------------------------------
    //Imprime matriz
    for ( w = 0; w < 3; w++) {
      for (i = 0; i < 10; i++) {
        printf("|");
        for (j = 0; j < 10; j++) {
          printf(" %d |", m[i][j][z[w]]);
        }
        printf("\n");
      }
      printf("\n\n\n\n");
    }
    //--------------------------------------------------------------------------
}


//Funcion que cambia los valores
int camb (int i, int j, int z[3], int w) {
  if (m[i][j][z[w]] == 0) {
    return 0;
  } else {
    m[i][j][z[w]] = 0;
    return 1;
  }
}
