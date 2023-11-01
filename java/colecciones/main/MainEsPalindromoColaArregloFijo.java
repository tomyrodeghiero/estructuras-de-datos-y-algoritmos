package colecciones.main;

import java.util.Scanner;

import colecciones.cola.ColaArregloFijo;

public class MainEsPalindromoColaArregloFijo {
    /*
     * En esta clase, se implementa con TAD ColaArregloFijo un programa que decide
     * // si dada una cadena ingresada por línea de comandos es un palíndromo o no
     */

    // Un palíndromo es una cadena que se lee igual de izquierda a derecha que de
    // derecha a izquierda

    public static void main(String[] args) {
        ColaArregloFijo<String> cola = new ColaArregloFijo<String>();
        ColaArregloFijo<String> colaInversa = new ColaArregloFijo<String>();

        // Ingresar palabra por línea de comandos
        System.out.printf("Ingrese una palabra a determinar si es palíndromo o no: ");
        try (Scanner scanner = new Scanner(System.in)) {
            String inputString = scanner.nextLine();

            // Encolar cada caracter de la cadena en orden original y orden inverso
            for (int i = 0; i < inputString.length(); i++) {
                String caracterOriginal = inputString.substring(i, i + 1);
                cola.encolar(caracterOriginal);

                String caracterInverso = inputString.substring(inputString.length() - i - 1, inputString.length() - i);
                colaInversa.encolar(caracterInverso);
            }

            // Comparar la cadena original con los elementos encolados en orden inverso
            boolean esPalindromo = true;
            if (!(cola).equals(colaInversa)) {
                esPalindromo = false;
            }

            if (esPalindromo)
                System.out.println("La cadena ingresada es palíndromo");
            else
                System.out.println("La cadena ingresada no es palíndromo");
        }
    }
}
