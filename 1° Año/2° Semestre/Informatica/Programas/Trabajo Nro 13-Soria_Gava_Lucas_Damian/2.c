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
  int m[5][8],i,j,n,c=0;


	//Obtencion de informacion
  do {
    printf("Ingrese un numero entre 0 y 9\n");
    scanf("%d",&n );
  } while(n<0 || n>9);


  //Creacion de la matriz
  for (i=0;i<5;i++) {
    for (j=0;j<8;j++) {
      m[i][j] = rand () % 10;
      if (m[i][j] == n) {
        c++;
      }
    }
  }


  //Resultado
  printf("\n\nEl numero %d sale en la matriz %d veces\n",n,c );


}
