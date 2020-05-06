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


int vec (int a);


int main() {


	//Declaracion de variables
  int a[10], i, res;


	//Obtencion de informacion
  for ( i = 0; i < 10; i++) {
    a[i] = vec (i);
  }


  printf("Los numeros del vector son\n|");
  for ( i = 0; i < 10; i++) {
    printf(" %d |", a[i] );
  }


}


int vec (int a) {
  printf("Ingrese un numero\n");
  scanf("%d", &a);
  system ("cls");
  return a;
}
