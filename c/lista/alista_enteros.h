#define MAX_SIZE 100 // constante para el tamaño máximo del arreglo

// Objeto de un tipo incompleto.
typedef struct estructura_lista* tipo_lista;

struct estructura_lista{
    int elementos[MAX_SIZE];
    int cantidad;
};

//Retorna una lista vacía.
tipo_lista crear( void );

// consulta si la lista es vacia
int es_vacia( tipo_lista lista );

// agrega un elemento al comienzo de la lista
tipo_lista agregar( tipo_lista lista, int elemento );

// inserta un elemento en una posicion particular de la lista.
/*  0 si es exitosa la operacion
 * -1 si la posicion es invalida. */
int ins( tipo_lista lista, int elemento, int position);


// agrega un elemento al final de la lista
tipo_lista fin( tipo_lista lista, int elemento );

// elimina la cabeza de la lista (remueve el primer elemento).
/*  0 si es exitosa la operacion
 * -1 si la lista es vacia. */
int eliminar_comienzo( tipo_lista lista );

// elimina un elemento en una posicion dada.
/*  0 si es exitosa la operacion
 * -1 si la posicion es invalida o si la lista es vacia.*/
int eliminar( tipo_lista lista, int posicion);

// retorna el elemento en la posicion dada.
/* La posicion dada debe ser valida */
int obtener( tipo_lista lista, int position );

// retorna la reversa de la lista.
tipo_lista reversa( tipo_lista lista );

// concatenacion de listas.
tipo_lista concat( tipo_lista ini, tipo_lista cola );

// retorna la sub-lista comenzando en 'ini' y terminando en 'fin'
tipo_lista sub( tipo_lista lista, int ini, int fin );

// retorna una copia de la lista
tipo_lista copia( tipo_lista lista );

// retorna el primer elemento de una lista no vacia.
int cabeza( tipo_lista lista );

// retorna todos los elementos de la lista menos el primero.
tipo_lista cola( tipo_lista lista );

// muestra el contenido de la lista en la salida estandar 'std'
void mostrar( tipo_lista lista );

/* dada una lista l, un elemento e y una posición i, la función reemplaza
el elemento que estaba en la posición i de l con e y retorna el elemento que
estaba previamente en la posición i. */
int reemplazar( tipo_lista lista, int elemento, int posicion );

/* dada una lista l y dos posiciones de elementos enteros p1 y p2, la función
los intercambia. */
void intercambiar(tipo_lista lista, int p1, int p2);