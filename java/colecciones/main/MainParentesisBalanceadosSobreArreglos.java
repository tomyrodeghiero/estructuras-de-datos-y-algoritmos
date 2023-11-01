package colecciones.main;

import colecciones.pila.ExcepcionPila;
import colecciones.pila.PilaSobreArreglos;

public class MainParentesisBalanceadosSobreArreglos {

    /*
     * Este es un algoritmo que toma como argumentos una secuencia de paréntesis,
     * corchetes y llaves y determine si la secuencia está balanceada. Por ejemplo,
     * el algoritmo retorna verdadero para [()][()()]() y falso para [(]).
     * (Utiliza el TAD Pila sobre Arreglos)
     */
    public static void main(String[] args) throws ExcepcionPila {
        PilaSobreArreglos pila = new PilaSobreArreglos();
        String secuencia = "[()][()()]()";

        boolean esBalanceada = true;

        try {
            for (int i = 0; i < secuencia.length(); i++) {
                char actual = secuencia.charAt(i);

                if (actual == '(' || actual == '{' || actual == '[') {
                    pila.apilar(actual);
                } else if (actual == ')' || actual == '}' || actual == ']') {
                    char tope = (char) pila.tope();
                    if (!sonPareja(tope, actual)) {
                        esBalanceada = false;
                        break;
                    } else {
                        pila.desapilar();
                    }
                }
            }
        } catch (ExcepcionPila event) {
            System.out.println("Excepción de Pila: " + event.getMessage());
            esBalanceada = false;
        }

        if (!pila.esVacia()) {
            esBalanceada = false;
        }

        if (esBalanceada) {
            System.out.println("La secuencia de paréntesis está balanceada");
        } else {
            System.out.println("La secuencia de paréntesis no está balanceada");
        }
    }

    public static boolean sonPareja(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') || (apertura == '{' && cierre == '}')
                || (apertura == '[' && cierre == ']');
    }
}
