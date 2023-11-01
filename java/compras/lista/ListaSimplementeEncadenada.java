package lista;

public class ListaSimplementeEncadenada<T> {
    Nodo<T> cabeza;

    public ListaSimplementeEncadenada() {
        cabeza = null;
    }

    public int longitud() {
        int numItems = 0;

        Nodo<T> nodoActual = cabeza;

        while (nodoActual != null) {
            numItems++;
            nodoActual = nodoActual.siguiente;
        }

        return numItems;
    }

    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> nodoActual = cabeza;

            while (nodoActual.siguiente != null) {
                nodoActual = nodoActual.siguiente;
            }

            nodoActual.siguiente = nuevoNodo;
        }
    }

    public void Listar() {
        Nodo<T> nodoActual = cabeza;

        while (nodoActual != null) {
            System.out.println(nodoActual.dato);
            nodoActual = nodoActual.siguiente;
        }
    }
}
