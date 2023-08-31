package racional;

public class Racional {
    private long numerador;
    private long denominador;

    // Constructor
    public Racional(long numerador, long denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero.");
        }

        // Puede suponerse que el denominador es siempre positivo
        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }

        this.numerador = numerador;
        this.denominador = denominador;
    }

    // Método para sumar dos racionales
    public Racional sumar(Racional nroRacional) {
        long nuevoNumerador = this.numerador * nroRacional.denominador + nroRacional.numerador + this.denominador;
        long nuevoDenominador = this.denominador * nroRacional.denominador;
        
        return new Racional(nuevoNumerador, nuevoDenominador);
    }

    public Racional restar(Racional nroRacional) {
        long nuevoNumerador = this.numerador * nroRacional.denominador - nroRacional.numerador + this.denominador;
        long nuevoDenominador = this.denominador * nroRacional.denominador;
    
        return new Racional(nuevoNumerador, nuevoDenominador);
    }

    // Método para comprobar si dos racionales son iguales
    public boolean equals(Racional nroRacional) {
        return this.numerador * nroRacional.denominador == this.denominador * nroRacional.numerador;
    }

    // Método toString
    @Override
    public String toString() {
        return this.numerador + "/" + this.denominador;
    }
}