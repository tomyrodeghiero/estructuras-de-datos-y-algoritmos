package colecciones.cola;

import colecciones.lista.listaenlazada.ListaEnlazada;

public class ColaConListas<T> implements Cola<T> {
    private ListaEnlazada<T> lista;

    public ColaConListas() {
        this.lista = new ListaEnlazada<T>();
    }

    @Override
    public boolean esVacia() {
        return lista.esVacia();
    }

    @Override
    public boolean encolar(T elem) {
        return lista.agregar(elem);
    }

    @Override
    public T desencolar() {
        if (esVacia()) {
            throw new RuntimeException("La cola está vacía.");
        }

        T elem = lista.obtener(0);
        lista.eliminar(0);
        return elem;
    }

    @Override
    public T primero() {
        if (esVacia()) {
            throw new RuntimeException("La cola está vacía.");
        }

        return lista.obtener(0);
    }

    @Override
    public void vaciar() {
        lista.vaciar();
    }

    @Override
    public int elementos() {
        return lista.elementos();
    }

    @Override
    public boolean repOK() {
        return lista.repOK();
    }

    @Override
    public String toString() {
        return lista.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColaConListas<?>))
            return false;

        ColaConListas<?> otra = (ColaConListas<?>) obj;
        return lista.equals(otra.lista);
    }
}
