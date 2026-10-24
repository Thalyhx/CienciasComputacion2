package analysis;

import java.util.Locale;

public class GraphProfile {

    private final int vertexCount;
    private final int edgeCount;
    private final double density;
    private final boolean dense;

    public GraphProfile(int vertexCount, int edgeCount, double density, boolean dense) {
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.density = density;
        this.dense = dense;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public double getDensity() {
        return density;
    }

    public boolean isDense() {
        return dense;
    }

    public String getTypeLabel() {
        return dense ? "denso" : "disperso";
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "V = %d, E = %d, densidad = %.3f -> grafo %s",
                vertexCount, edgeCount, density, getTypeLabel());
    }
}