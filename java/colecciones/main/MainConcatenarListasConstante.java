package colecciones.main;

import colecciones.lista.listaenlazadacircular.ListaEnlazadaCircular;

public class MainConcatenarListasConstante {
    // Concatenate two double linked list circular
    public static void main(String[] args) {

        // Create a new double linked list circular
        ListaEnlazadaCircular<Integer> lista1 = new ListaEnlazadaCircular<Integer>();

        // Add elements
        lista1.agregar(1);
        lista1.agregar(2);
        lista1.agregar(3);
        lista1.agregar(4);
        lista1.agregar(1);

        // Create a new double linked list circular
        ListaEnlazadaCircular<Integer> lista2 = new ListaEnlazadaCircular<Integer>();
        // Add elements to new double linked list circular
        lista2.agregar(1);
        lista2.agregar(2);
        lista2.agregar(5);

        System.out.println("Lista 1: " + lista1);
        System.out.println("Lista 2: " + lista2);

        // Concatenate two double linked list circular in first double linked list
        // circular
        lista1.concatenar(lista2);

        System.out.println("Listas concatenadas: " + lista1);
    }
}
