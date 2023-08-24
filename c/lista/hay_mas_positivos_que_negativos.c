#include <stdio.h>
#include <stdlib.h>
#include "lista_enteros.h"	

/* Programa que, dada una lista de enteros, determina si contiene más elementos posivos
 que negativos. */

int main( int argc, char * argv[] )
{
  	tipo_lista lista = crear( );
	
	ins( lista, 1, 0 );
	ins( lista, -2, 1 );
	ins( lista, 0, 2 );
	ins( lista, 4, 3 );
	
	printf( "Lista = " );
	mostrar( lista );
	printf("\n");
	
	nodo cursor = lista->cabeza;

	int positivos = 0;
	int negativos = 0;

	while (cursor != NULL) {
		if (cursor->valor > 0){
			positivos++;
		}
		
		if (cursor->valor < 0){
			negativos++;
		}

		cursor = cursor->siguiente;
	}

	printf("Positivos = %d\n", positivos);
	printf("Negativos = %d\n", negativos);

	if (positivos > negativos) {
		printf("Hay más elementos positivos que negativos.\n");
	} else if (positivos == negativos) {
		printf("Hay la misma cantidad de elementos positivos que negativos.\n");
	} else {
		printf("Hay más elementos negativos que positivos.\n");
	}

  return 0;
}
