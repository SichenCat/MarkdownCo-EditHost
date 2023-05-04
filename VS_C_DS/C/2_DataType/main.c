#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>


int main()
{
	int i;
	int ret;
	while (rewind(stdin), (ret = scanf("%d", &i)) != EOF)
	{
		printf("i = %d\n", i);
	}
}