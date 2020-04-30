#include <stdio.h>
#include <stdlib.h>
int main()
{
	int n1,n2,i=0,j=0,x,y;

   int matriz[5][5]={
							2, 2, 2, 4, 5,
							3, 4, 5, 3, 2,
							1, 1, 1, 1, 2,
							3, 3, 4, 4, 5,
							6, 7, 8, 9, 2,};

	for (i=0;i<5;i++)
	{
		for (j=0;j<5;j++)
		{
			printf("| %d |",matriz[i][j]);
		}
	printf("\n");
	}

	return 0;
}
