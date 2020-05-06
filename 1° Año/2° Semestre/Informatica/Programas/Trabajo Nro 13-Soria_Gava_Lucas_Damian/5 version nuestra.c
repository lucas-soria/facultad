#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int main() {


	//Declaracion de variables
  int m[9][9],a[30],i,j,l,g,n,v;
  srand(time(NULL));


  //Creacion de la matriz
  for (i=0;i<9;i++) {
    for (j=0;j<9;j++) {
      m[i][j] = 0;
    }
  }

  //Creacion del vector
  for (j=0;j<30;j++) {
    a[j] = rand () % (5 - 1) + 1;
  }


  //La gilada
  i=4;
  j=4;
  m[4][4]=5;
  for (l=0;l<30;l++) {
    g=a[l];
    switch (g) {
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


    //Imprime matriz
    for (v = 0; v <9; v++) {
      for (n = 0; n <9; n++) {
        printf(" %d ",m[v][n]);
      }
      printf("\n");
    }


    getchar();

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
}
