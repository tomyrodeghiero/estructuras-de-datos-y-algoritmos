package lista;

public class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    public Nodo() {
        dato = null;
        siguiente = null;
    }

    public Nodo(T dato) {
        this.dato = dato;
        siguiente = null;
    }
}
