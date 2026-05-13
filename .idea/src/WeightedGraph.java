import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        map.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        map.putIfAbsent(source, new ArrayList<>());
        map.putIfAbsent(destination, new ArrayList<>());

        source.addAdjacentVertex(destination, weight);
        destination.addAdjacentVertex(source, weight);

        map.get(source).add(destination);
        map.get(destination).add(source);
    }

    public Map<Vertex<V>, List<Vertex<V>>> getMap() {
        return map;
    }

    public Iterable<Vertex<V>> getVertices() {
        return map.keySet();
    }
}