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


int calc (int a,int b,int c);
int sumafea (int a);


int main() {


	//Declaracion de variables
  int a,b,c,res;


	//Obtencion de informacion
  printf("Ingrese un numero\n");
  scanf("%d", &a );
  system ("cls");
  printf("Ingrese un numero\n");
  scanf("%d", &b );
  system ("cls");
  printf("Ingrese un numero\n");
  scanf("%d", &c );
  system ("cls");

  res = sumafea (calc (a,b,c));
  printf("El resultado del calculo es: %d", res );
}

int calc (int a, int b,int c) {
  int i,j;
  for ( i = 0; i < c; i++) {
    j = (a*b) * (a*b);
  }
  return j;
}

int sumafea (int a) {
  int s;
  s = a + 5;
  return s;
}
