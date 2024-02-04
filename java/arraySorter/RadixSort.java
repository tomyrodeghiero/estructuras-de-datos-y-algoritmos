public class RadixSort {

    // Una función utilitaria para obtener el valor máximo en arr[]
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // Una función para hacer el ordenamiento de arr[] de acuerdo al dígito
    // representado por exp.
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // arreglo de salida
        int i;
        int count[] = new int[10];
        java.util.Arrays.fill(count, 0);

        // Almacenar el conteo de ocurrencias en count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Cambiar count[i] para que count[i] ahora contenga la posición actual de este
        // dígito en output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Construir el arreglo de salida
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copiar el arreglo de salida a arr[], para que arr[] ahora contenga los
        // números ordenados
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // La función principal para ordenar arr[] de tamaño n usando Radix Sort
    static void radixsort(int arr[], int n) {
        // Encontrar el número máximo para saber la cantidad de dígitos
        int m = getMax(arr, n);

        // Hacer el ordenamiento por conteo para cada dígito. Nota que en lugar de pasar
        // el dígito, exp es pasado.
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    // Una función de utilidad para imprimir un arreglo
    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    // Método principal para probar la clase RadixSort
    public static void main(String[] args) {
        int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
        int n = arr.length;
        radixsort(arr, n);
        print(arr, n);
    }
}
