#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


struct alumnos {
  char nom[10], ape[10];
  int lega, not[3][4];
}alu[4];


int main() {


	//Declaracion de variables
  int i, a, j, k, max;
  srand(time(NULL));


  //Info de alumnos
  alu[0].nom[10] = "Lucas";
  alu[0].ape[10] = "Soria";
  alu[0].lega = 58156;
  alu[1].nom[10] = "Juan";
  alu[1].ape[10] = "Justo";
  alu[1].lega = 24589;
  alu[2].nom[10] = "Jaime";
  alu[2].ape[10] = "Ruiz";
  alu[2].lega = 32014;
  alu[3].nom[10] = "Santi";
  alu[3].ape[10] = "Peralta";
  alu[3].lega = 36492;



	//Pone notas
  for (i = 0; i < 4; i++) {
    for ( j = 0; j < 3; j++) {
      for (k = 0; k < 4; k++) {
        alu[i].not[j][k] = rand () % 10 + 1;
      }
    }
  }


  //Obtiene indice
  printf("Ingrese el indice de un alumno (del 1 al 4): \n");
  scanf("%d", &a);
  a--;



  //Imprime info
  system("cls");
  puts(alu[a].nom);
  (alu[a].ape);
  printf("\nLegajo: %d\n\n", alu[a].lega);
  for ( i = 0; i < 3; i++) {
    switch (i) {
      case 0:
        printf("MAT ");
        break;
      case 1:
        printf("FIS ");
        break;
      case 2:
        printf("INF ");
        break;
    }
    printf("|");
    for ( j = 0; j < 4; j++) {
      printf(" %d |", alu[a].not[i][j]);
      if (j==0) {
        max = alu[a].not[i][j];
      }
      if (alu[a].not[i][j]>max) {
        max = alu[a].not[i][j];
      }
    }
    printf("\t\tNota mas alta: %d\n", max);
  }


}
