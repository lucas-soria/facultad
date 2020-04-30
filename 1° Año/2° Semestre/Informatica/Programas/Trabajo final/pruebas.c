/*
Soria Gava, Lucas Damian
Graffigna Garces, Santiago
Ruiz Pitton, Juan Ignacio
*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>


float calc (int i, int j, int n, int q, int fil, int col, int sal, float res);


int main() {


	//Declaracion de variables enteras
  int i, j, n, q, fil, col, sal;
  float res=1;
  srand(time(NULL));


  //Presentacion
  printf("Buen dia!!!\n\nEsto es una...");
  sleep(3);
  system("cls");


  do {
    printf("CALCULADORA DE DETERMINANTES POR REGLA DE CHIO:\n\n");
    printf("Menu:\n1)Calcular\n2)Creadores\n3)Salir\n");
    scanf("%d", &sal);


    //Opciones
    switch (sal) {
      //Calculos
      case 1:

        system("cls");
        //Calcula
        calc (i, j, n, q, fil, col, sal, res);

        printf("Presione cualquier tecla para continuar\n");
        getch();
        system("cls");
        break;


      //Creadores
      case 2:
        system("cls");
        printf("Creadores:\n\nSoria Gava, Lucas Damian\nGraffigna Garces, Santiago\nRuiz Pitton, Juan Ignacio\n\n");
        printf("Presione cualquier tecla para continuar\n");
        getch();
        system("cls");
        break;


      //Salida
      case 3:
        system("cls");
        printf("Adios!!!\n");
    }
  } while(sal != 3);

}


//Calcula el det
float calc (int i, int j, int n, int q, int fil, int col, int sal, float res) {
  //Pregunta por el orden de la matriz
  printf("Ingrese el orden de la matriz\n");
  scanf("%d", &n);
  system("cls");


  //Declaracion de las variables reales
  float m[n][n], u, mul=1;


  //Crea la matriz de orden n
  q = n*n;
  for ( i = 0; i < n; i++) {
    for ( j = 0; j < n; j++) {
      printf("Ingrese %d numeros\n", q);
      scanf("%f", &m[i][j]);
      //m[i][j] = rand () % (10 - -10) + -10;
      q--;
      system("cls");
    }
  }


  //--------------------------------------------------------------------------
  //Imprime matriz
  printf("La matriz ingresada:\n\n");
  for (i = 0; i < n; i++) {
    printf("|");
    for (j = 0; j < n; j++) {
      printf(" %.2f |", m[i][j]);
    }
    printf("\n");
  }
  printf("\n\n\n\n");
  //--------------------------------------------------------------------------


  //En caso de que tenga dos filas/columnas iguales (por el error que causa usar todos n)
  q = 0;
  for ( i = 0; i < n; i++) {
    for ( j = 0; j < n; j++) {
      if (m[i][j] == m[i][j-1] && j != 0) {
        q++;
        if (q == (n*n)-n) {
          res = 0;
          n = -1;
        }
      }
    }
  }


  //Inicio del bucle
  while (n > 2) {


    //Busca el primer elemento no 0
    q = 0;
    for ( i = 0; i < n; i++) {
      for ( j = 0; j < n; j++) {
        if ((m[i][j] != 0) && (q == 0)) {
          q++;
          fil = i;
          col = j;
          mul = mul * m[i][j];
        }
      }
    }


    //Hace el resto de la fila 0
    for ( i = 0; i < n; i++) {
      u = m[i][col];
      for ( j = 0; j < n; j++) {
        if (i != fil) {
          m[i][j] = ( ((((-1)*(u)) / (m[fil][col])) * m[fil][j]) + (m[i][j]) );
        }
      }
    }


    //Crea la matriz auxiliar
    n--;
    float y[n][n];


    //Crea una matriz de un grado menor
    for ( i = 0; i < n; i++) {
      for ( j = 0; j < n; j++) {
        if (i < fil) {
          if (j < col) {
            y[i][j] = m[i][j];
          }
          if (j >= col) {
            y[i][j] = m[i][j+1];
          }
        }
        if (i >= fil) {
          if (j < col) {
            y[i][j] = m[i+1][j];
          }
          if (j >= col) {
            y[i][j] = m[i+1][j+1];
          }
        }
      }
    }


    //Pasar los valores de la matriz auxiliar a la real
    for ( i = 0; i < n; i++) {
      for ( j = 0; j < n; j++) {
        m[i][j] = y[i][j];
      }
    }


  }


  //Calculo del determinante
  if (n == 2) {
    res = (((m[0][0] * m[1][1]) - (m[0][1] * m[1][0])) * mul);
  }
  if (n == 1) {
    res = m[0][0];
  }


  //Resultado
  printf("\n\n\nEl determinante de la matriz es: %.2f\n\n\n", res);
}
