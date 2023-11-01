package colecciones.lista.listaarreglo;

import colecciones.lista.Lista;

public class ListaArreglo<T> implements Lista<T> {
    private Object[] elementos;
    private int size;
    private static final int CAPACIDAD_POR_DEFECTO = 50;

    public ListaArreglo() {
        elementos = new Object[CAPACIDAD_POR_DEFECTO];
        size = 0;
    }

    @Override
    public boolean agregar(T elem) {
        if (size == elementos.length) {
            System.out.println("No se pueden agregar más elementos a la lista porque está llena.");
            return false;
        }

        elementos[size] = elem;
        size++;
        return true;
    }

    @Override
    public boolean agregarTodos(Lista<T> otraLista) {
        for (int i = 0; i < otraLista.elementos(); i++) {
            this.agregar(otraLista.obtener(i));
        }

        return true;
    }

    @Override
    public boolean insertar(T elem, int indice) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException("El índice " + indice + " está fuera de rango.");
        }

        if (size == elementos.length) {
            System.out.println("No se pueden agregar más elementos a la lista porque está llena.");
            return false;
        }

        for (int i = size; i > indice; i--) {
            elementos[i] = elementos[i - 1];
        }

        elementos[indice] = elem;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T eliminar(int indice) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException("El índice " + indice + " está fuera de rango.");
        }

        T elemento = (T) elementos[indice];

        for (int i = indice; i < size; i++) {
            elementos[i] = elementos[i + 1];
        }

        size--;
        return elemento;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T obtener(int indice) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException("El índice " + indice + " está fuera de rango.");
        }

        return (T) elementos[indice];
    }

    @Override
    public Lista<T> subLista(int desdeInd, int hastaInd) {
        ListaArreglo<T> subLista = new ListaArreglo<>();

        for (int i = desdeInd; i < hastaInd; i++) {
            subLista.agregar(obtener(i));
        }

        return subLista;
    }

    @Override
    public boolean contiene(T elem) {
        for (int i = 0; i < size; i++) {
            if (elementos[i].equals(elem)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void vaciar() {
        for (int i = 0; i < size; i++) {
            elementos[i] = null;
        }

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
        return elementos != null && size >= 0 && size <= elementos.length;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < size; i++) {
            str += elementos[i];
            if (i < size - 1) {
                str += ", ";
            }
        }
        str += "]";

        return str;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro)
            return true;
        if (!(otro instanceof ListaArreglo))
            return false;

        Lista<?> otraLista = (Lista<?>) otro;

        if (this.elementos() != otraLista.elementos())
            return false;

        for (int i = 0; i < this.elementos(); i++) {
            if (!this.obtener(i).equals(otraLista.obtener(i)))
                return false;
        }

        return true;
    }
}
