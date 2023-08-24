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
tipo_alista crear(void);

// Consulta si el arreglo es vacío
int es_vacia(tipo_alista arreglo);

// Agrega un elemento al comienzo del arreglo
tipo_alista agregar (tipo_alista arreglo, int elemento);

// Inserta un elemento en una posición particular del arreglo.
/*  0 si es exitosa la operacion
 * -1 si la posición es invalida. */
int ins(tipo_alista *arreglo, int elemento, int posicion);

// Agrega un elemento al final del arreglo
tipo_alista fin(tipo_alista arreglo, int elemento);

// Elimina la cabeza del arreglo (remueve el primer elemento)
/*  0 si es exitosa la operacion
 * -1 si el arreglo es vacío. */
int eliminar_comienzo( tipo_alista arreglo );

// Retorna el elemento en la posición dada
/* La posición dada debe ser válida */
int eliminar(tipo_alista arreglo, int posicion);

// Retorna el elemento en la posición dada.
/* La posición dada debe ser válida */
int obtener( tipo_alista arreglo, int posicion );

// Retorna la reversa del arreglo
tipo_alista reversa( tipo_alista arreglo);

// Concatenacion de arreglos
tipo_alista concat( tipo_alista ini, tipo_alista cola );

// Retorna el sub-arreglo comenzando en 'ini' y terminando en 'fin'
tipo_alista sub(tipo_alista arreglo, int ini, int fin);

// Retorna una copia del arreglo
tipo_alista copia(tipo_alista arreglo);

// Retorna el primer elemento de un arreglo no vacío
int cabeza(tipo_alista arreglo);

// Retorna todos los elementos del arreglo menos el primero
tipo_alista cola(tipo_alista arreglo);

// Muestra el contenido del arreglo en la salida estandar 'std'
void mostrar( tipo_alista arreglo );

/* Dado un arreglo a, un elemento e y una posición i, la función reemplaza
el elemento que estaba en la posición i de a con e y retorna el elemento que
estaba previamente en la posición i */
int reemplazar(tipo_alista *arreglo, int elemento, int posicion);

/* Dado un arreglo a y dos posiciones de elementos enteros p1 y p2, la función
los intercambia */
void intercambiar(tipo_alista *arreglo, int p1, int p2);