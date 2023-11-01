package colecciones.lista.listaenlazada;

import colecciones.lista.Lista;

public class ListaEnlazada<T> implements Lista<T> {
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int size;

    public ListaEnlazada() {
        this.cabeza = null;
        this.size = 0;
    }

    @Override
    public boolean agregar(T elem) {
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

    @Override
    public boolean agregarTodos(Lista<T> otraLista) {
        for (int i = 0; i < otraLista.elementos(); i++) {
            agregar(otraLista.obtener(i));
        }
        return true;
    }

    @Override
    public boolean insertar(T elem, int indice) {
        if (indice < 0) {
            throw new IndexOutOfBoundsException("El índice no puede ser negativo.");
        }

        Nodo nuevoNodo = new Nodo(elem);
        if (indice == 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;

            for (int i = 1; i < indice; i++) {
                if (temp.siguiente == null)
                    return false;
                temp = temp.siguiente;
            }

            nuevoNodo.siguiente = temp.siguiente;
            temp.siguiente = nuevoNodo;
        }

        size++;
        return true;
    }

    @Override
    public T eliminar(int indice) {
        if (indice < 0 || indice >= size)
            throw new IndexOutOfBoundsException("El índice está fuera de rango.");

        T elem;
        if (indice == 0) {
            elem = cabeza.dato;
            cabeza = cabeza.siguiente;
        } else {
            Nodo temp = cabeza;
            for (int i = 1; i < indice; i++) {
                temp = temp.siguiente;
            }

            elem = temp.siguiente.dato;

            temp.siguiente = temp.siguiente.siguiente;
        }

        size--;
        return elem;
    }

    @Override
    public T obtener(int indice) {
        if (indice < 0 || indice >= size)
            throw new IndexOutOfBoundsException(indice + " está fuera de rango.");

        Nodo temp = cabeza;

        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;
        }

        return temp.dato;
    }

    @Override
    public Lista<T> subLista(int desdeIndInclusivo, int hastaIndExclusivo) {
        if (desdeIndInclusivo < 0 || hastaIndExclusivo > size || desdeIndInclusivo > hastaIndExclusivo)
            throw new IndexOutOfBoundsException("Los índices están fuera de rango.");

        Lista<T> subLista = new ListaEnlazada<T>();
        Nodo temp = cabeza;
        for (int i = 0; i < hastaIndExclusivo; i++) {
            if (i >= desdeIndInclusivo) {
                subLista.agregar(temp.dato);
            }
            temp = temp.siguiente;
        }

        return subLista;
    }

    @Override
    public boolean contiene(T elem) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.dato.equals(elem))
                return true;
            temp = temp.siguiente;
        }

        return false;
    }

    @Override
    public void vaciar() {
        cabeza = null;
        size = 0;
    }

    @Override
    public int elementos() {
        return size;
    }

    @Override
    public boolean esVacia() {
        return size == 0;
    }

    @Override
    public boolean repOK() {
        if (size < 0)
            return false;

        Nodo temp = cabeza;
        int contador = 0;
        while (temp != null) {
            contador++;
            temp = temp.siguiente;
        }

        return contador == size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Nodo temp = cabeza;
        while (temp != null) {
            sb.append(temp.dato);
            temp = temp.siguiente;
            if (temp != null)
                sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object otro) {
        if (!(otro instanceof Lista))
            return false;
        Lista<?> otraLista = (Lista<?>) otro;
        if (this.elementos() != otraLista.elementos())
            return false;

        Nodo temp = cabeza;
        for (int i = 0; i < size; i++) {
            if (!temp.dato.equals(otraLista.obtener(i)))
                return false;
            temp = temp.siguiente;
        }

        return true;
    }
}
