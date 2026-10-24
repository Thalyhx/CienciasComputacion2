package analysis;

import model.Graph;

// Calcula V, E y la densidad-
public class GraphAnalyzer {

    public static final double DENSITY_THRESHOLD = 0.5;

    public GraphProfile analyze(Graph graph) {
        int vertexCount = graph.vertexCount();
        int edgeCount = graph.edgeCount();

        double maxEdges = vertexCount * (vertexCount - 1) / 2.0;
        // Calculo de densidad: aristas que tiene / aristas máximas posibles.
        double density = maxEdges == 0 ? 0 : edgeCount / maxEdges;

        return new GraphProfile(vertexCount, edgeCount, density, density >= DENSITY_THRESHOLD);
    }
}