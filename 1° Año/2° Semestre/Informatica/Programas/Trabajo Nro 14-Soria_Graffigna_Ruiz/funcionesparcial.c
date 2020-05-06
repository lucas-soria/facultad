
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


int suma (int a, int c);
int resta (int b, int d);


int main() {


	//Declaracion de variables
  int a, b, c, d, q, w;


	//Obtencion de informacion
  do {
    printf("Ingrese un numero entre 0 y 100\n");
    scanf("%d", &a );
  } while(a<0 || a>100);
  do {
    printf("Ingrese un numero entre 0 y 100\n");
    scanf("%d", &b );
  } while(b<0 || b>100);
  do {
    printf("Ingrese un numero entre 100 y 200\n");
    scanf("%d", &c );
  } while(c<100 || c>200);
  do {
    printf("Ingrese un numero entre 100 y 200\n");
    scanf("%d", &d );
  } while(d<100 || d>200);

  w = suma (a,c);
  q = resta (b,d);

  if (b>a) {
    printf("La suma fue mas grande que la resta, la suma vale: %d\n", w );
  } else {
    if (a>b) {
      printf("La resta fue mas grande que la suma, la resta vale: &d\n", q );
    } else {
      printf("Ambos valen lo mismo\n");
    }
  }
}

int suma (int a, int c) {
  int i;
  i = a + c;
  return i;
}
int resta (int b, int d) {
  int i;
  i = d - b;
  return i;
}
