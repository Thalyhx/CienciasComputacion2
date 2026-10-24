package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Edge;

public class MSTResult {
    public static final double WEIGHT_TOLERANCE = 1e-9;

    private final List<Edge> edges;
    private final double totalWeight;
    private final int vertexCount;

    public MSTResult(List<Edge> edges, int vertexCount) {
        this.edges = Collections.unmodifiableList(new ArrayList<>(edges));
        this.vertexCount = vertexCount;

        double sum = 0;
        for (Edge edge : edges) {
            sum += edge.getWeight();
        }
        this.totalWeight = sum;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean hasExpectedEdgeCount() {
        return edges.size() == vertexCount - 1;
    }

    // Comparación de pesos:
    public boolean sameTotalWeightAs(MSTResult other) {
        double scale = Math.max(1.0, Math.max(Math.abs(totalWeight), Math.abs(other.totalWeight)));
        return Math.abs(totalWeight - other.totalWeight) <= WEIGHT_TOLERANCE * scale;
    }

    public String describe() {
        StringBuilder text = new StringBuilder();
        text.append("  Aristas del MST: ").append(edges).append(System.lineSeparator());
        text.append("  Peso total: ").append(Edge.formatWeight(totalWeight)).append(System.lineSeparator());
        text.append("  Verificación V-1 aristas: ").append(edges.size()).append(" de ").append(vertexCount - 1).append(hasExpectedEdgeCount() ? " (correcto)" : " (INCORRECTO)");
        return text.toString();
    }
}