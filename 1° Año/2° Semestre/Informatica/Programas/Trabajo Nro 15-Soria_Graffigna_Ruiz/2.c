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


struct champion {
  char nom[20];
  float alt;
  int peso, fuerza, vel, pg;
}hero[5];

struct champion hero[]={"Deadpool", 1.89, 95, 1350, 97, 899, "Juggernaut", 2.87, 480, 8560, 66, 285, "Magneto",
1.87, 86, 6500, 847, 599, "Scarlet Witch", 1.73, 60, 210, 142, 879, "Wolverine", 1.64, 136, 1090, 73, 988};


int nom (int i[2]);


int main() {


	//Declaracion de variables
  int i[2], j, c=0, p=0, t=1;
  srand(time(NULL));

	//Obtencion de informacion
  do {
    do {
      nom(i);
      printf("Elegiste a %s \n\n\nEleji la caracteristica con la que queres pelear: \n\n\n", hero[i[0]].nom);
      printf("1.Peso:  %d kg\n2.Altura:  %.2f m\n3.Fuerza:  %d kgf\n4.Velocidad:  %d km/h\n5.Peleas Ganadas:  %d\n\n\n\n",
      hero[i[0]].peso, hero[i[0]].alt, hero[i[0]].fuerza, hero[i[0]].vel, hero[i[0]].pg);
      scanf("%d", &j);
      system("cls");
      puts(hero[i[0]].nom);
      printf("VS.\n");
      puts(hero[i[1]].nom);
      printf("\n\n");
      switch (j) {
        case 1:
          printf("%d\t\t%d\n", hero[i[0]].peso, hero[i[1]].peso);
          if (hero[i[0]].peso>hero[i[1]].peso) {
            p++;
            printf("Ganaste!!!\n");
          } else {
            c++;
            printf("Gano la computadora\n");
          }
          break;
        case 2:
          printf("%.2f\t\t%.2f\n", hero[i[0]].alt, hero[i[1]].alt);
          if (hero[i[0]].alt>hero[i[1]].alt) {
            p++;
            printf("Ganaste!!!\n");
          } else {
            c++;
            printf("Gano la computadora\n");
          }
          break;
        case 3:
          printf("%d\t\t%d\n", hero[i[0]].fuerza, hero[i[1]].fuerza);
          if (hero[i[0]].fuerza>hero[i[1]].fuerza) {
            p++;
            printf("Ganaste!!!\n");
          } else {
            c++;
            printf("Gano la computadora\n");
          }
          break;
        case 4:
          printf("%d\t\t%d\n", hero[i[0]].vel, hero[i[1]].vel);
          if (hero[i[0]].vel>hero[i[1]].vel) {
            p++;
            printf("Ganaste!!!\n");
          } else {
            c++;
            printf("Gano la computadora\n");
          }
          break;
        case 5:
          printf("%d\t\t%d\n", hero[i[0]].pg, hero[i[1]].pg);
          if (hero[i[0]].pg>hero[i[1]].pg) {
            p++;
            printf("Ganaste!!!\n");
          } else {
            c++;
            printf("Gano la computadora\n");
          }
          break;
      }
      fflush(stdin);
      getchar();
      system("cls");
    } while((c<2 && p<2) || (c<p && p<c));
    if (c<p) {
      printf("YOU WIN!!\n\n\n");
    } else {
      printf("YOY LOSE!!\n\n\n");
    }
    printf("Te gustaria jugar otra vez?\n\nSi es asi escribe 1, si no escribe 2\n");
    scanf("%d", &t);
    c=0;
    p=0;
    system("cls");
  } while(t==1);
}

int nom (int i[2]) {
  int j;
  printf("Ingrese el numero del heroe que quiere elejir:\n");
  for (j = 1; j < 6; j++) {
    printf("%d.", j);
    puts(hero[j-1].nom);
  }
  scanf("%d", &i[0]);
  i[0]--;
  i[1] = rand () % 5;
  system("cls");
}
