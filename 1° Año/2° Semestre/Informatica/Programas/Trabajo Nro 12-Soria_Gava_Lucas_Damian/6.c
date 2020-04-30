

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {


	//Declaracion de variables
  int m[5][5],i,j,n,f,c,ci=0,cu=0;
  srand (time(NULL));


	//Obtencion de informacion
  for (i = 0; i <5; i++) {
    for (j = 0; j <5; j++) {
      n = rand () % (10 - 1 + 1) + 1;
      if (n<7) {
        m[i][j] = 0;
      } else {
        m[i][j] = 1;
      }
    }
  }

  //Pedir coordenadas
do {
  do {
    printf("\nIngrese un numero\n");
    scanf("%d",&f );
    printf("\nIngrese otro numero\n");
    scanf("%d",&c );
  } while(f>5 || f<1 || c>5 || c<1);
  ci++;
  if (m[f-1][c-1]==1) {
    m[f-1][c-1]=0;
    cu++;
    printf("\nUNDIDOOO!!!\n");
  } else {
    printf("\nFALLASTE!!!\n");
  }

} while(cu<3);
printf("\n\n\n\nGANASTE!!!\n\n");
printf("Hiciste %d intentos antes de ganar.\n\n\n\n",ci);


}
