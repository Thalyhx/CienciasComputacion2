package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import metrics.OperationCounter;
import model.Edge;
import model.Graph;

// Prim:
public class PrimAlgorithm implements MSTAlgorithm {

    public static final String NAME = "Prim";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MSTResult run(Graph graph, OperationCounter operationCounter) {
        int vertexCount = graph.vertexCount();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> heap = new PriorityQueue<>(operationCounter.countingComparator());
        List<Edge> mstEdges = new ArrayList<>();

        String start = graph.getVertices().iterator().next();
        visited.add(start);

        addCandidateEdges(graph, start, visited, heap, operationCounter);

        while (!heap.isEmpty() && mstEdges.size() < vertexCount - 1) {
            Edge cheapest = heap.poll();
            operationCounter.countHeapExtraction();

            boolean sourceInTree = visited.contains(cheapest.getSource());
            boolean targetInTree = visited.contains(cheapest.getTarget());

            if (sourceInTree && targetInTree) {
                continue;
            }

            String newVertex = sourceInTree ? cheapest.getTarget() : cheapest.getSource();
            visited.add(newVertex);
            mstEdges.add(cheapest);

            addCandidateEdges(graph, newVertex, visited, heap, operationCounter);
        }

        return new MSTResult(mstEdges, vertexCount);
    }

    private void addCandidateEdges(Graph graph, String vertex, Set<String> visited, PriorityQueue<Edge> heap, OperationCounter operationCounter) {
        for (Edge edge : graph.getIncidentEdges(vertex)) {
            if (!visited.contains(edge.getOpposite(vertex))) {
                heap.add(edge);
                operationCounter.countHeapInsertion();
            }
        }
        operationCounter.trackAuxiliaryElements(heap.size() + visited.size());
    }
}