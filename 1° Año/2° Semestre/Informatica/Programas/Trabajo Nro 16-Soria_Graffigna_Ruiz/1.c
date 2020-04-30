/*
Soria Gava, Lucas Damian
Graffigna Garces, Santiago
Ruiz Pitton, Juan Ignacio
*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int veri (int i, int j, int k, int p);
int a[10][10][10];

int main() {


	//Declaracion de variables
  int i, j, k, p, c=0, d;
  srand(time(NULL));


	//Obtencion de informacion


  //Pone las naves en la matriz
  for ( i = 0; i < 10; i++) {
    for ( j = 0; j < 10; j++) {
      for ( k = 0; k < 10; k++) {
        p = rand () % 10 + 1;
        if (p >= 7) {
          a[i][j][k] = 1;
        } else {
          a[i][j][k] = 0;
        }
      }
    }
  }

  do {
    //Pregunta por coordenadas
    printf("Ingrese una coordenada en x:\n");
    scanf("%d", &i);
    printf("Ingrese una coordenada en y:\n");
    scanf("%d", &j);
    printf("Ingrese una coordenada en z:\n");
    scanf("%d", &k);

    //La funcion analiza si hay o no impacto
    d = veri (i, j, k, p);

    //Dice si impacto o no
    if (d == 1) {
      printf("Hubo impacto\n");
    } else {
      printf("No hubo impacto\n");
    }

    c++;



    //--------------------------------------------------------------------------
    //Imprime matriz
    printf("\n\n\n");
    for (i = 0; i < 10; i++) {
      printf("|");
      for (j = 0; j < 10; j++) {
        printf(" %d |", a[i][j][k]);
      }
      printf("\n");
    }
    printf("\n\n\n\n");
    //--------------------------------------------------------------------------
    fflush(stdin);
    getchar ();
    system ("cls");

  } while(c<3);
}


int veri (int i, int j, int k, int p) {
  if (a[i][j][k] == 1) {
    a[i][j][k] = 0;
    return 1;
  } else {
    return 0;
  }
}
