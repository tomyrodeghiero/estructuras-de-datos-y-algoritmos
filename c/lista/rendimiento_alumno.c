#include <stdio.h>
#include <stdlib.h>
#include "lista_enteros.h"	

/* Dada una lista de valores enteros, que representa las notas de los exámenes finales del rendimiento
académico de un estudiante, este es un programa, en el lenguaje C, que calcula el promedio con
aplazos y sin aplazos del estudiante. */

int main( int argc, char * argv[] )
{
    tipo_lista lista = crear( );
	
	ins( lista, 1, 0 );
	ins( lista, 2, 1 );
	ins( lista, 3, 2 );
	ins( lista, 4, 3 );
	ins( lista, 5, 4 );
	ins( lista, 6, 5 );
	ins( lista, 7, 6 );
	ins( lista, 8, 7 );
	ins( lista, 9, 8 );
	ins( lista, 10, 9 );
	
	
	printf( "Lista = " );
	mostrar( lista );
	printf("\n");
	
	nodo cursor = lista->cabeza;

	int notasDesaprobadas = 0;
	int notasAprobadas = 0;
    int sumaNotasDesaprobadas = 0;
    int sumaNotasAprobadas = 0;

	while (cursor != NULL) {
		if (cursor->valor >=1 && cursor->valor < 5){
			notasDesaprobadas++;
            sumaNotasDesaprobadas += cursor->valor;
		} else if (cursor->valor >= 5 && cursor->valor <= 10){
			notasAprobadas++;
            sumaNotasAprobadas += cursor->valor;
		} else {
            printf("La nota %d no es válida. Por favor, ingrese una notra entre del 1 y al 10.\n", cursor->valor);
        }

		cursor = cursor->siguiente;
	}

    printf("Notas desaprobadas: %d\n", notasDesaprobadas);
    printf("Notas aprobadas: %d\n", notasAprobadas);
    printf("Suma de notas desaprobadas: %d\n", sumaNotasDesaprobadas);
    printf("Suma de notas aprobadas: %d\n", sumaNotasAprobadas);
    
    float promedioNotasDesaprobadas = (notasDesaprobadas > 0) ? (float) sumaNotasDesaprobadas / notasDesaprobadas : 0.00;
    float promedioNotasAprobadas = (notasDesaprobadas > 0) ? (float) sumaNotasAprobadas / notasAprobadas : 0.00;

    printf("Promedio de notas desaprobadas: %.2f\n", promedioNotasDesaprobadas);
    printf("Promedio de notas aprobadas: %.2f\n", promedioNotasAprobadas);

  return 0;
}
