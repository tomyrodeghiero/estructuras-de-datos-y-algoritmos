import java.util.Arrays;

public class GraphCycleDetector {

    static class Graph {
        private int[][] adjacencyMatrix;

        public Graph(int numberOfNodes) {
            // Inicializamos la matriz de adyacencia con valores altos para representar la
            // ausencia de aristas
            adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
            for (int[] row : adjacencyMatrix) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            // Colocamos 0 en la diagonal principal ya que la distancia de un nodo a sí
            // mismo es 0
            for (int i = 0; i < numberOfNodes; i++) {
                adjacencyMatrix[i][i] = 0;
            }
        }

        public void addEdge(int from, int to, int weight) {
            // Añadir un borde con el peso especificado
            adjacencyMatrix[from][to] = weight;
        }

        public int[][] getAdjacencyMatrix() {
            return adjacencyMatrix;
        }
    }

    // Método estático de detección de ciclos negativos para trabajar con el Graph
    public static boolean floydWarshallDetectNegativeCycles(int[][] graph, int[][] d, int[][] path) {
        int n = graph.length; // cantidad de nodos

        // se inicializa dist y path
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MAX_VALUE && i != j) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        // se considera cada camino pasando por un posible k
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Si hay un nuevo mínimo se cambia el valor y se actualiza el camino
                    if (d[i][k] != Integer.MAX_VALUE && d[k][j] != Integer.MAX_VALUE && d[i][k] + d[k][j] < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }

        // Se busca un ciclo con costo negativo
        for (int i = 0; i < n; i++) {
            if (d[i][i] < 0) {
                return true; // Se encontró un ciclo negativo
            }
        }

        return false; // No se encontró ningún ciclo negativo
    }

    public static void main(String[] args) {
        Graph g = new Graph(3); // Un grafo con 3 nodos
        g.addEdge(0, 1, 4);
        g.addEdge(1, 0, -10);
        g.addEdge(1, 2, 4);

        int[][] graph = g.getAdjacencyMatrix();

        int n = graph.length;
        int[][] d = new int[n][n]; // La matriz de distancias
        int[][] path = new int[n][n]; // La matriz de caminos

        boolean hasNegativeCycle = floydWarshallDetectNegativeCycles(graph, d, path);

        if (hasNegativeCycle) {
            System.out.println("El grafo tiene al menos un ciclo con costo negativo.");
        } else {
            System.out.println("El grafo no tiene ciclos con costo negativo.");
        }
    }
}