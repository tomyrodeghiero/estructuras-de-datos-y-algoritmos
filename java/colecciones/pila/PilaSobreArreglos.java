package colecciones.pila;

public class PilaSobreArreglos implements Pila {
    private static final int MAX_PILA = 100;
    private Object[] items;
    private int tope;

    public PilaSobreArreglos() {
        items = new Object[MAX_PILA];
        tope = -1;
    }

    @Override
    public boolean esVacia() {
        return (tope == -1);
    }

    @Override
    public void vaciar() {
        tope = -1;
    }

    @Override
    public int longitud() {
        return tope + 1;
    }

    @Override
    public void apilar(Object item) throws ExcepcionPila {
        if (tope == MAX_PILA - 1) {
            throw new ExcepcionPila("Error: La pila está llena");
        }

        tope++;
        items[tope] = item;
    }

    @Override
    public void desapilar() throws ExcepcionPila {
        if (esVacia()) {
            throw new ExcepcionPila("Error: La pila está vacía");
        }

        tope--;
    }

    @Override
    public Object tope() throws ExcepcionPila {
        if (esVacia()) {
            throw new ExcepcionPila("Error: La pila está vacía");
        }

        return items[tope];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i <= tope; i++) {
            sb.append(items[i]);
            if (i < tope)
                sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }
}
