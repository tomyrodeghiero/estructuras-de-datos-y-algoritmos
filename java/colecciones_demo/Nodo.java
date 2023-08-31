package colecciones_demo; // colecciones.Nodo
// import java.util.*; -- Trae todo, sólo traer el o los paquestes necesarios

// Compilar desde la carpeta de más afuera
// En los métodos no colocar cartelitos, sino la forma en que quiero que tenga el Object de toString
// Para sobreescribir con 'override', tiene que tener el mismo perfil la función a la cual se sobreescribirá
// Programar a la defensiva realizando las validaciones necesarias en los métodos y programas.

/**
 * Clase Nodo simple con dos campos.
 * Clases: Con mayúscula
 * Paquetes: Con minúscula
 * variables con dos o más palabras: camelCase
 * Clases con dos o más palabras: PascalCase
 */
public class Nodo {
    // Constructores - No se puede tener los mismos contructores con la misma cantidad de argumentos
    public Nodo() {
        valor = 0;
        siguiente = null;
    }

    public Nodo(int valor, Nodo siguiente) {
        this.valor = valor;
        this.siguiente = siguiente;
    }
    
    // Atributos
    // Visibilidad private, public, protected, default (no poner nada) -> Depende cómo lo ven las otras clases, la clase en sí misma siempre la ve
    private int valor; // Inicializa con el valor por defecto, en este caso 0
    private Nodo siguiente; // null

    /* 
     * Métodos:
     * visibilidad
     * topo de retorno
     * nombre del método
     * parámetros
     */

     /*
      * Obtiene el valor asociado a este nodo.
      */
     public int getValor() {
        return valor;
     }

     public void setValor(int valor) {
        this.valor = valor;
     }

     /*
      * Obtine el nodo siguiente a este nodo, puede ser {@code null}.
      * @return el nodo siguiente a este modo.
      */
     public Nodo getSiguiente() {
        return siguiente;
     }

     public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
     }
}