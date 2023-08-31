package main;

import racional.Racional;

public class MainRacional {
    public static void main(String[] args) {
        Racional r1 = new Racional(1, 2);
        Racional r2 = new Racional(2, 3);
        Racional r3 = new Racional(3, 4);

        Racional suma = r1.sumar(r2);
        Racional resta = r1.restar(r2);

        System.out.println("Racional 1: " + r1);
        System.out.println("Racional 2: " + r2);
        System.out.println("Racional 3: " + r3);

        System.out.println("Suma de Racional 1 y Racional 2: " + suma);
        System.out.println("Resta de Racional 1 y Racional 2: " + resta);
        System.out.println("¿Racional 1 es igual a Racional 3?: " + r1.equals(r3));

    }
}
