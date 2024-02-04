import java.util.HashSet;
import java.util.Set;

public class RelationsAlgorithms {

    // Método para calcular la composición de R y S
    public static Set<Pair> compose(Set<Pair> R, Set<Pair> S) {
        Set<Pair> composition = new HashSet<>();
        for (Pair r : R) {
            for (Pair s : S) {
                if (r.getSecond().equals(s.getFirst())) {
                    composition.add(new Pair(r.getFirst(), s.getSecond()));
                }
            }
        }
        return composition;
    }

    // Método para calcular la clausura transitiva de R y S
    public static Set<Pair> transitiveClosure(Set<Pair> R) {
        Set<Pair> closure = new HashSet<>(R);
        boolean changed = true;
        while (changed) {
            changed = false;
            Set<Pair> newPairs = new HashSet<>();
            for (Pair p1 : closure) {
                for (Pair p2 : closure) {
                    if (p1.getSecond().equals(p2.getFirst())) {
                        Pair newPair = new Pair(p1.getFirst(), p2.getSecond());
                        if (!closure.contains(newPair)) {
                            newPairs.add(newPair);
                            changed = true;
                        }
                    }
                }
            }
            closure.addAll(newPairs);
        }
        return closure;
    }

    // Clase auxiliar para representar un par ordenado
    static class Pair {
        private Integer first;
        private Integer second;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public Integer getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Pair pair = (Pair) o;

            if (!first.equals(pair.first))
                return false;
            return second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            int result = first.hashCode();
            result = 31 * result + second.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    // Método main para probar los algoritmos
    public static void main(String[] args) {
        Set<Pair> R = new HashSet<>();
        Set<Pair> S = new HashSet<>();

        // Añadir pares a R y S
        R.add(new Pair(1, 2));
        R.add(new Pair(2, 3));
        S.add(new Pair(2, 5));
        S.add(new Pair(3, 4));

        // Calcular la composición de R y S
        Set<Pair> composition = compose(R, S);
        System.out.println("Composición de R y S: " + composition);

        // Calcular la clausura transitiva de R
        Set<Pair> closureR = transitiveClosure(R);
        System.out.println("Clausura transitiva de R: " + closureR);

        // Calcular la clausura transitiva de S
        Set<Pair> closureS = transitiveClosure(S);
        System.out.println("Clausura transitiva de S: " + closureS);
    }
}
