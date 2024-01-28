import java.util.*;

public class DijkstraAlgorithm {

    // Clase Graph para gestionar el grafo de manera más efectiva
    static class Graph {
        private final List<List<Edge>> graph;

        public Graph(int n) {
            graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
        }

        // Añade una arista al grafo
        public void addEdge(int source, int dest, int weight) {
            graph.get(source).add(new Edge(dest, weight));
        }

        // Obtiene la representación del grafo
        public List<List<Edge>> getGraph() {
            return graph;
        }
    }

    private static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Vertex implements Comparable<Vertex> {
        int id, dist;

        Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    // Algoritmo de Dijkstra para encontrar el camino más corto desde un nodo fuente
    // a todos los demás nodos
    public static void dijkstra(List<List<Edge>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Integer[] prev = new Integer[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(src, 0));

        while (!pq.isEmpty()) {
            Vertex vertex = pq.poll();
            int u = vertex.id;

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                    pq.add(new Vertex(v, dist[v]));
                }
            }
        }

        printShortestPaths(src, dist, prev);
    }

    // Imprime los caminos más cortos desde el nodo fuente a todos los demás nodos
    private static void printShortestPaths(int src, int[] dist, Integer[] prev) {
        int n = dist.length;
        System.out.println("Caminos más cortos desde " + src + ":");
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(i + " -> Sin camino");
            } else {
                System.out.println(i + " -> Distancia: " + dist[i] + ", Camino: " + getPath(i, prev));
            }
        }
    }

    // Reconstruye y devuelve el camino más corto desde el nodo fuente hasta un nodo
    // objetivo
    private static String getPath(int target, Integer[] prev) {
        List<Integer> path = new ArrayList<>();
        for (Integer at = target; at != null; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5); // Creamos un grafo con 5 nodos (0 a 4)
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 2, 9);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 0, 7);

        dijkstra(graph.getGraph(), 0); // 0 es el nodo origen
    }

}
