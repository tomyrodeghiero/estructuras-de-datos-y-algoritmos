package colecciones.lista.listaenlazadacircular;

import colecciones.lista.Lista;

public class ListaEnlazadaCircular<T> implements Lista<T> {
    // Create a double linked list
    public class Nodo {
        T dato;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo cabeza;
    private int size;
    private Nodo ultimo;

    // Create a circular double linked list
    public ListaEnlazadaCircular() {
        this.cabeza = null;
        this.size = 0;
        this.ultimo = null;
    }

    // Add getters
    public Nodo getCabeza() {
        return cabeza;
    }

    public int getSize() {
        return size;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    // Add setters
    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    // Add methods
    public boolean esVacia() {
        return cabeza == null;
    }

    public boolean agregar(T elem) {
        // Add element to double linked list
        Nodo nuevoNodo = new Nodo(elem);

        if (esVacia()) {
            cabeza = nuevoNodo;
            ultimo = nuevoNodo;
            ultimo.siguiente = cabeza;
            cabeza.anterior = ultimo;
        } else {
            ultimo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimo;
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
            ultimo = nuevoNodo;
        }

        size++;
        return true;
    }

    public boolean insertar(T elem, int indice) {
        if (indice < 0) {
            throw new IndexOutOfBoundsException("El índice no puede ser negativo.");
        }

        Nodo nuevoNodo = new Nodo(elem);
        if (indice == 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            ultimo.siguiente = cabeza;
            cabeza.anterior = ultimo;
        } else {
            Nodo temp = cabeza;

            for (int i = 1; i < indice; i++) {
                if (temp.siguiente == null)
                    return false;
                temp = temp.siguiente;
            }

            nuevoNodo.siguiente = temp.siguiente;
            temp.siguiente = nuevoNodo;
            nuevoNodo.anterior = temp;
            nuevoNodo.siguiente.anterior = nuevoNodo;
        }

        size++;
        return true;
    }

    public T eliminar(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("El índice no puede ser negativo o mayor al tamaño de la lista.");
        }

        Nodo temp = cabeza;
        T datoEliminado = null;

        if (indice == 0) {
            datoEliminado = cabeza.dato;
            cabeza = cabeza.siguiente;
            ultimo.siguiente = cabeza;
            cabeza.anterior = ultimo;
        } else {
            for (int i = 1; i < indice; i++) {
                temp = temp.siguiente;
            }

            datoEliminado = temp.siguiente.dato;
            temp.siguiente = temp.siguiente.siguiente;
            temp.siguiente.anterior = temp;
        }

        size--;
        return datoEliminado;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("El índice no puede ser negativo o mayor al tamaño de la lista.");
        }

        Nodo temp = cabeza;

        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;
        }

        return temp.dato;
    }

    public boolean concatenar(ListaEnlazadaCircular<T> otraLista) {
        if (otraLista.esVacia()) {
            return false;
        }

        if (this.esVacia()) {
            this.cabeza = otraLista.cabeza;
            this.ultimo = otraLista.ultimo;
            this.size = otraLista.size;

            return true;
        }

        this.ultimo.siguiente = otraLista.cabeza;
        otraLista.cabeza.anterior = this.ultimo;
        this.cabeza.anterior = otraLista.ultimo;
        otraLista.ultimo.siguiente = this.cabeza;

        return true;
    }

    // Implement toString
    @Override
    public String toString() {
        if (cabeza == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Nodo temp = cabeza;
        do {
            sb.append(temp.dato);
            temp = temp.siguiente;
            if (temp != cabeza) {
                sb.append(", ");
            }
        } while (temp != cabeza);

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean agregarTodos(Lista<T> otraLista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarTodos'");
    }

    @Override
    public Lista<T> subLista(int desdeInd, int hastaInd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subLista'");
    }

    @Override
    public boolean contiene(T elem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contiene'");
    }

    @Override
    public void vaciar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'vaciar'");
    }

    @Override
    public int elementos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'elementos'");
    }

    @Override
    public boolean repOK() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repOK'");
    }

}
