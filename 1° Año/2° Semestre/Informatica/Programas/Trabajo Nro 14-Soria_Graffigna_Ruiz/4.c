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


int resta (int a, int b);
int suma (int a, int b);


int main() {


	//Declaracion de variables
  int A, B, res;


	//Obtencion de informacion
  printf("Ingrese un numero\n");
  scanf("%d", &A);
  system ("cls");
  printf("Ingrese un numero\n");
  scanf("%d", &B);
  system ("cls");

  if (A>B) {
    res = resta (A,B);
    printf("El resultado de la resta es: %d", res );
  } else {
    res = suma (A,B);
    printf("El resultado de la suma es: %d", res );
  }



}

int resta (int a, int b) {
  int c;
  c = a - b;
  return c;
}


int suma (int a, int b) {
  int c;
  c = a + b;
  return c;
}
