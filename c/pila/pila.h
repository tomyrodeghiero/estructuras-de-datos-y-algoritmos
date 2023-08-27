#include <stdio.h>
#include <stdlib.h>

// Tipo polimórfico Pila
typedef enum {Entero, Caracter} Tipo;

typedef struct Nodo {
    void* valor;
    struct Nodo *siguiente;
} Nodo;

typedef struct {
    Nodo *tope;
    Tipo tipo;
    int elementos;
} Pila;

// función que permite crear una pila
Pila* crearPila(Tipo tipo);

// mostrar por pantalla la pila
void mostrarPila(Pila *pila);

// - tope : Retorna el elemento en el tope de una pila no vacía sin modificar a la misma.
void* tope(Pila *pila);
// - apilar : Apila un nuevo elemento en la pila y retorna si la operación fue exitosa.
/*  0 si es exitosa la operación
 * -1 si la posición es invalida. */
int apilar(Pila *pila, void* elemento);

// - desapilar : Desapila y retorna el elemento en el tope de una pila no vacía.
void* desapilar(Pila *pila);

// - vaciar : Elimina todos los elementos de la pila dejándola vacía.
void vaciar(Pila *pila);

// - elementos : Retorna la cantidad de elementos presentes en la pila.
int elementos(Pila *pila);