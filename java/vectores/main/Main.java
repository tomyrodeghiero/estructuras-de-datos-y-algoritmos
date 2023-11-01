package vectores.main;

import vectores.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(new double[] { 0, 1, 2, 3, 4 });
        Vector v2 = new Vector(new double[] { 5, 6, 7, 8, 9, });

        System.out.println("Vector v1: " + v1);
        System.out.println("Vector v2: " + v2);

        Vector v3 = v1.escalar(2);
        System.out.println("v1 * 2: " + v3);

        Vector v4 = v1.suma(v2);
        System.out.println("v1 + v2: " + v4);

        double productoPunto = v1.productoPunto(v2);
        System.out.println("v1 . v2: " + productoPunto);

        boolean iguales = v1.equals(new Vector(new double[] { 0, 1, 2, 3, 4, }));
        System.out.println("v1 == [0, 1, 2, 3, 4]: " + iguales);
    }
}
