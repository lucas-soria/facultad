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

float matn (int c, int i, int j);
float pmat (int i, int j, int c);
float prom (int c, int i, int j);
int legajo (int legajo);
int edad (int age);
char nom (int i);



struct clase {
  int age, lega;
  float mat[4][4], prom[4];
  char nom[20];
}alum[3];



//------------------------------------------------------------------------------



int main() {


	//Declaracion de variables
  int i, j, c=0;
  srand(time(NULL));


	//Obtencion de informacion
  printf("      LA BASE DE DATOS DE LA UM\n\n\n");
  for ( c = 0; c < 3; c++) {
    nom (c);
    edad (c);
    legajo (c);
    matn (c,i,j);
  }


  //Muestra la info
  printf("Elija el indice (del 1 al 3) del alumno: \n");
  do {
    scanf("%d", &c);
  } while(c<1 && c>3);
  system("cls");
  puts(alum[c-1].nom);
  printf("%d anos\n", alum[c-1].age );
  printf("Legajo: %d\n", alum[c-1].lega );
  pmat (i,j,c);
  prom (c,i,j);
}



//------------------------------------------------------------------------------



char nom (int i) {
  printf("Ingrese el nombre del alumno\n");
  fflush(stdin);
  gets(alum[i].nom);
  system ("cls");
  return (nom);
}


int edad (int i) {
  printf("Ingrese la edad del alumno\n");
  scanf("%d", &alum[i].age );
  system ("cls");
  return (alum[i].age);
}

int legajo (int i) {
  printf("Ingrese el legajo del alumno\n");
  scanf("%d", &alum[i].lega );
  system ("cls");
  return (alum[i].lega);
}

float matn (int c, int i, int j) {
  printf("Ingrese las notas del alumno\n");
  for ( i = 0; i < 4; i++) {
    printf("Ingrese las notas de ");
    switch (i) {
      case 0:
        printf("Matematicas\n");
        break;
      case 1:
        printf("Fisica\n");
        break;
      case 2:
        printf("Quimica\n");
        break;
      case 3:
        printf("Informatica\n");
        break;
    }
    for ( j = 0; j < 4; j++) {
      //scanf("%f", &alum[c].mat[i][j]);
      alum[c].mat[i][j] = rand () % 9 + 1;
    }
    system ("cls");
  }
}


float pmat (int i, int j, int c) {
  //--------------------------------------------------------------------------
  //Imprime matriz
  printf("\n\n\n");
  for (i = 0; i < 4; i++) {
    switch (i) {
      case 0:
        printf("MAT");
        break;
      case 1:
        printf("FIS");
        break;
      case 2:
        printf("QUI");
        break;
      case 3:
        printf("INF");
        break;
    }
    printf(" |");
    for (j = 0; j < 4; j++) {
      printf(" %.2f |", alum[c-1].mat[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");
  //--------------------------------------------------------------------------
}

float prom (int c, int i, int j) {
  float q;
  for (i = 0; i < 4; i++) {
    q=0;
    for (j = 0; j < 4; j++) {
      q = q + alum[c-1].mat[i][j];
    }
    alum[c-1].prom[i] = q/4;
    printf("Promedio");
    switch (i) {
      case 0:
        printf(" Matematicas");
        break;
      case 1:
        printf(" Fisica");
        break;
      case 2:
        printf(" Quimica");
        break;
      case 3:
        printf(" Informatica");
        break;
    }
    printf(" = %.2f\n", alum[c-1].prom[i]);
  }
}
