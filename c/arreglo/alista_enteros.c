#include <stdio.h>

/*
	Implementación de un arreglo de enteros 
	usando la estructura de lista de enteros.
*/

#define CAPACIDAD_MAX 100 // constante para el tamaño máximo del arreglo

// Definición de la estructura de la lista basada en el arreglo
typedef struct {
    int elementos[CAPACIDAD_MAX];
    int cantidad;
} tipo_alista;

// Inicializar un arreglo vacío
tipo_alista crear() {
    tipo_alista arreglo;
    arreglo.cantidad = 0;
    return arreglo;
}

// Consulta si el arreglo es vacío
int es_vacia(tipo_alista arreglo) {
    if (arreglo.cantidad == 0) {
        return 1;
    } else {
        return 0;
    }
}

// Agrega un elemento al comienzo del arreglo
tipo_alista agregar (tipo_alista arreglo, int elemento) {
    if (arreglo.cantidad == CAPACIDAD_MAX) {
        printf("Error: Arreglo llena. \n");
        return arreglo;
    }

    for (int i = arreglo.cantidad; i > 0; i--) {
        arreglo.elementos[i] = arreglo.elementos[i-1];
    }

    arreglo.elementos[0] = elemento;
    arreglo.cantidad++;
    return arreglo;
}

// Inserta un elemento en una posición particular del arreglo.
/*  0 si es exitosa la operacion
 * -1 si la posición es invalida. */
int ins(tipo_alista *arreglo, int elemento, int posicion) {
    if (arreglo->cantidad == CAPACIDAD_MAX) {
        printf("Error: Arreglo llena. \n");
        return -1;
    }

    if (posicion < 0 || posicion > arreglo->cantidad) {
        printf("Error: Posición inválida. \n");
        return -1;
    }

    for (int i = arreglo->cantidad; i > posicion; i--) {
        arreglo->elementos[i] = arreglo->elementos[i-1];
    }

    arreglo->elementos[posicion] = elemento;
    arreglo->cantidad++;

    return 0;
}

// Agrega un elemento al final del arreglo
tipo_alista fin(tipo_alista arreglo, int elemento) {
    if (arreglo.cantidad == CAPACIDAD_MAX) {
        printf("Error: Arreglo llena. \n");
        return arreglo;
    }

    arreglo.elementos[arreglo.cantidad] = elemento;
    arreglo.cantidad++;
    return arreglo;
}

// Elimina la cabeza del arreglo (remueve el primer elemento)
/*  0 si es exitosa la operacion
 * -1 si el arreglo es vacío. */
int eliminar_comienzo( tipo_alista arreglo ) {
    if (arreglo.cantidad == 0) {
        printf("Error: Arreglo vacío. \n");
        return -1;
    }

    for (int i = 0; i < arreglo.cantidad; i++) {
        arreglo.elementos[i] = arreglo.elementos[i+1];
    }

    arreglo.cantidad--;
    return 0;
}

// Retorna el elemento en la posición dada
/* La posición dada debe ser válida */
int eliminar(tipo_alista arreglo, int posicion) {
    if (posicion < 0 || posicion >= arreglo.cantidad) {
        printf("Error: Posición inválida. \n");
        return -1;
    }

    int elemento_eliminado = arreglo.elementos[posicion];

    for (int i = posicion; i < arreglo.cantidad - 1; i++) {
        arreglo.elementos[i] = arreglo.elementos[i+1];
    }

    arreglo.cantidad--;

    return elemento_eliminado;
}

// Retorna el elemento en la posición dada.
/* La posición dada debe ser válida */
int obtener( tipo_alista arreglo, int posicion ) {
    if (posicion < 0 || posicion >= arreglo.cantidad) {
        printf("Error: Posición inválida.\n");
        return -1;
    }

    return arreglo.elementos[posicion];
}

// Retorna la reversa del arreglo
tipo_alista reversa( tipo_alista arreglo) {
    tipo_alista arreglo_reversa;
    arreglo_reversa.cantidad = arreglo.cantidad;
    for (int i = 0; i < arreglo.cantidad; i++){
        arreglo_reversa.elementos[i] = arreglo.elementos[arreglo.cantidad-1-i];
    }

    return arreglo_reversa;
}

// Concatenacion de arreglos
tipo_alista concat( tipo_alista ini, tipo_alista cola ) {
    tipo_alista arreglo_concat;
    if (ini.cantidad + cola.cantidad > CAPACIDAD_MAX) {
        printf("Error: La capacidad total excede la capacidad máxima.\n");
        arreglo_concat.cantidad = 0;
        return arreglo_concat;
    }

    for (int i = 0; i < ini.cantidad; i++) {
        arreglo_concat.elementos[i] = ini.elementos[i];
    }

    for (int i = 0; i < cola.cantidad; i++) {
        arreglo_concat.elementos[ini.cantidad + i] = cola.elementos[i];
    }

    arreglo_concat.cantidad = ini.cantidad + cola.cantidad;

    return arreglo_concat;
}

// Retorna el sub-arreglo comenzando en 'ini' y terminando en 'fin'
tipo_alista sub(tipo_alista arreglo, int ini, int fin) {
    tipo_alista sub_arreglo;

    if (ini < 0 || fin >= arreglo.cantidad || ini > fin) {
        printf("Error: Límites inválidos para sub-arreglo.\n");
        sub_arreglo.cantidad = 0;
        return sub_arreglo;
    }

    int j = 0;
    for (int i = ini; i <= fin; i++) {
        sub_arreglo.elementos[j] = arreglo.elementos[i];
    }

    sub_arreglo.cantidad = j;

    return sub_arreglo;
}

// Retorna una copia del arreglo
tipo_alista copia(tipo_alista arreglo) {
    tipo_alista copia_arreglo;

    for (int i = 0; i < arreglo.cantidad; i++) {
        copia_arreglo.elementos[i] = arreglo.elementos[i];
    }

    copia_arreglo.cantidad = arreglo.cantidad;

    return copia_arreglo;
}

// Retorna el primer elemento de un arreglo no vacío
int cabeza(tipo_alista arreglo) {
    if (es_vacia(arreglo)) {
       printf("Error: Lista vacía. \n");
        return -1; 
    }

    return arreglo.elementos[0];
}


// Retorna todos los elementos del arreglo menos el primero
tipo_alista cola(tipo_alista arreglo) {
    tipo_alista cola_arreglo;

    if (arreglo.cantidad <= 1) {
        cola_arreglo.cantidad = 0;
        return cola_arreglo;
    }

    for (int i = 1; i < arreglo.cantidad; i++) {
        cola_arreglo.elementos[i-1] = arreglo.elementos[i];
    }

    cola_arreglo.cantidad = arreglo.cantidad - 1;

    return cola_arreglo;
}

// Muestra el contenido del arreglo en la salida estandar 'std'
void mostrar( tipo_alista arreglo ) {
    printf("[");

    for (int i = 0; i < arreglo.cantidad; i++) {
        printf("%d", arreglo.elementos[i]);

        if (i < arreglo.cantidad - 1) {
            printf(", ");
        }
    }

    printf("]\n");
}

/* Dado un arreglo a, un elemento e y una posición i, la función reemplaza
el elemento que estaba en la posición i de a con e y retorna el elemento que
estaba previamente en la posición i */
int reemplazar(tipo_alista *arreglo, int elemento, int posicion) {
    if (posicion < 0 || posicion >= arreglo->cantidad) {
        printf("Error: Posición inválida. \n");
        return -1;
    }

    int elemento_previo = arreglo->elementos[posicion];
    arreglo->elementos[posicion] = elemento;

    return elemento_previo;
}

/* Dado un arreglo a y dos posiciones de elementos enteros p1 y p2, la función
los intercambia */
void intercambiar(tipo_alista *arreglo, int p1, int p2) {
    if (p1 < 0 || p1 >= arreglo->cantidad || p2 < 0 || p2 >= arreglo->cantidad) {
        printf("Error: Posición inválida. \n");
        return;
    }

    int valor_p1_temporal = arreglo->elementos[p1];
    arreglo->elementos[p1] = arreglo->elementos[p2];
    arreglo->elementos[p2] = valor_p1_temporal;
}