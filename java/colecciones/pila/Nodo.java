package colecciones.pila;

public class Nodo {
    private Object item;
    private Nodo siguiente;

    public Nodo() {
        item = null;
        siguiente = null;
    }

    public Nodo(Object nuevoItem) {
        item = nuevoItem;
        siguiente = null;
    }

    public Nodo(Object nuevoItem, Nodo siguienteNodo) {
        item = nuevoItem;
        siguiente = siguienteNodo;
    }

    public void cambiarItem(Object nuevoItem) {
        item = nuevoItem;
    }

    public void cambiarSiguiente(Nodo siguienteNodo) {
        siguiente = siguienteNodo;
    }

    public Object obtenerItem() {
        return item;
    }

    public Nodo obtenerSiguiente() {
        return siguiente;
    }
}
