package colecciones.arbol;

/**
 * Clase auxiliar para diccionarios implementados con nodos encadenados.
 */
public class NodoBinario<T> {
    private T valor;
    private NodoBinario<T> izquierdo;
    private NodoBinario<T> derecho;
    private int altura;

    /**
     * Constructor completo para crear un nodo con valor, hijo izquierdo y derecho.
     */
    public NodoBinario(T valor, NodoBinario<T> izquierdo, NodoBinario<T> derecho) {
        this.valor = valor;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 1; // Cuando creamos un nodo, si es una hoja, su altura es 1.
    }

    /**
     * Constructor predeterminado para crear un nodo nulo.
     */
    public NodoBinario() {
        this.valor = null;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0; // Un nodo nulo podría considerarse de altura 0.
    }

    /**
     * Constructor para crear un nodo con solo un valor (sin hijos).
     */
    public NodoBinario(T valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; // Un nuevo nodo con valor es una hoja inicialmente, por lo tanto, altura 1.
    }

    /**
     * Obtiene el valor del nodo.
     */
    public T getValor() {
        return valor;
    }

    /**
     * Establece un nuevo valor para el nodo.
     */
    public void setValor(T nuevoValor) {
        valor = nuevoValor;
    }

    /**
     * Obtiene el hijo izquierdo del nodo.
     */
    public NodoBinario<T> getIzquierdo() {
        return izquierdo;
    }

    /**
     * Establece el hijo izquierdo del nodo.
     */
    public void setIzquierdo(NodoBinario<T> nuevoIzquierdo) {
        izquierdo = nuevoIzquierdo;
        actualizarAltura(); // Actualizar la altura después de cambiar el hijo.
    }

    /**
     * Obtiene el hijo derecho del nodo.
     */
    public NodoBinario<T> getDerecho() {
        return derecho;
    }

    /**
     * Establece el hijo derecho del nodo.
     */
    public void setDerecho(NodoBinario<T> nuevoDerecho) {
        derecho = nuevoDerecho;
        actualizarAltura(); // Actualizar la altura después de cambiar el hijo.
    }

    /**
     * Obtiene la altura del nodo.
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Establece la altura del nodo. Generalmente este método es utilizado
     * internamente
     * después de operaciones de inserción o eliminación en el árbol AVL.
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Método auxiliar para actualizar la altura del nodo basado en la altura de sus
     * hijos.
     */
    private void actualizarAltura() {
        altura = 1 + Math.max(
                izquierdo != null ? izquierdo.getAltura() : 0,
                derecho != null ? derecho.getAltura() : 0);
    }
}
