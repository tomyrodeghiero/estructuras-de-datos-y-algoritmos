package main;

import datos.Libro;
import catalogo.Catalogo;

/**
 * La clase {@code MainCatalogo} implementa una aplicación simple para mostrar
 * el uso de {@code Catalogo} junto con {@code Libro}.
 * Esta clase no usa parámetros, el comportamiento está completamente
 * "hardcodeado" requiriendo modificiar y recompilar para hacer pruebas.
 * 
 * @see catalogo.Catalogo
 * @see datos.Libro
 * @version 1.0
 */
public class MainCatalogo {
	/**
	 * Experimenta con {@code Catalogo}, agregar libros, buscar, y mostrar el
	 * {@code Catalogo}.
	 * 
	 * @param args : arguments for this main method, not used in this
	 *             implementation.
	 */
	public static void main(String[] args) {
		// Crear algunos libros
		Libro libro1 = new Libro("Isaac Asimov", "The Caves of Steel", 42);
		Libro libro2 = new Libro("Isaac Asimov", "The Naked Sun", 47);
		Libro libro3 = new Libro("Programmer 3", "Computer 3", 53);

		// Crear un catálogo con capacidad para 10 libros
		Catalogo catalogo = new Catalogo(10);

		// Agregar libros al catálogo
		catalogo.agregarLibro(libro1);
		catalogo.agregarLibro(libro2);
		catalogo.agregarLibro(libro3);

		// Mostrar el catálogo
		System.out.println("Estado inicial del catálogo:\n" + catalogo);

		// Buscar un libro por su título
		Libro libroBuscado = catalogo.buscarPorTitulo("Computer 3");
		if (libroBuscado != null) {
			System.out.println("Libro encontrado: " + libroBuscado);
		} else {
			System.out.println("Libro no encontrado");
		}

		// Intentar agregar un libro al catálogo
		Libro libro4 = new Libro("Programmer 4", "Computer 4", 74);
		boolean agregado = catalogo.agregarLibro(libro4);
		if (agregado) {
			System.out.println("Libro agregado exitosamente: " + libro4);
		} else {
			System.out.println("No se pudo agregar el libro: " + libro4 + ". El catálogo ya está lleno.");
		}

		// Mostrar el contenido del catálogo
		System.out.println("Catalogo:\n" + catalogo);
	}
}
