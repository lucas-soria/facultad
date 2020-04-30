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


float suma (float a, float b);
float resta (float a, float b);
float multi (float a, float b);
float divi (float a, float b);


int main() {


	//Declaracion de variables
  int p;
  float a, b, resp;


	//Obtencion de informacion
  printf("Ingrese un numero\n");
  scanf("%f", &a );
  system ("cls");
  printf("Ingrese un numero\n");
  scanf("%f", &b );
  system ("cls");

  printf("Ingrese una opcion\n\n1:Suma\n2:Resta\n3:Division\n4:Multiplicacion\n");
  scanf("%d", &p );
  system ("cls");
  //Opciones
  switch (p) {
    case 1:
      resp = suma (a,b);
      break;
    case 2:
      resp = resta (a,b);
      break;
    case 3:
      resp = divi (a,b);
      break;
    case 4:
      resp = multi (a,b);
      break;
  }


  //llamando a funciones
  printf("El resultado de la operacion es: %f", resp );

}


float suma (float a, float b){
    float r;
    r = a + b;
    return r;
}
float resta (float a, float b){
  float r;
  r = a - b;
  return r;
}
float divi (float a, float b){
  float r;
  r = a / b;
  return r;
}
float multi (float a, float b){
  float r;
  r = a * b;
  return r;
}
