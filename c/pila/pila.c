#include <stdio.h>
#include <stdlib.h>

/* Programa en el lenguaje C que implementa un tipo polimórfico Pila, el puede utilizarse 
para pilas de enteros y de caracteres. (Ayuda: Analizar punteros a void). 
Las operaciones que incluye esta Pila son: tope, apilar, desapilar, vaciar y elementos. */

typedef struct Nodo {
    void* valor;
    struct Nodo *siguiente;
} Nodo;

typedef struct {
    Nodo *tope;
} Pila;

// función que permite crear una pila
Pila* crearPila() {
    Pila *pila = (Pila*) malloc(sizeof(Pila));

    if (pila == NULL) {
        printf("No se pudo crear la pila.\n");
        exit(1);
    }

    pila->tope = NULL;
    // pila->elementos = 0;
    return pila;
}

void mostrarPila(Pila *pila) {
    Nodo *nodoTemporal = pila->tope;

    printf( "[ " );

    while (nodoTemporal != NULL) {
        printf("%d, ", nodoTemporal->valor);
        nodoTemporal = nodoTemporal->siguiente;
    }
    
    printf( "] " );
}

// - tope : Retorna el elemento en el tope de una pila no vac ́ıa sin modificar a la misma.
void* tope(Pila *pila) {
    if (pila->tope == NULL) {
        printf("La pila está vacía.\n");
        return NULL;
    }

    return pila->tope->valor;
}

// - apilar : Apila un nuevo elemento en la pila y retorna si la operación fue exitosa.
/*  0 si es exitosa la operación
 * -1 si la posición es invalida. */
int apilar(Pila *pila, void* elemento) {
    Nodo *nodo = (Nodo*) malloc(sizeof(Nodo));
    
    if (nodo == NULL) {
        printf("No se pudo crear el nodo.\n");
        return 1;
    }

    nodo->valor = elemento;
    nodo->siguiente = pila->tope;
    pila->tope = nodo;
    // pila->elementos++;
    return 0;
}

// - desapilar : Desapila y retorna el elemento en el tope de una pila no vacía.
void* desapilar(Pila *pila) {
    if (pila->tope == NULL) {
        printf("La pila está vacía.\n");
        return NULL;
    }

    Nodo *nodoTemporal = pila->tope;
    void *elemento = nodoTemporal->valor;
    pila->tope = nodoTemporal->siguiente;
    free(nodoTemporal);
    // pila->elementos--;

    return elemento;
}

// - vaciar : Elimina todos los elementos de la pila dejándola vacía.
void vaciar(Pila *pila) {
    while (pila->tope != NULL) {
        desapilar(pila);
    }
}

// - elementos : Retorna la cantidad de elementos presentes en la pila.
int elementos(Pila *pila) {
    int elementos = 0;
    Nodo *nodoTemporal = pila->tope;

    while (nodoTemporal != NULL) {
        elementos++;
        nodoTemporal = nodoTemporal->siguiente;
    }

    return elementos;
}