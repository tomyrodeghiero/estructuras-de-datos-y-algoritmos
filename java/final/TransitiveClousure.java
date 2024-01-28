public class TransitiveClousure {
    private boolean[][] reachabilityMatrix;

    public TransitiveClousure(int numVertices) {
        reachabilityMatrix = new boolean[numVertices][numVertices];
    }

    public void addEdge(int source, int target) {
        reachabilityMatrix[source][target] = true;
    }

    public void computeTransitiveClosure() {
        int size = reachabilityMatrix.length;
        // Inicializar la matriz de alcanzabilidad con la información de las aristas
        // directas.
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // Actualizar la matriz de alcanzabilidad si existe un camino entre i y j a
                    // través de k.
                    reachabilityMatrix[i][j] = reachabilityMatrix[i][j]
                            || (reachabilityMatrix[i][k] && reachabilityMatrix[k][j]);
                }
            }
        }
    }

    public boolean canReach(int source, int target) {
        return reachabilityMatrix[source][target];
    }

    public static void main(String[] args) {
        TransitiveClousure graph = new TransitiveClousure(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.computeTransitiveClosure();

        System.out.println("Puede llegar de 0 a 3: " + graph.canReach(0, 3));
        System.out.println("Puede llegar de 3 a 0: " + graph.canReach(3, 0));
    }
}
