#include <stdio.h>
#include <stdlib.h>

/*
	Implementación de un arreglo de enteros 
	usando la estructura de lista de enteros.
*/
// Realizan con last

#define MAX_SIZE 100 // constante para el tamaño máximo del arreglo

typedef struct estructura_lista* tipo_lista;

// Definición de la estructura de la lista basada en el arreglo
struct estructura_lista {
    int elementos[MAX_SIZE];
    int cantidad;
};

// Inicializar un arreglo vacío
tipo_lista crear() {
    tipo_lista lista = (tipo_lista) malloc (sizeof(struct estructura_lista));
    lista->cantidad = 0;

    return lista;
}

// Consulta si el arreglo es vacío
int es_vacia(tipo_lista lista) {
    return (lista->cantidad == 0);
}

// Agrega un elemento al comienzo del arreglo
tipo_lista agregar( tipo_lista lista, int elemento ) {
    if (lista->cantidad == MAX_SIZE) {
        printf("Error: Arreglo llena. \n");
        return lista;
    }

    for (int i = lista->cantidad; i > 0; i--) {
        lista->elementos[i] = lista->elementos[i-1];
    }

    lista->elementos[0] = elemento;
    lista->cantidad++;
    return lista;
}

// Inserta un elemento en una posición particular del arreglo.
/*  0 si es exitosa la operacion
 * -1 si la posición es invalida. */
int ins( tipo_lista lista, int elemento, int position) {
    printf("Cantidad de la lista: %d. \n", lista->cantidad);

    if (lista->cantidad == MAX_SIZE) {
        printf("Error: Arreglo llena. \n");
        return -1;
    }

    if (position < 0 || position > lista->cantidad) {
        printf("Error: Posición inválida. \n");
        return -1;
    }

    for (int i = lista->cantidad; i > lista->cantidad; i--) {
        lista->elementos[i] = lista->elementos[i-1];
    }

    lista->elementos[position] = elemento;
    lista->cantidad++;

    return 0;
}

// Agrega un elemento al final del arreglo
tipo_lista fin( tipo_lista lista, int elemento ) {
    if (lista->cantidad == MAX_SIZE) {
        printf("Error: Arreglo llena. \n");
        return lista;
    }

    lista->elementos[lista->cantidad] = elemento;
    lista->cantidad++;
    return lista;
}

// Elimina la cabeza del arreglo (remueve el primer elemento)
/*  0 si es exitosa la operacion
 * -1 si el arreglo es vacío. */
int eliminar_comienzo( tipo_lista lista ) {
    if (lista->cantidad == 0) {
        printf("Error: Arreglo vacío. \n");
        return -1;
    }

    for (int i = 0; i < lista->cantidad; i++) {
        lista->elementos[i] = lista->elementos[i+1];
    }

    lista->cantidad--;
    return 0;
}

// Retorna el elemento en la posición dada
/* La posición dada debe ser válida */
int eliminar( tipo_lista lista, int posicion) {
    if (posicion < 0 || posicion >= lista->cantidad) {
        printf("Error: Posición inválida. \n");
        return -1;
    }

    int elemento_eliminado = lista->elementos[posicion];

    for (int i = posicion; i < lista->cantidad - 1; i++) {
        lista->elementos[i] = lista->elementos[i+1];
    }

    lista->cantidad--;

    return elemento_eliminado;
}

// Retorna el elemento en la posición dada.
/* La posición dada debe ser válida */
int obtener( tipo_lista lista, int position ) {
    if (position < 0 || position >= lista->cantidad) {
        printf("Error: Posición inválida.\n");
        return -1;
    }

    return lista->elementos[position];
}

// Retorna la reversa del arreglo
tipo_lista reversa( tipo_lista lista ) {
    tipo_lista arreglo_reversa = (tipo_lista) malloc (sizeof(struct estructura_lista));
    arreglo_reversa->cantidad = lista->cantidad;
    for (int i = 0; i < lista->cantidad; i++){
        arreglo_reversa->elementos[i] = lista->elementos[lista->cantidad-1-i];
    }

    return arreglo_reversa;
}

// Concatenacion de arreglos
tipo_lista concat( tipo_lista ini, tipo_lista cola ) {
    tipo_lista arreglo_concat = (tipo_lista) malloc (sizeof(struct estructura_lista));
    
    if (ini->cantidad + cola->cantidad > MAX_SIZE) {
        printf("Error: La capacidad total excede la capacidad máxima.\n");
        arreglo_concat->cantidad = 0;
        return arreglo_concat;
    }

    for (int i = 0; i < ini->cantidad; i++) {
        arreglo_concat->elementos[i] = ini->elementos[i];
    }

    for (int i = 0; i < cola->cantidad; i++) {
        arreglo_concat->elementos[ini->cantidad + i] = cola->elementos[i];
    }

    arreglo_concat->cantidad = ini->cantidad + cola->cantidad;

    return arreglo_concat;
}

// Retorna el sub-arreglo comenzando en 'ini' y terminando en 'fin'
tipo_lista sub( tipo_lista lista, int ini, int fin ) {
    tipo_lista sub_arreglo;

    if (ini < 0 || fin >= lista->cantidad || ini > fin) {
        printf("Error: Límites inválidos para sub-arreglo.\n");
        sub_arreglo->cantidad = 0;
        return sub_arreglo;
    }

    int j = 0;
    for (int i = ini; i <= fin; i++) {
        sub_arreglo->elementos[j] = lista->elementos[i];
    }

    sub_arreglo->cantidad = j;

    return sub_arreglo;
}

// Retorna una copia del arreglo
tipo_lista copia( tipo_lista lista ) {
    tipo_lista copia_arreglo;

    for (int i = 0; i < lista->cantidad; i++) {
        copia_arreglo->elementos[i] = lista->elementos[i];
    }

    copia_arreglo->cantidad = lista->cantidad;

    return copia_arreglo;
}

// Retorna el primer elemento de un arreglo no vacío
int cabeza( tipo_lista lista ) {
    if (es_vacia(lista)) {
       printf("Error: Lista vacía. \n");
        return -1; 
    }

    return lista->elementos[0];
}


// Retorna todos los elementos del arreglo menos el primero
tipo_lista cola( tipo_lista lista ) {
    tipo_lista cola_arreglo;

    if (lista->cantidad <= 1) {
        cola_arreglo->cantidad = 0;
        return cola_arreglo;
    }

    for (int i = 1; i < lista->cantidad; i++) {
        cola_arreglo->elementos[i-1] = lista->elementos[i];
    }

    cola_arreglo->cantidad = lista->cantidad - 1;

    return cola_arreglo;
}

// Muestra el contenido del arreglo en la salida estandar 'std'
void mostrar( tipo_lista lista ) {
    printf("[");

    for (int i = 0; i < lista->cantidad; i++) {
        printf("%d", lista->elementos[i]);

        if (i < lista->cantidad - 1) {
            printf(", ");
        }
    }

    printf("]\n");
}

/* Dado un arreglo a, un elemento e y una posición i, la función reemplaza
el elemento que estaba en la posición i de a con e y retorna el elemento que
estaba previamente en la posición i */
int reemplazar( tipo_lista lista, int elemento, int posicion ) {
    if (posicion < 0 || posicion >= lista->cantidad) {
        printf("Error: Posición inválida. \n");
        return -1;
    }

    int elemento_previo = lista->elementos[posicion];
    lista->elementos[posicion] = elemento;

    return elemento_previo;
}

/* Dado un arreglo a y dos posiciones de elementos enteros p1 y p2, la función
los intercambia */
void intercambiar(tipo_lista lista, int p1, int p2) {
    if (p1 < 0 || p1 >= lista->cantidad || p2 < 0 || p2 >= lista->cantidad) {
        printf("Error: Posición inválida. \n");
        return;
    }

    int valor_p1_temporal = lista->elementos[p1];
    lista->elementos[p1] = lista->elementos[p2];
    lista->elementos[p2] = valor_p1_temporal;
}