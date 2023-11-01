package articulo;

public class Articulo {
    String nombre;
    int cantidad;

    public Articulo(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String toString() {
        return nombre + " (Cantidad: " + cantidad + ")";
    }
}
