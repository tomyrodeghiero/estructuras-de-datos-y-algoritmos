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
        Vertex source = new Vertex(startId);
        Map<Vertex, Integer> dist = new HashMap<>();
        Map<Vertex, Vertex> prev = new HashMap<>();
        Map<Vertex, Integer> numNodesInPath = new HashMap<>();

        for (Vertex v : adjList.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
            numNodesInPath.put(v, Integer.MAX_VALUE);
        }

        dist.put(source, 0);
        numNodesInPath.put(source, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        queue.addAll(adjList.keySet());

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            if (adjList.containsKey(u)) {
                for (Edge edge : adjList.get(u)) {
                    Vertex y = edge.target;
                    int weight = edge.weight;
                    int newDist = dist.get(u) + weight;
                    int newPathSize = numNodesInPath.get(u) + 1;

                    if (newDist < dist.get(y) || (newDist == dist.get(y) && newPathSize < numNodesInPath.get(y))) {
                        dist.put(y, newDist);
                        prev.put(y, u);
                        numNodesInPath.put(y, newPathSize);
                        queue.remove(y);
                        queue.add(y);
                    }
                }
            }
        }

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
            System.out.println("Distance from 1 to " + entry.getKey().id + " is " + entry.getValue());
        }
    }
}
