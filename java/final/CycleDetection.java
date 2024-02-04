import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjList;

    public Graph(int vertices) {
        adjList = new HashMap<>();
        for (int i = 1; i <= vertices; i++) {
            adjList.put(i, new LinkedList<>());
        }
    }

    public void addEdge(int source, int dest) {
        if (!adjList.containsKey(source)) {
            adjList.put(source, new LinkedList<>());
        }
        if (!adjList.containsKey(dest)) {
            adjList.put(dest, new LinkedList<>());
        }
        adjList.get(source).add(dest);
    }

    public List<Integer> adjacentVertices(int v) {
        return adjList.get(v);
    }
}

public class CycleDetection {

    public static boolean detectarCicloDFS(Graph g, int start) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> path = new HashMap<>();

        stack.push(start);
        path.put(start, null); // El nodo de inicio no tiene predecesor

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited.contains(current)) {
                // Si volvemos a visitar el nodo de inicio, hay un ciclo
                if (current == start) {
                    return true;
                }
                continue;
            }

            visited.add(current);

            for (int adj : g.adjacentVertices(current)) {
                if (adj == start) {
                    // Si encontramos el nodo de inicio mientras recorremos, hay un ciclo
                    return true;
                }

                // Si el nodo adyacente no ha sido visitado o es el nodo de inicio
                if (!visited.contains(adj)) {
                    stack.push(adj);
                    path.put(adj, current); // Guardar el predecesor
                }
            }
        }

        return false; // No se encontró un ciclo que incluya al nodo de inicio
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(3, 4);
        g.addEdge(4, 3);
        g.addEdge(5, 6);
        g.addEdge(6, 1);

        boolean hasCycle = detectarCicloDFS(g, 3);
        System.out.println("¿Hay un ciclo que incluya al nodo 2? " + hasCycle);
    }
}
