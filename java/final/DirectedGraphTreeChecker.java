import java.util.*;

public class DirectedGraphTreeChecker {
    private Map<Integer, List<Integer>> adjList; // Lista de adyacencia para representar el grafo
    private Set<Integer> visited; // Conjunto para llevar registro de nodos visitados
    private Set<Integer> currentPath; // Conjunto para rastrear el camino actual en DFS
    private boolean hasCycle; // Bandera para indicar si se ha encontrado un ciclo

    public DirectedGraphTreeChecker(int numVertices) {
        adjList = new HashMap<>();
        visited = new HashSet<>();
        currentPath = new HashSet<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
        hasCycle = false;
    }

    public void addEdge(int source, int target) {
        adjList.get(source).add(target);
    }

    // Método DFS para verificar si hay un ciclo
    private void dfs(int v) {
        if (currentPath.contains(v)) {
            hasCycle = true;
            return;
        }
        if (visited.contains(v)) {
            return;
        }

        visited.add(v);
        currentPath.add(v);

        for (int adj : adjList.get(v)) {
            dfs(adj);
            if (hasCycle)
                return; // Si se encuentra un ciclo, terminar
        }

        currentPath.remove(v); // Remover el nodo del camino actual al retroceder
    }

    // Método para determinar si el grafo es un árbol
    public boolean isTree() {
        // Se asume que el nodo 0 es la raíz del árbol
        dfs(0); // Iniciar DFS desde el nodo 0

        // El grafo es un árbol si no tiene ciclos y todos los nodos son accesibles
        // desde la raíz
        return !hasCycle && visited.size() == adjList.size();
    }

    public static void main(String[] args) {
        DirectedGraphTreeChecker graph = new DirectedGraphTreeChecker(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        // graph.addEdge(3, 0); // Descomentar para introducir un ciclo

        System.out.println("El grafo es un árbol: " + graph.isTree());
    }
}
