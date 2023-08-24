#include <stdio.h>
#include <stdlib.h>

typedef struct Nodo {
    void* valor;
    struct Nodo *siguiente;
} Nodo;

typedef struct {
    Nodo *tope;
    // int elementos;
} Pila;

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

int main() {
    Pila *pilaEnteros = crearPila();
    int num1 = 5, num2 = 10;
    apilar(pilaEnteros, &num1);
    apilar(pilaEnteros, &num2);
    printf("Elementos en la pila de enteros: %d\n", elementos(pilaEnteros));

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