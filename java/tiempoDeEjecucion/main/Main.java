public class Main {

    public static int suma1(int n) {
        return (n * (n + 1) / 2);
    }

    public static int suma(int n) {
        if (n == 0)
            return 0;
        return (n + suma(n - 1));
    }

    public static void main(String args[]) {
        int n = 25000; // Un número representativo para la prueba

        // Medir tiempo para suma1
        long tiempoInicial1 = System.nanoTime();
        suma1(n);
        long tiempoFinal1 = System.nanoTime();
        long tiempoTotal1 = tiempoFinal1 - tiempoInicial1;

        // Medir tiempo para suma
        long tiempoInicial2 = System.nanoTime();
        suma(n);
        long tiempoFinal2 = System.nanoTime();
        long tiempoTotal2 = tiempoFinal2 - tiempoInicial2;

        System.out.println("Tiempo para suma1: " + tiempoTotal1 + " nanosegundos");
        System.out.println("Tiempo para suma (recursiva): " + tiempoTotal2 + " nanosegundos");
    }
}
