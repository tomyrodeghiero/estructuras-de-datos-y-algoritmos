import java.util.*;

public class GraphConnectivityChecker {
    private Map<Integer, Vertex> vertices;

    public GraphConnectivityChecker() {
        this.vertices = new HashMap<>();
    }

    private class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjacent;

        Vertex(int id) {
            this.id = id;
            this.visited = false;
            this.adjacent = new ArrayList<>();
        }

        void mark() {
            this.visited = true;
        }

        void addNeighbor(Vertex neighbor) {
            this.adjacent.add(neighbor);
        }
    }

    public void addEdge(int v1, int v2) {
        vertices.putIfAbsent(v1, new Vertex(v1));
        vertices.putIfAbsent(v2, new Vertex(v2));
        vertices.get(v1).addNeighbor(vertices.get(v2));
        vertices.get(v2).addNeighbor(vertices.get(v1));
    }

    public void dfs(Vertex v) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(v);
        v.mark();

        while (!stack.isEmpty()) {
            Vertex u = stack.peek();
            Vertex w = getUnvisitedAdj(u);
            if (w != null) {
                w.mark();
                stack.push(w);
            } else {
                stack.pop();
            }
        }
    }

    private Vertex getUnvisitedAdj(Vertex u) {
        for (Vertex v : u.adjacent) {
            if (!v.visited) {
                return v;
            }
        }
        return null;
    }

    public boolean hasDisconnectedNode(int startVertexId) {
        if (vertices.isEmpty()) {
            return false; // Un grafo vacío no tiene nodos disconexos
        }

        Vertex startVertex = vertices.get(startVertexId);
        if (startVertex == null) {
            throw new IllegalArgumentException("El vértice de inicio no existe en el grafo.");
        }

        dfs(startVertex);

        for (Vertex v : vertices.values()) {
            if (!v.visited) {
                return true; // Se encontró un nodo disconexo
            }
        }
        return false; // No hay nodos disconexos
    }

    public boolean hasUnreachableParts() {
        if (vertices.isEmpty()) {
            return false;
        }

        Vertex startVertex = vertices.values().iterator().next();
        dfs(startVertex);

        for (Vertex v : vertices.values()) {
            if (!v.visited) {
                return true; // Encontramos un vértice inalcanzable
            }
        }
        return false; // Todos los vértices son alcanzables
    }

    public static void main(String[] args) {
        GraphConnectivityChecker checker = new GraphConnectivityChecker();
        checker.addEdge(1, 2);
        checker.addEdge(2, 3);
        checker.addEdge(3, 4);
        checker.addEdge(10, 5);

        boolean hasDisconnectedNode = checker.hasDisconnectedNode(1);
        System.out.println("El grafo tiene nodos disconexos: " + hasDisconnectedNode);
    }
}
