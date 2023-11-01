package colecciones.main;

import java.util.Scanner;

import colecciones.pila.ExcepcionPila;
import colecciones.pila.PilaSobreArreglos;

public class MainInvertirPilaSobreArreglos {
    /*
     * Este algoritmo toma como argumento una secuencia de números y los imprima
     * en orden inverso utilizando una pila
     */

    public static void main(String[] args) throws ExcepcionPila {
        PilaSobreArreglos pila = new PilaSobreArreglos();

        // Ingresar secuencia de números por línea de comandos
        System.out.printf("Ingrese una secuencia de números: ");
        try (Scanner scanner = new Scanner(System.in)) {
            // int inputNumber = Integer.parseInt(scanner.nextLine());
            long inputNumber = Long.parseLong(scanner.nextLine());

            while (inputNumber > 0) {
                try {
                    /*
                     * int num = inputNumber % 10;
                     * long num = inputNumber % 10;
                     * pila.apilar(num);
                     * inputNumber /= 10;
                     */

                    long cantidadDeDigitosInputNumber = cantidadDeDigitos(inputNumber);

                    int denominador = (int) Math.round(Math.pow(10, cantidadDeDigitosInputNumber - 1));

                    long num = inputNumber / denominador;
                    pila.apilar(num);

                    inputNumber = inputNumber % (long) Math.round(Math.pow(10, cantidadDeDigitosInputNumber - 1));
                } catch (ExcepcionPila event) {
                    System.out.println("Error: La pila está llena" + event.getMessage());
                }
            }

            // Imprimir los números en orden inverso
            // 1. System.out.println("La secuencia de números en orden inverso es: " +
            // pila);

            // 2.
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = pila.longitud() - 1; i >= 0; i--) {
                sb.append(pila.tope());
                pila.desapilar();
                if (i > 0) {
                    sb.append(", ");
                }
            }

            sb.append("]");

            System.out.println("La secuencia de números en orden inverso es: " + sb.toString());

        } catch (NumberFormatException event) {
            System.out.println("Error: Por favor, ingrese una secuencia de números válida." + event.getMessage());
        }
    }

    public static int cantidadDeDigitos(long numero) {
        int cantidad = 0;
        while (numero > 0) {
            numero /= 10;
            cantidad++;
        }
        return cantidad;

    }
}
