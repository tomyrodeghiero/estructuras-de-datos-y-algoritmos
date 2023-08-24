#include <stdio.h>
#include <stdlib.h>
#include "alista_enteros.h"	

/* ejemplo para manipular listas */

int main( int argc, char * argv[] )
{
  
  	tipo_alista arreglo = crear( );

  	printf( "arreglo inicial = " );
	mostrar( arreglo );
	printf("\n");
	
	ins( &arreglo, 1, 0 );
	ins( &arreglo, 2, 1 );
	ins( &arreglo, 3, 2 );
	ins( &arreglo, 4, 3 );
	
	// arreglo = [ 1, 2, 3, 4 ]
	
	printf( "arreglo = " );
	mostrar( arreglo );
	printf("\n");
	
	// reversa( arreglo ) = [ 4, 3, 2, 1 ]
	
	printf( "reversa( arreglo ) = " );
	mostrar( reversa( arreglo ) );
	printf("\n");
	
	tipo_alista xs = concat( arreglo, reversa( arreglo ) );
	
	// xs = [ 1, 2, 3, 4, 4, 3, 2, 1 ]
	
	printf( "concatenamos la arreglo y su reversa = " );
	mostrar( xs );
	printf("\n");

	printf( "Insertar en el arreglo original el valor 0 en la posicion 1 =" );
	
	ins( &arreglo, 0, 1 );
	
	// arreglo = [ 1, 0, 2, 3, 4 ]
	
	mostrar( arreglo );
	printf("\n");

	reemplazar(&arreglo, 17, 1);

	printf( "reemplazar( arreglo ) = " );
	mostrar( arreglo );
	printf("\n");

	intercambiar(&arreglo, 0, 1);

	printf( "intercambiar( arreglo ) = " );
	mostrar( arreglo );
	printf("\n");


  	return 0;
}
