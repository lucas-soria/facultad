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
  int m[9][9],a[30],i,j,l,g,n,v,p;
  srand(time(NULL));


  //Creacion de la matriz
  for (i=0;i<9;i++) {
    for (j=0;j<9;j++) {
      m[i][j] = 0;
    }
  }

  //Procedimiento

  i=4;
  j=4;
  for (l=0;l<30;l++) {


    //Imprime matriz
    for (v = 0; v <9; v++) {
      for (n = 0; n <9; n++) {
        printf(" %d ",m[v][n]);
      }
      printf("\n");
    }


    scanf("%d", &a[l]);
    switch (a[l]) {
      case 1:
        i=i-1;
        m[i][j] = 1;
        break;
      case 2:
        i=i+1;
        m[i][j] = 2;
        break;
      case 3:
        j=j+1;
        m[i][j] = 3;
        break;
      case 4:
        j=j-1;
        m[i][j] = 4;
        break;
    }


    //Limpiar pantalla
    system("cls");
  }


  //Imprime matriz
  for (v = 0; v <9; v++) {
    for (n = 0; n <9; n++) {
      printf(" %d ",m[v][n]);
    }
    printf("\n");
  }


  //Imprime vector
  printf("\n\nLos numeros del vector son: \n");
  for (n = 0; n <30; n++) {
    printf(" %d, ",a[n]);
  }
}
