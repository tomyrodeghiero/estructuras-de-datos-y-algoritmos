package vectores.vector;

public class Vector {
    private double[] elementos;

    public Vector(int n) {
        this.elementos = new double[n];
    }

    public Vector(double[] elementos) {
        this.elementos = elementos;
    }

    public double getElemento(int i) {
        return elementos[i];
    }

    public void setElemento(int i, double valor) {
        elementos[i] = valor;
    }

    public int elementos() {
        return elementos.length;
    }

    // Multiplicación por un escalar
    public Vector escalar(double k) {
        double[] resultado = new double[this.elementos.length];

        for (int i = 0; i < elementos.length; i++) {
            resultado[i] = k * elementos[i];
        }

        return new Vector(resultado);
    }

    // Suma de vectores
    public Vector suma(Vector otroVector) {
        if (elementos.length != otroVector.elementos()) {
            throw new IllegalArgumentException("Los vectores deben tener el mismo tamaño");
        }

        double[] resultado = new double[elementos.length];
        for (int i = 0; i < elementos.length; i++) {
            resultado[i] = elementos[i] + otroVector.getElemento(i);
        }

        return new Vector(resultado);
    }

    // Multiplicación (producto punto) de vectores del mismo tamaño
    public double productoPunto(Vector otroVector) {
        if (elementos.length != otroVector.elementos()) {
            throw new IllegalArgumentException("Los vectores deben tener el mismo tamaño");
        }

        double suma = 0;

        for (int i = 0; i < elementos.length; i++) {
            suma += elementos[i] * otroVector.getElemento(i);
        }

        return suma;
    }

    // Comparar por igualdad
    public boolean equals(Vector otro) {
        if (elementos.length != otro.elementos()) {
            return false;
        }

        for (int i = 0; i < elementos.length; i++) {
            if (elementos[i] != otro.getElemento(i)) {
                return false;
            }
        }

        return true;
    }

    // Representación en cadena de texto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elementos.length; i++) {
            sb.append(String.format("%.0f", elementos[i])); // Redondear a 0 decimales
            if (i < elementos.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
