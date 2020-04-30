/*

Juan Ruiz
Lucas Soria
Santiago Graffigna


*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
  int m[5][5],v[25],p=0,i,j,c=0;
  srand(time(NULL));


	//Obtencion de informacion
  for (i=0;i<5;i++) {
    for (j=0;j<5;j++) {
      printf("Ingrese un numero\n");
      scanf("%d",&m[i][j]);
      //m[i][j] = rand () % 4;
      v[c]= m[i][j];
      c++;
    }
  }


  //Calculo del promedio
  for ( i = 0; i < 25; i++) {
      p = p + v[i];
  }
  p = p/25;


  //Imprime matriz
  printf("\n\nLa matriz 5x5:\n");
  for (i = 0; i <5; i++) {
    printf("|");
    for (j = 0; j <5; j++) {
      printf(" %d |",m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");


  //Imprime vector
  printf("|");
  for (i = 0; i <25; i++) {
    printf(" %d |",v[i]);
  }

  //Resultado
  printf("\n\n\nEl promedio de los numeros del vector es: %d \n",p );
}
