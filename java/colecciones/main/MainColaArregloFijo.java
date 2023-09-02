package colecciones.main;

import java.util.Scanner;

import colecciones.cola.ColaArregloFijo;

public class MainColaArregloFijo {
    // En esta clase, se implementa con TAD ColaArregloFijo un programa que decide
    // si dada una cadena ingresada por línea de comandos es un palíndromo o no
    // Un palíndromo es una cadena que se lee igual de izquierda a derecha que de
    // derecha a izquierda

    public static void main(String[] args) {
        ColaArregloFijo<String> cola = new ColaArregloFijo<String>();
        ColaArregloFijo<String> colaInversa = new ColaArregloFijo<String>();

        // Ingresar palabra por línea de comandos
        System.out.printf("Ingrese una palabra a determinar si es palíndromo o no: ");
        try (Scanner scanner = new Scanner(System.in)) {
            String inputString = scanner.nextLine();

            // Encolar cada caracter de la cadena
            for (int i = 0; i < inputString.length(); i++) {
                String caracter = inputString.substring(i, i + 1);
                cola.encolar(caracter);
                colaInversa.encolar(caracter);
            }

            // Desencolar elementos de la cola inversa y ponerlos en orden inverso
            ColaArregloFijo<String> colaTemporal = new ColaArregloFijo<String>();
            while (!colaInversa.esVacia()) {
                colaTemporal.encolar(colaInversa.desencolar());
            }

            // Comparar la cadena original con los elementos desencolados
            boolean esPalindromo = true;
            while (!cola.esVacia() && !colaTemporal.esVacia()) {
                if (!cola.desencolar().equals(colaTemporal.desencolar())) {
                    esPalindromo = false;
                    break;
                }
            }

            if (esPalindromo)
                System.out.println("La cadena ingresada es palíndromo");
            else
                System.out.println("La cadena ingresada no es palíndromo");
        }
    }
}
