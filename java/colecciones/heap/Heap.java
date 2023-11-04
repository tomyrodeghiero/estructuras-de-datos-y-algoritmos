package colecciones.heap;

public class Heap {
    private int[] array; // Array para guardar los elementos del heap
    private int size; // Cantidad actual de elementos en el heap

    // Constructor
    public Heap(int capacity) {
        array = new int[capacity + 1]; // +1 porque el índice 0 no se usa
        size = 0;
    }

    // Inserta un nuevo elemento en el heap
    public void insertar(int value) {
        // Verificar si el heap está lleno
        if (size >= array.length - 1) {
            throw new IllegalStateException("El heap está lleno");
        }

        // Colocar el valor al final del heap
        array[++size] = value;

        // Flotar el valor hasta su posición correcta para mantener la propiedad del
        // heap
        flotar(size);
    }

    // Remueve y retorna el elemento en la raíz del heap (el mínimo)
    public int remover() {
        // Verificar si el heap está vacío
        if (size == 0) {
            throw new IllegalStateException("El heap está vacío");
        }

        // Guardar el valor mínimo
        int min = array[1];

        // Mover el último elemento a la raíz
        array[1] = array[size];
        array[size] = 0; // Opcional: limpiar el último nodo
        size--;

        // Hundir el nuevo valor de la raíz para mantener la propiedad del heap
        hundir(1);

        return min;
    }

    // Verifica si el heap está vacío
    public boolean esVacio() {
        return size == 0;
    }

    // Verifica la propiedad del heap (opción de debugging)
    public boolean repOk() {
        // Comprobar que cada nodo i cumple que array[i] <= array[i*2] y array[i] <=
        // array[i*2 + 1]
        for (int i = 1; i <= size / 2; i++) {
            if (array[i] > array[2 * i] || (2 * i + 1 <= size && array[i] > array[2 * i + 1])) {
                return false;
            }
        }
        return true;
    }

    // Método privado para flotar un elemento y mantener la propiedad del heap
    private void flotar(int index) {
        while (index > 1 && array[index] < array[index / 2]) {
            // Intercambiar con el padre
            swap(index, index / 2);
            // Subir al padre
            index = index / 2;
        }
    }

    // Método privado para hundir un elemento y mantener la propiedad del heap
    private void hundir(int index) {
        while (2 * index <= size) { // Mientras tenga al menos un hijo
            int hijoMenor = 2 * index; // El hijo izquierdo
            // Si tiene hijo derecho y el hijo derecho es menor que el hijo izquierdo
            if (hijoMenor < size && array[hijoMenor] > array[hijoMenor + 1]) {
                hijoMenor++; // El hijo derecho es el menor
            }
            // Si el padre es menor que el hijo menor, estamos listos
            if (array[index] <= array[hijoMenor]) {
                break;
            }
            // Intercambiar con el hijo menor
            swap(index, hijoMenor);
            // Moverse hacia abajo al hijo
            index = hijoMenor;
        }
    }

    // Método privado para intercambiar dos elementos en el heap
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
