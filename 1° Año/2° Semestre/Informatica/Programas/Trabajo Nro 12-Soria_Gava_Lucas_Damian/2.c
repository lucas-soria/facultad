

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {


	//Declaracion de variables
  int v1[10],v2[10],v3[10],i;
  //srand (time(NULL));


	//Obtencion de informacion
  for (i = 0; i <10; i++) {
    do {
      printf("Ingrese un valor entre 1 y 9\n");
      scanf("%d", &v1[i]);
      //v1[i] = rand () % 25;
    } while(v1[i]>10 || v1[i]<0);
  }

  for ( i = 0; i <10; i++) {
    v2[i] = v1[9-i];
    v3[i] = v1[i] + v2[i];
  }

  printf("\nEl primer vector es: \n");
  for (i = 0; i <10 ; i++) {
    printf("%d, ",v1[i] );
  }
  printf("\nEl segundo vector es: \n");
  for (i = 0; i <10 ; i++) {
    printf("%d, ",v2[i] );
  }
  printf("\nLa suma da: \n");
  for (i = 0; i <10 ; i++) {
    printf("%d, ",v3[i] );
  }


}
