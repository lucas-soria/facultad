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


int recur (int a, int c);


int main() {


	//Declaracion de variables
  int a, res, i=0;


	//Obtencion de informacion
  printf("Ingrese un numero\n");
  scanf("%d", &a);

  //Factorial
  a = recur (a,i);
  printf("El coso de ese numero es: %d\n", a );
}

int recur (int a, int c) {
  if (c<5) {
    printf("El coso dentro del bucle es: %d\n", a );
    return (recur(a*2,c+1));
    c++;
  }
}
