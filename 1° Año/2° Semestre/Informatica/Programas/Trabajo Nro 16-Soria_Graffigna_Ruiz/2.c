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


int suma (int i, int j, int p);


int main() {


	//Declaracion de variables
  int i, j, d;



	//Obtencion de informacion
  for ( i = 0; i < 3; i++) {
    if (i==0) {
      printf("Ingrese un valor:\n");
      scanf("%d", &d );
    }
    printf("\nIngrese otro valor:\n");
    scanf("%d", &j );
    d = suma (d, j, i);
    printf("El resultado es: %d", d);
  }


}


int suma (int i, int j, int p) {
  int d;
  if (p == 2) {
    d = i - j;
  } else {
    d = i + j;
  }
  return  d;
}
