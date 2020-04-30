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


struct humanos {
  int altura, peso, edad, dni;
}per[10];

struct humanos per[] = {2.3, 90, 50, 20100444, 1.5, 57, 18, 42674945, 1.84, 75, 18, 42670460, 1.7, 80, 25, 23045987, 1.23, 239, 30, 12947532, 1.95, 100, 42, 12345678, 2.34, 110, 25, 87654321, 1.85, 60, 17, 87263016, 2.1, 55, 20, 27944616, 1.67, 79, 26, 73056278};
int main() {


	//Declaracion de variables
  int ps, pi, as, ai, ds, di, es, ei, i, ca=0, ce=0, cd=0, cp=0;


	//Obtencion de informacion
  printf("Ingrese el limite superior de peso\n");
  scanf("%d", &ps);
  printf("Ingrese el limite inferior de peso\n");
  scanf("%d", &pi);
  system ("cls");
  printf("Ingrese el limite superior de edad\n");
  scanf("%d", &es);
  printf("Ingrese el limite inferior de edad\n");
  scanf("%d", &ei);
  system ("cls");
  printf("Ingrese el limite superior de altura\n");
  scanf("%d", &as);
  printf("Ingrese el limite inferior de altura\n");
  scanf("%d", &ai);
  system ("cls");
  printf("Ingrese el limite superior de dni\n");
  scanf("%d", &ds);
  printf("Ingrese el limite inferior de dni\n");
  scanf("%d", &di);
  system ("cls");


  for ( i = 0; i < 10; i++) {
    if (per[i].peso>=pi && per[i].peso<=ps) {
      cp++;
    }
    if (per[i].dni>=di && per[i].dni<=ds) {
      cd++;
    }
    if (per[i].altura>=ai && per[i].altura<=as) {
      ca++;
    }
    if (per[i].edad>=ei && per[i].edad<=es) {
      ce++;
    }
  }
  printf("%d personas estan dentro del limite de peso\n\n%d personas estan dentro del limite de edad\n\n%d personas estan dentro del limite de altura\n\n%d personas estan dentro del limite de dni", cp, ce, ca, cd);
}
