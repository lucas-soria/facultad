/*
Soria Gava, Lucas Damian
Graffigna Garces, Santiago
Ruiz Pitton, Juan Ignacio
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 9;
//srand(time(NULL));


int comparador (int numerito1,int numerito2);


int main() {


	//Declaracion de variables
  int n1, n2, resp;


	//Obtencion de informacion
  printf("Ingrese un numero\n");
  scanf("%d", &n1 );
  system ("cls");
  printf("Ingrese un numero\n");
  scanf("%d", &n2 );
  system ("cls");
  resp = comparador (n1,n2);
  printf("El mayor numero es %d\n", resp );

}


int comparador (int numerito1, int numerito2) {
  if (numerito1 > numerito2) {
    return numerito1;
  } else {
    return numerito2;
  }
}
