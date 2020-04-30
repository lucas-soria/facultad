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


struct triangulo {
  char nombre[20];
  int indice, lados, perimetro;
}tri[4];


int main() {


	//Declaracion de variables
  int i, j;
  tri[0].indice = 0;
  tri[0].lados = 3;
  tri[0].perimetro = 9;

	//Obtencion de informacion
  for ( i = 0; i < 4; i++) {
    if (i != 0) {
      tri[i].lados = tri[i-1].lados + 1;
      printf("Ingrese el nombre de la figura con %d lados\n", tri[i].lados);
      fflush(stdin);
      gets(tri[i].nombre);
      tri[i].indice = tri[i-1].indice + 1;
      printf("Ingrese el valor del lado del ");
      puts(tri[i].nombre);
      scanf("%d", &j);
      tri[i].perimetro = tri[i].lados * j;
    } else {
      printf("Ingrese el nombre de la figura con %d lados\n", tri[i].lados);
      fflush(stdin);
      gets(tri[i].nombre);
    }
  }
  fflush(stdin);
  system("cls");
  for ( i = 0; i < 4; i++) {
    puts(tri[i].nombre);
    printf("Indice %d\n", tri[i].indice);
    printf("Lados %d\n", tri[i].lados);
    printf("Perimetro %d\n\n", tri[i].perimetro);
  }
}
