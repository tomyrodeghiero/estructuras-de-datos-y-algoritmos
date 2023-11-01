package colecciones.pila;

public interface Pila {
    public abstract boolean esVacia();

    public abstract void vaciar();

    public abstract int longitud();

    public abstract void apilar(Object item) throws ExcepcionPila;

    public abstract void desapilar() throws ExcepcionPila;

    public abstract Object tope() throws ExcepcionPila;
}
