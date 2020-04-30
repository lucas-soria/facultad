
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

//m[i][j] = rand () % 4;
//srand(time(NULL));


void delay(int milliseconds);


int main() {


	//Declaracion de variables
  int a[27][27],s[1], i, j,fil=13, col=13,as;



	//Obtencion de informacion
  //posicion inicial es 13,13
  for ( i = 0; i < 27; i++) {
    for ( j = 0; j < 27; j++) {
      if (i == 0 || j == 0 || i == 26 || j == 26) {
        a[i][j] = 2;
      } else {
        if (i == fil && j == col) {
          a[i][j] = 1;
        } else {
          a[i][j] = 0;
        }
      }
    }
  }


  //--------------------------------------------------------------------------
  //Imprime matriz
  for (i = 0; i < 27; i++) {
    for (j = 0; j < 27; j++) {
      if (a[i][j] == 0) {
        printf("   ");
      } else {
        if (i == fil && j == col) {
          as = 64;
          printf(" %c ",43);
        } else {
          printf(" %c ", 35);
      }
    }
  }
    printf("\n");
  }
  //--------------------------------------------------------------------------

  //delay(500);


  //42 ascii para comidita
  //35 para bordes
  //43 y 111 para la snake

  //para tomar el teclado a= getche();

  //system ("cls");
}


void delay(int milliseconds)
{
    long pause;
    clock_t now,then;

    pause = milliseconds*(CLOCKS_PER_SEC/1000);
    now = then = clock();
    while( (now-then) < pause )
        now = clock();
}

int posicionc (int col, int a) {
  int cola;
  cola = col;
  if (a == 72) {
    col--;
  } else {
    if (a == 80) {
      col++;
    }
  }
}
