package colecciones.lista.listaarreglo;
import colecciones.lista.Lista;
import datos.Libro;

public class ListaArreglo<T> implements Lista<T> {
    private class Nodo {
        T dato;
        Nodo elementos;

        Nodo(T dato) {
            this.dato = dato;
            this.elementos = null;
        }
    }

    private Nodo cabeza;
    private int size;
    	/**
	* La capacidad por defecto de un nuevo Catalogo.
	*/
	public static final int CAPACIDAD_POR_DEFECTO = 50;

	//el arreglo interno de libros.
	private Libro[] libros;
	//la cantidad actual de libros almacenados.
	private int nroLibros;

    public ListaArreglo() {
        this.cabeza = null;
        this.size = 0;
    }

    @Override
    public boolean agregar(T elem) {
        if (this.size == ) throw new IndexOutOfBoundsException("El índice no puede ser negativo.");
        


        Nodo nuevoNodo = new Nodo(elem);
        if (esVacia()) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
        size++;
        return true;
    }

}
