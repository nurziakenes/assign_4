import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Graph:
        //   A --4-- B
        //   |  \    |
        //   2   7   1
        //   |    \  |
        //   C --3-- D --5-- E

        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);

        graph.addEdge(a, b, 4);
        graph.addEdge(a, c, 2);
        graph.addEdge(a, d, 7);
        graph.addEdge(b, d, 1);
        graph.addEdge(c, d, 3);
        graph.addEdge(d, e, 5);

        // BFS
        System.out.println("=== BFS ===");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, a);
        bfs.run();
        bfs.printResult();

        System.out.println("BFS path A -> E:");
        List<Vertex<String>> bfsPath = bfs.pathTo(e);
        if (bfsPath != null) {
            for (Vertex<String> v : bfsPath) System.out.print(v.getData() + " ");
        }
        System.out.println("\n");

        // Dijkstra
        System.out.println("=== Dijkstra ===");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, a);
        dijkstra.run();
        dijkstra.printResult();

        System.out.println("\nDijkstra path A -> E:");
        List<Vertex<String>> dijkstraPath = dijkstra.pathTo(e);
        if (dijkstraPath != null) {
            for (Vertex<String> v : dijkstraPath) System.out.print(v.getData() + " ");
            System.out.printf("%nTotal distance: %.2f%n", dijkstra.distanceTo(e));
        }
    }
}