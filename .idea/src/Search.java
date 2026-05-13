public abstract class Search<V> {
    protected Vertex<V> source;

    public Search(Vertex<V> source) {
        this.source = source;
    }

    public abstract Iterable<Vertex<V>> pathTo(Vertex<V> destination);
}