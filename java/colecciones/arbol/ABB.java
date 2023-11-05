package colecciones.arbol;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

/**
 * Árbol binario de busqueda (ABB), es una implementación de {@code Diccionario}
 * mediante nodos encadenados que preserva las propiedades de Diccionario.
 * 
 * @param <T> Tipo del valor asociado a los nodos del árbol, debe ser posible
 *            definir un orden total para los mismos.
 * @see NodoBinario
 */
public class ABB<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;

    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     * 
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     */
    public ABB(Comparator<? super T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al
     * comparador dado.
     * 
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     * @param valor      de la raiz del nuevo arbol si no es null.
     */
    public ABB(Comparator<? super T> comparador, T valor) {
        this.comparador = comparador;
        this.raiz = new NodoBinario<T>(valor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        raiz = insertarRecursivo(raiz, elem);
    }

    private NodoBinario<T> insertarRecursivo(NodoBinario<T> nodo, T elem) {
        // Si el nodo actual es null, significa que hemos encontrado la posición
        // para insertar el nuevo nodo, así que creamos uno nuevo con el elemento.
        if (nodo == null) {
            return new NodoBinario<T>(elem);
        }

        // Si el elemento es menor que el valor del nodo actual, se procede a insertarlo
        // en el subárbol izquierdo.
        if (comparador.compare(elem, nodo.getValor()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), elem));
        }
        // Si el elemento es mayor que el valor del nodo actual, se procede a insertarlo
        // en el subárbol derecho.
        else if (comparador.compare(elem, nodo.getValor()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), elem));
        }

        // Se retorna el nodo actual para seguir con la construcción del árbol en los
        // niveles superiores.
        return nodo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        return perteneceRecursivo(raiz, elem);
    }

    private boolean perteneceRecursivo(NodoBinario<T> nodo, T elem) {
        if (nodo == null) {
            return false;
        }

        if (comparador.compare(elem, nodo.getValor()) == 0) {
            return true;
        }

        if (comparador.compare(elem, nodo.getValor()) < 0) {
            return perteneceRecursivo(nodo.getIzquierdo(), elem);
        } else {
            return perteneceRecursivo(nodo.getDerecho(), elem);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        raiz = eliminarRec(elem, raiz);
    }

    private NodoBinario<T> eliminarRec(T elem, NodoBinario<T> nodo) {
        // Si el nodo actual es nulo, no hay nada que eliminar.
        if (nodo == null) {
            return null;
        }

        // Compara el elemento a eliminar con el valor actual del nodo.
        int compareResult = comparador.compare(elem, nodo.getValor());

        // Si el elemento es menor que el valor actual, se busca en el subárbol
        // izquierdo.
        if (compareResult < 0) {
            nodo.setIzquierdo(eliminarRec(elem, nodo.getIzquierdo()));
        }
        // Si el elemento es mayor, se busca en el subárbol derecho.
        else if (compareResult > 0) {
            nodo.setDerecho(eliminarRec(elem, nodo.getDerecho()));
        }
        // Cuando se encuentra el elemento:
        else if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
            // Si el nodo a eliminar tiene dos hijos, se busca el máximo en el subárbol
            // izquierdo.
            nodo.setValor(buscarMayor(nodo.getIzquierdo()).getValor());
            // Luego, se elimina el nodo máximo que se utilizó para reemplazar el valor,
            // para evitar duplicados en el árbol.
            nodo.setIzquierdo(eliminarRec(nodo.getValor(), nodo.getIzquierdo()));
        }
        // Si el nodo tiene solo un hijo o ninguno:
        else {
            // Se reemplaza el nodo actual con su único hijo, o con null si no tiene hijos.
            nodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
        }

        // Retorna el nodo actualizado o null si el nodo fue eliminado y no tenía hijos.
        return nodo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void vaciar() {
        raiz = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T raiz() {
        if (raiz == null) {
            throw new IllegalStateException("El árbol está vacío.");
        }
        return raiz.getValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolIzquierdo() {
        if (raiz == null || raiz.getIzquierdo() == null) {
            return new ABB<>(comparador);
        }

        ABB<T> subArbol = new ABB<>(comparador);
        subArbol.raiz = raiz.getIzquierdo();
        return subArbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolDerecho() {
        if (raiz == null || raiz.getDerecho() == null) {
            return new ABB<>(comparador);
        }

        ABB<T> subArbol = new ABB<>(comparador);
        subArbol.raiz = raiz.getDerecho();
        return subArbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int elementos() {
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(NodoBinario<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRec(nodo.getIzquierdo()) + contarNodosRec(nodo.getDerecho());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int altura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(NodoBinario<T> nodo) {
        if (nodo == null) {
            return -1; // Asume que la altura de un árbol vacío es -1, así la altura de un nodo hoja es
                       // 0.
        }
        return 1 + Math.max(calcularAltura(nodo.getIzquierdo()), calcularAltura(nodo.getDerecho()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * {@inheritDoc}
     */
    public T mayorValor() {
        if (raiz == null)
            return null;
        return buscarMayor(raiz).getValor();
    }

    private NodoBinario<T> buscarMayor(NodoBinario<T> nodo) {
        while (nodo.getDerecho() != null) {
            nodo = nodo.getDerecho();
        }
        return nodo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T menorValor() {
        if (raiz == null)
            return null;
        return buscarMenor(raiz).getValor();
    }

    private NodoBinario<T> buscarMenor(NodoBinario<T> nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T sucesor(T elem) {
        NodoBinario<T> nodo = buscarNodo(raiz, elem);

        // Si el nodo no se encuentra en el árbol
        if (nodo == null) {
            return null;
        }

        // Si el nodo tiene subárbol derecho, el sucesor es el valor menor de ese
        // subárbol
        if (nodo.getDerecho() != null) {
            return buscarMenor(nodo.getDerecho()).getValor();
        } else {
            return null;
        }
    }

    private NodoBinario<T> buscarNodo(NodoBinario<T> raizActual, T valor) {
        while (raizActual != null) {
            int cmp = comparador.compare(valor, raizActual.getValor());
            if (cmp < 0) {
                raizActual = raizActual.getIzquierdo();
            } else if (cmp > 0) {
                raizActual = raizActual.getDerecho();
            } else {
                return raizActual; // valor encontrado
            }
        }
        return null; // valor no encontrado
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T predecesor(T elem) {
        NodoBinario<T> nodo = buscarNodo(raiz, elem);

        // Si el nodo no se encuentra en el árbol
        if (nodo == null) {
            return null;
        }

        // Si el nodo tiene subárbol izquierdo, el predecesor es el valor máximo de ese
        // subárbol
        if (nodo.getIzquierdo() != null) {
            return buscarMayor(nodo.getIzquierdo()).getValor();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean repOK() {
        return isABB(raiz, null, null);
    }

    private boolean isABB(NodoBinario<T> nodo, T min, T max) {
        if (nodo == null)
            return true;

        if (min != null && comparador.compare(nodo.getValor(), min) <= 0)
            return false;
        if (max != null && comparador.compare(nodo.getValor(), max) >= 0)
            return false;

        return isABB(nodo.getIzquierdo(), min, nodo.getValor()) && isABB(nodo.getDerecho(), nodo.getValor(), max);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        toStringRec(raiz, result);
        return "[" + result.toString() + "]";
    }

    private void toStringRec(NodoBinario<T> nodo, StringBuilder sb) {
        if (nodo == null)
            return;

        // Recorrido inorden: izquierda, nodo, derecha
        toStringRec(nodo.getIzquierdo(), sb);

        if (sb.length() != 0) {
            sb.append(", ");
        }
        sb.append(nodo.getValor());

        toStringRec(nodo.getDerecho(), sb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        ABB<?> that = (ABB<?>) other;
        return equalsRec(this.raiz, that.raiz);
    }

    private boolean equalsRec(NodoBinario<T> nodo1, NodoBinario<?> nodo2) {
        if (nodo1 == null && nodo2 == null)
            return true;
        if (nodo1 == null || nodo2 == null)
            return false;

        return nodo1.getValor().equals(nodo2.getValor()) &&
                equalsRec(nodo1.getIzquierdo(), nodo2.getIzquierdo()) &&
                equalsRec(nodo1.getDerecho(), nodo2.getDerecho());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista(Orden orden) {
        List<T> elementos = new LinkedList<>();
        switch (orden) {
            case INORDER:
                return aListaInOrder(raiz, elementos);
            case PREORDER:
                return aListaPreOrder(raiz, elementos);
            case POSTORDER:
                return aListaPostOrder(raiz, elementos);
        }
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> nodo, List<T> elementos) {
        if (nodo == null) {
            return elementos;
        }

        aListaInOrder(nodo.getIzquierdo(), elementos);
        elementos.add(nodo.getValor());
        aListaInOrder(nodo.getDerecho(), elementos);
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz == null) {
            return elementos;
        }

        elementos.add(raiz.getValor());
        aListaPreOrder(raiz.getIzquierdo(), elementos);
        aListaPreOrder(raiz.getDerecho(), elementos);
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz == null) {
            return elementos;
        }

        aListaPostOrder(raiz.getIzquierdo(), elementos);
        aListaPostOrder(raiz.getDerecho(), elementos);
        elementos.add(raiz.getValor());
        return elementos;
    }
}
