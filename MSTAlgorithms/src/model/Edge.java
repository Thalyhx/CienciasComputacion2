package model;

// Arista con peso
public final class Edge implements Comparable<Edge> {
    private final String source;
    private final String target;
    private final double weight;

    public Edge(String source, String target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    public String getOpposite(String vertex) {
        if (vertex.equals(source)) {
            return target;
        }
        if (vertex.equals(target)) {
            return source;
        }
        throw new IllegalArgumentException("El vértice " + vertex + " no pertenece a la arista " + this);
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    public static String formatWeight(double weight) {
        if (weight == Math.rint(weight) && Math.abs(weight) < 1e15) {
            return String.valueOf((long) weight);
        }
        return String.valueOf(weight);
    }

    @Override
    public String toString() {
        return source + "-" + target + "(" + formatWeight(weight) + ")";
    }
}