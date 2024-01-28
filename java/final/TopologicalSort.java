import java.util.*;

public class TopologicalSort {
    private int numVertices;
    private ArrayList<ArrayList<Integer>> adj;
    private boolean[] visited;
    private Stack<Integer> stack;

    // Constructor
    public TopologicalSort(int numVertices) {
        this.numVertices = numVertices;
        adj = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[numVertices];
        stack = new Stack<>();
    }

    // Función para añadir una arista al grafo
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    // Una función recursiva utilizada por topologicalSort
    private void dfs(int v) {
        // Marcar el nodo actual como visitado
        visited[v] = true;

        // Recorrer todos los vértices adyacentes a este vértice
        for (Integer vertex : adj.get(v)) {
            if (!visited[vertex]) {
                dfs(vertex);
            }
        }

        // Empujar el vértice actual a la pila que contiene el resultado
        stack.push(v);
    }

    // La función para hacer el ordenamiento topológico. Devuelve una lista con el
    // orden.
    public List<Integer> topologicalSort() {
        Arrays.fill(visited, false); // Reiniciar el arreglo de visitados

        // Llamar a la función recursiva para almacenar el ordenamiento topológico
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!stack.empty()) {
            sortedOrder.add(stack.pop());
        }

        return sortedOrder;
    }

    public static void main(String args[]) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        List<Integer> sortedOrder = g.topologicalSort();
        System.out.println("El siguiente es un ordenamiento topológico del grafo:");
        for (int v : sortedOrder) {
            System.out.print(v + " ");
        }
    }
}
