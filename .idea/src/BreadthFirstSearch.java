import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Boolean> visited = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> parent = new HashMap<>();
    private List<Vertex<V>> order = new ArrayList<>();

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(graph, source);
    }

    @Override
    public void run() {
        Queue<Vertex<V>> queue = new LinkedList<>();
        visited.put(source, true);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            order.add(current);

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.getOrDefault(neighbor, false)) {
                    visited.put(neighbor, true);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    public List<Vertex<V>> pathTo(Vertex<V> destination) {
        if (!visited.getOrDefault(destination, false)) return null;

        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> v = destination; v != null; v = parent.get(v)) {
            path.add(0, v);
        }
        return path;
    }

    @Override
    public void printResult() {
        System.out.println("BFS traversal order from " + source + ":");
        for (Vertex<V> v : order) {
            System.out.print(v.getData() + " ");
        }
        System.out.println();
    }
}