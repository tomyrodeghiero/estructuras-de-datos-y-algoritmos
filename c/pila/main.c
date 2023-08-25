#include <stdio.h>
#include <stdlib.h>
#include "pila.h"

/* Programa en el lenguaje C que implementa un tipo polimórfico Pila, el puede utilizarse 
para pilas de enteros y de caracteres. (Ayuda: Analizar punteros a void). 
Las operaciones que incluye esta Pila son: tope, apilar, desapilar, vaciar y elementos. */

int main() {
    Pila *pilaEnteros = crearPila();
    int num1 = 5, num2 = 10;
    apilar(pilaEnteros, &num1);
    apilar(pilaEnteros, &num2);
    printf("Elementos en la pila de enteros: %d\n", elementos(pilaEnteros));

    mostrarPila(pilaEnteros);

    Pila *pilaCaracteres = crearPila();
    char c1 = 'a', c2 = 'b', c3 = 'c';

    apilar(pilaCaracteres, &c1);
    apilar(pilaCaracteres, &c2);
    apilar(pilaCaracteres, &c3);
    printf("Elementos en la pila de caracteres: %d\n", elementos(pilaCaracteres));

    // Limpieza de la memoria
    vaciar(pilaEnteros);
    free(pilaEnteros);
    vaciar(pilaCaracteres);
    free(pilaCaracteres);

    return 0;
}