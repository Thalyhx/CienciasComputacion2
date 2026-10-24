package algorithms;

import java.util.ArrayList;
import java.util.List;

import metrics.OperationCounter;
import model.Edge;
import model.Graph;

// Kruskal
public class KruskalAlgorithm implements MSTAlgorithm {

    public static final String NAME = "Kruskal";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MSTResult run(Graph graph, OperationCounter operationCounter) {
        int vertexCount = graph.vertexCount();

        DisjointSet sets = new DisjointSet(operationCounter);
        for (String vertex : graph.getVertices()) {
            sets.makeSet(vertex);
        }

        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        sortedEdges.sort(operationCounter.countingComparator());
        operationCounter.trackAuxiliaryElements(sortedEdges.size() + 2L * vertexCount);

        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : sortedEdges) {
            boolean merged = sets.union(edge.getSource(), edge.getTarget());

            if (merged) {
                mstEdges.add(edge);
            }

            if (mstEdges.size() == vertexCount - 1) {
                break;
            }
        }

        return new MSTResult(mstEdges, vertexCount);
    }
}