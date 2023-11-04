package colecciones.heap;

public class HeapSort {

    public static void sort(int[] array) {
        // Crear un heap con capacidad igual al tamaño del arreglo
        Heap heap = new Heap(array.length);

        // Insertar todos los elementos del arreglo en el heap
        for (int value : array) {
            heap.insertar(value);
        }

        // Extraer los elementos del heap uno por uno y colocarlos en el arreglo
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.remover();
        }
    }

}
