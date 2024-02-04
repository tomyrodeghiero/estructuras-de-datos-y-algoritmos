import java.util.*;

public class ShortestPathFinderDijkstra {
    private Map<Vertex, List<Edge>> adjList;

    public ShortestPathFinderDijkstra() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(int source, int target, int weight) {
        Vertex vSource = new Vertex(source);
        Vertex vTarget = new Vertex(target);

        adjList.putIfAbsent(vSource, new ArrayList<>());
        adjList.putIfAbsent(vTarget, new ArrayList<>());

        adjList.get(vSource).add(new Edge(vTarget, weight));
    }

    public Map<Vertex, Integer> dijkstra(int startId) {
        // Crear el vértice fuente a partir del ID de inicio
        Vertex source = new Vertex(startId);

        // Mapa para almacenar la distancia mínima desde 'source' a cada vértice
        Map<Vertex, Integer> dist = new HashMap<>();

        // Mapa para rastrear el vértice anterior en el camino más corto
        Map<Vertex, Vertex> prev = new HashMap<>();

        // Mapa para almacenar el número de nodos en la ruta más corta desde 'source'
        Map<Vertex, Integer> numNodesInPath = new HashMap<>();

        // Inicializar todos los vértices con distancia infinita, previo como null, y
        // número de nodos en ruta como infinito
        for (Vertex v : adjList.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
            numNodesInPath.put(v, Integer.MAX_VALUE);
        }

        // Establecer la distancia y el número de nodos en ruta a 'source' como 0
        dist.put(source, 0);
        numNodesInPath.put(source, 0);

        // Cola de prioridad que ordena vértices por su distancia mínima
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        queue.addAll(adjList.keySet());

        // Mientras la cola no esté vacía
        while (!queue.isEmpty()) {
            // Obtener y remover el vértice con la distancia mínima de 'source'
            Vertex u = queue.poll();

            // Si el vértice tiene vecinos
            if (adjList.containsKey(u)) {
                // Iterar sobre todos los vecinos
                for (Edge edge : adjList.get(u)) {
                    Vertex y = edge.target; // El vértice objetivo de la arista
                    int weight = edge.weight; // El peso de la arista
                    int newDist = dist.get(u) + weight; // Nueva distancia potencial
                    int newPathSize = numNodesInPath.get(u) + 1; // Nuevo tamaño del camino

                    // Si la nueva distancia es menor o igual pero con menos nodos
                    if (newDist < dist.get(y) || (newDist == dist.get(y) && newPathSize < numNodesInPath.get(y))) {
                        // Actualizar la distancia, el vértice previo, y el número de nodos en ruta para
                        // 'y'
                        dist.put(y, newDist);
                        prev.put(y, u);
                        numNodesInPath.put(y, newPathSize);
                        // Actualizar la cola de prioridad
                        queue.remove(y);
                        queue.add(y);
                    }
                }
            }
        }

        // Devolver el mapa de distancias
        return dist;
    }

    private static class Vertex {
        int id;

        Vertex(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Vertex vertex = (Vertex) o;
            return id == vertex.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private static class Edge {
        Vertex target;
        int weight;

        Edge(Vertex target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        ShortestPathFinderDijkstra finder = new ShortestPathFinderDijkstra();
        finder.addEdge(1, 2, 4);
        finder.addEdge(1, 3, 2);
        finder.addEdge(2, 3, 5);
        finder.addEdge(2, 4, 10);
        finder.addEdge(3, 4, 3);

        Map<Vertex, Integer> distances = finder.dijkstra(1);
        for (Map.Entry<Vertex, Integer> entry : distances.entrySet()) {
            System.out.println("Distancia de 1 a " + entry.getKey().id + " es " + entry.getValue());
        }
    }
}
