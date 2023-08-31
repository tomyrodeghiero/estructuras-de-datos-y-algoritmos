package colecciones.main;

import colecciones.lista.Lista;
import colecciones.lista.listaenlazada.ListaEnlazada;

public class MainListaEnlazada {
    public static void main(String[] args) {
        // Crear una nueva lista enlazada
        Lista<Integer> lista = new ListaEnlazada<Integer>();

        // Agregar elementos
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        System.out.println("Lista después de agregar 1, 2 y 3: " + lista);

        // Insertar elementos
        lista.insertar(0, 3);
        lista.insertar(1, 4);
        System.out.println("Lista después de insertar 0 en la posición 3 y 1 en la posición 4: " + lista);

        // Eliminar elementos
        lista.eliminar(0);
        lista.eliminar(2);
        System.out.println("Lista después de eliminar el primer y tercer elemento: " + lista);

        // Obtener un elemento
        System.out.println("El elemento en la posición 1 es: " + lista.obtener(1));

        // Verificar si la lista contiene un elemento
        System.out.println("¿Contiene la lista el elemento número 2?: " + lista.contiene(2));

        // Mostrar la lista hasta el momento
        System.out.println("Lista hasta el momento: " + lista);

        // Obtener una sublista
        System.out.println("La sublista entre las posiciones 1 y 3 es: " + lista.subLista(1, 3));

        // Crear otra lista para agregar todos sus elementos a la lista original
        Lista<Integer> otraLista = new ListaEnlazada<Integer>();
        otraLista.agregar(5);
        otraLista.agregar(7);
        lista.agregarTodos(otraLista);
        System.out.println("Lista después de agregar todos los elementos de otra lista: " + lista);

        // Vaciar la lista
        lista.vaciar();
        System.out.println("Lista después de vaciarla: " + lista);
    }
}
