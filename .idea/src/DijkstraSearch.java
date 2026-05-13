import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distances = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> parent = new HashMap<>();
    private Set<Vertex<V>> settled = new HashSet<>();

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(graph, source);
    }

    @Override
    public void run() {
        for (Vertex<V> v : graph.getVertices()) {
            distances.put(v, Double.MAX_VALUE);
        }
        distances.put(source, 0.0);

        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(
                Comparator.comparingDouble(v -> distances.getOrDefault(v, Double.MAX_VALUE))
        );
        pq.add(source);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            if (settled.contains(current)) continue;
            settled.add(current);

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();

                if (!settled.contains(neighbor)) {
                    double newDist = distances.get(current) + weight;
                    if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                        distances.put(neighbor, newDist);
                        parent.put(neighbor, current);
                        pq.add(neighbor);
                    }
                }
            }
        }
    }

    public List<Vertex<V>> pathTo(Vertex<V> destination) {
        if (!distances.containsKey(destination) ||
                distances.get(destination) == Double.MAX_VALUE) return null;

        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> v = destination; v != null; v = parent.get(v)) {
            path.add(0, v);
        }
        return path;
    }

    public double distanceTo(Vertex<V> destination) {
        return distances.getOrDefault(destination, Double.MAX_VALUE);
    }

    @Override
    public void printResult() {
        System.out.println("Dijkstra shortest distances from " + source + ":");
        for (Map.Entry<Vertex<V>, Double> e : distances.entrySet()) {
            System.out.printf("  to %s : %.2f%n", e.getKey().getData(), e.getValue());
        }
    }
}