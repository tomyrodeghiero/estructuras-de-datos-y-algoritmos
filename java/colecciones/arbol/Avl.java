package colecciones.arbol;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Una implementación de {@code Diccionario} mediante nodos encadenados que
 * preserva,
 * las propiedades de ABB y ademas mantiene el arbol balanceado, es decir,
 * las alturas de los subárboles izquierdo y derecho de cada nodo difieren como
 * máximo en uno.
 * 
 * @param <T> Tipo del valor asociado a los nodos del árbol, debe ser posible
 *            definir un orden total para los mismos.
 * @see NodoBinario
 */
public class Avl<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;
    private int size;

    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     *
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     */
    public Avl(Comparator<? super T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
        this.size = 0;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al
     * comparador dado.
     *
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     * @param valor      de la raiz del nuevo arbol si no es null.
     */
    public Avl(Comparator<? super T> comparador, T valor) {
        this.raiz = new NodoBinario<>(valor);
        this.comparador = comparador;
        this.size = 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        raiz = insertarRec(raiz, elem);
    }

    private NodoBinario<T> insertarRec(NodoBinario<T> nodo, T elem) {
        if (nodo == null) {
            size++;
            return new NodoBinario<>(elem);
        }

        int cmp = comparador.compare(elem, nodo.getValor());
        if (cmp < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), elem));
        } else if (cmp > 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), elem));
        } else {
            // Si el elemento ya existe, decidimos no insertar duplicados.
            return nodo;
        }

        // Actualizar altura del nodo actual
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        // Balancear el árbol
        return balancear(nodo);
    }

    private NodoBinario<T> balancear(NodoBinario<T> nodo) {
        int balance = balance(nodo);
        // Si el nodo está desbalanceado, hay 4 casos
        if (balance > 1) {
            // Izquierdo izquierdo
            if (balance(nodo.getIzquierdo()) >= 0) {
                return rotarDerecha(nodo);
                // Izquierdo derecho
            } else {
                nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
                return rotarDerecha(nodo);
            }
        } else if (balance < -1) {
            // Derecho derecho
            if (balance(nodo.getDerecho()) <= 0) {
                return rotarIzquierda(nodo);
                // Derecho izquierdo
            } else {
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
                return rotarIzquierda(nodo);
            }
        }

        // No se necesita balancear
        return nodo;
    }

    private NodoBinario<T> rotarIzquierda(NodoBinario<T> nodo) {
        NodoBinario<T> nuevoRaiz = nodo.getDerecho();
        NodoBinario<T> izquierdaDeNuevoRaiz = nuevoRaiz.getIzquierdo();
        nuevoRaiz.setIzquierdo(nodo);
        nodo.setDerecho(izquierdaDeNuevoRaiz);
        actualizarAltura(nodo);
        actualizarAltura(nuevoRaiz);
        return nuevoRaiz;
    }

    private NodoBinario<T> rotarDerecha(NodoBinario<T> nodo) {
        NodoBinario<T> nuevoRaiz = nodo.getIzquierdo();
        NodoBinario<T> derechaDeNuevoRaiz = nuevoRaiz.getDerecho();
        nuevoRaiz.setDerecho(nodo);
        nodo.setIzquierdo(derechaDeNuevoRaiz);
        actualizarAltura(nodo);
        actualizarAltura(nuevoRaiz);
        return nuevoRaiz;
    }

    private void actualizarAltura(NodoBinario<T> nodo) {
        if (nodo != null) {
            nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));
        }
    }

    private int altura(NodoBinario<T> nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private int balance(NodoBinario<T> nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    @Override
    public boolean pertenece(T elem) {
        return perteneceRec(raiz, elem);
    }

    private boolean perteneceRec(NodoBinario<T> nodo, T elem) {
        if (nodo == null) {
            return false;
        }
        int cmp = comparador.compare(elem, nodo.getValor());
        if (cmp < 0) {
            return perteneceRec(nodo.getIzquierdo(), elem);
        } else if (cmp > 0) {
            return perteneceRec(nodo.getDerecho(), elem);
        } else {
            // cmp == 0, encontramos el elemento
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        raiz = borrarRec(raiz, elem);
    }

    private NodoBinario<T> borrarRec(NodoBinario<T> nodo, T elem) {
        if (nodo == null) {
            return null;
        }

        int cmp = comparador.compare(elem, nodo.getValor());
        if (cmp < 0) {
            nodo.setIzquierdo(borrarRec(nodo.getIzquierdo(), elem));
        } else if (cmp > 0) {
            nodo.setDerecho(borrarRec(nodo.getDerecho(), elem));
        } else {
            // Este es el nodo que queremos borrar
            size--;

            // Caso 1: Nodo con un solo hijo o ninguno
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            // Caso 2: Nodo con dos hijos
            // Encontrar el sucesor (el menor en el subárbol derecho)
            NodoBinario<T> sucesor = encontrarMin(nodo.getDerecho());
            // Reemplazar el valor actual por el valor del sucesor
            nodo.setValor(sucesor.getValor());
            // Eliminar el sucesor
            nodo.setDerecho(borrarRec(nodo.getDerecho(), sucesor.getValor()));
        }

        // Actualizar altura del nodo actual
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        // Balancear el árbol y devolver el nuevo nodo raíz para este subárbol
        return balancear(nodo);
    }

    private NodoBinario<T> encontrarMin(NodoBinario<T> nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    /** {@inheritDoc} */
    @Override
    public void vaciar() {
        raiz = null; // Simply set the root to null to 'empty' the tree.
        size = 0; // Reset the size to 0 as the tree is now empty.
    }

    /** {@inheritDoc} */
    @Override
    public T raiz() {
        if (raiz == null) {
            throw new NoSuchElementException("El árbol está vacío, no tiene raíz.");
        }
        return raiz.getValor();
    }

    /** {@inheritDoc} */
    @Override
    public Avl<T> subArbolIzquierdo() {
        if (raiz == null) {
            throw new NoSuchElementException("El árbol está vacío, no tiene subárbol izquierdo.");
        }
        Avl<T> subArbol = new Avl<>(comparador);
        subArbol.raiz = raiz.getIzquierdo();
        // Here we could also calculate the size of the new subtree if needed.
        return subArbol;
    }

    /** {@inheritDoc} */
    @Override
    public Avl<T> subArbolDerecho() {
        if (raiz == null) {
            throw new NoSuchElementException("El árbol está vacío, no tiene subárbol derecho.");
        }
        Avl<T> subArbol = new Avl<>(comparador);
        subArbol.raiz = raiz.getDerecho();
        // Here we could also calculate the size of the new subtree if needed.
        return subArbol;
    }

    /** {@inheritDoc} */
    @Override
    public int elementos() {
        // Since we are keeping track of the size every time we insert or delete,
        // we can just return that value.
        return size;
    }

    /** {@inheritDoc} */
    @Override
    public int altura() {
        // We use the altura method already defined, which is recursive.
        // It uses the height property of the root node, so it's an O(1) operation.
        return altura(raiz);
    }

    /** {@inheritDoc} */
    @Override
    public boolean esVacio() {
        // The tree is empty if the root node is null.
        return raiz == null;
    }

    /** {@inheritDoc} */
    @Override
    public T mayorValor() {
        if (raiz == null) {
            throw new NoSuchElementException("El árbol está vacío.");
        }
        // The largest value is in the rightmost node.
        NodoBinario<T> actual = raiz;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        return actual.getValor();
    }

    /** {@inheritDoc} */
    @Override
    public T menorValor() {
        if (raiz == null) {
            throw new NoSuchElementException("El árbol está vacío.");
        }
        // The smallest value is in the leftmost node.
        return encontrarMin(raiz).getValor();
    }

    /** {@inheritDoc} */
    @Override
    public T sucesor(T elem) {
        NodoBinario<T> actual = raiz;
        NodoBinario<T> sucesor = null;
        while (actual != null) {
            int cmp = comparador.compare(elem, actual.getValor());
            if (cmp >= 0) {
                actual = actual.getDerecho();
            } else {
                sucesor = actual;
                actual = actual.getIzquierdo();
            }
        }
        if (sucesor == null) {
            throw new NoSuchElementException("No hay sucesor para el valor dado.");
        }
        return sucesor.getValor();
    }

    /** {@inheritDoc} */
    @Override
    public T predecesor(T elem) {
        NodoBinario<T> actual = raiz;
        NodoBinario<T> predecesor = null;
        while (actual != null) {
            int cmp = comparador.compare(elem, actual.getValor());
            if (cmp <= 0) {
                actual = actual.getIzquierdo();
            } else {
                predecesor = actual;
                actual = actual.getDerecho();
            }
        }
        if (predecesor == null) {
            throw new NoSuchElementException("No hay predecesor para el valor dado.");
        }
        return predecesor.getValor();
    }

    /**
     * obtiene el balance del arbol, es decir, la diferencia de altura de sus
     * subarboles.
     * 
     * @return diferencia de altura de los subarboles.
     */
    public int balance() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /** {@inheritDoc} */
    @Override
    public boolean repOK() {
        return checkRepOKRec(raiz);
    }

    private boolean checkRepOKRec(NodoBinario<T> nodo) {
        if (nodo == null) {
            return true;
        }
        int balance = balance(nodo);
        boolean currentOk = balance >= -1 && balance <= 1;
        return currentOk && checkRepOKRec(nodo.getIzquierdo()) && checkRepOKRec(nodo.getDerecho());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        toStringRec(raiz, sb);
        sb.append("]");
        return sb.toString();
    }

    private void toStringRec(NodoBinario<T> nodo, StringBuilder sb) {
        if (nodo == null)
            return;
        if (sb.length() > 0) {
            sb.append(", "); // Añade una coma y un espacio antes de cada nodo, excepto el primero.
        }
        toStringRec(nodo.getIzquierdo(), sb);
        sb.append(nodo.getValor().toString());
        toStringRec(nodo.getDerecho(), sb);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Avl))
            return false;
        Avl<?> otherAvl = (Avl<?>) other;
        return this.toString().equals(otherAvl.toString());
    }

    /** {@inheritDoc} */
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /** {@inheritDoc} */
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
        if (nodo == null)
            return elementos;

        aListaInOrder(nodo.getIzquierdo(), elementos); // Visit left subtree
        elementos.add(nodo.getValor()); // Visit node itself
        aListaInOrder(nodo.getDerecho(), elementos); // Visit right subtree

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
    private List<T> aListaPreOrder(NodoBinario<T> nodo, List<T> elementos) {
        if (nodo == null)
            return elementos;

        elementos.add(nodo.getValor()); // Visit node itself
        aListaPreOrder(nodo.getIzquierdo(), elementos); // Visit left subtree
        aListaPreOrder(nodo.getDerecho(), elementos); // Visit right subtree

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
    private List<T> aListaPostOrder(NodoBinario<T> nodo, List<T> elementos) {
        if (nodo == null)
            return elementos;

        aListaPostOrder(nodo.getIzquierdo(), elementos); // Visit left subtree
        aListaPostOrder(nodo.getDerecho(), elementos); // Visit right subtree
        elementos.add(nodo.getValor()); // Visit node itself

        return elementos;
    }

}
