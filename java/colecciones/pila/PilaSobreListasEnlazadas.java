package colecciones.pila;

public class PilaSobreListasEnlazadas implements Pila {
    private Nodo tope;

    public PilaSobreListasEnlazadas() {
        tope = null;
    }

    @Override
    public boolean esVacia() {
        return (tope == null);
    }

    @Override
    public int longitud() {
        int numItems = 0;
        Nodo corriente = tope;

        while (corriente != null) {
            numItems = numItems + 1;
            corriente = corriente.obtenerSiguiente();
        }

        return (numItems);
    }

    @Override
    public void vaciar() {
        tope = null;
    }

    @Override
    public void apilar(Object item) {
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.cambiarItem(item);
        nuevoNodo.cambiarSiguiente(tope);
        tope = nuevoNodo;
    }

    @Override
    public void desapilar() throws ExcepcionPila {
        if (tope != null) {
            tope = tope.obtenerSiguiente();
        } else {
            throw new ExcepcionPila("PilaSobreListasEnlazadas.desapilar: La pila está vacía");
        }
    }

    public Object tope() throws ExcepcionPila {
        if (tope != null) {
            return tope.obtenerItem();
        } else {
            throw new ExcepcionPila("PilaSobreListasEnlazadas.tope: La pila está vacía");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo nodoActual = tope;

        while (nodoActual != null) {
            sb.append(nodoActual.obtenerItem());
            nodoActual = nodoActual.obtenerSiguiente();

            if (nodoActual != null) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
