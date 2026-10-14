package grafos.algoritmos;

import grafos.Edge;
import grafos.representaciones.AdjacencyListGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFord {
    private final Map<String, Double> shortestPaths = new HashMap<>();

    public BellmanFord(AdjacencyListGraph adjacencyList, String rootNode) {
        findShortestPath(rootNode, adjacencyList);
    }

    private void findShortestPath(String rootNodeKey, AdjacencyListGraph adjacencyList) {
        shortestPaths.put(rootNodeKey, 0.0);

        int nodeCount = adjacencyList.getNodeCount();
        List<Edge> allEdges = adjacencyList.getAllEdges();

        // Recorrer todas las aristas |V| - 1 veces
        for (int i = 0; i < nodeCount - 1; i++) {
            boolean updated = false;

            for (Edge edge : allEdges) {
                if (edge.getStart() == null) continue;

                String u = edge.getStart().getKey();
                String v = edge.getEnd().getKey();
                double weight = edge.getWeight();

                if (shortestPaths.containsKey(u)) {
                    double currentDistU = shortestPaths.get(u);
                    if (!shortestPaths.containsKey(v) || currentDistU + weight < shortestPaths.get(v)) {
                        shortestPaths.put(v, currentDistU + weight);
                        updated = true;
                    }
                }
            }

            // Optimización: si una iteración completa no actualizó nada, se acaba
            if (!updated) {
                break;
            }
        }

        // Verificación de ciclos de peso negativo (|V|-ésima iteración)
        for (Edge edge : allEdges) {
            if (edge.getStart() == null) continue;

            String u = edge.getStart().getKey();
            String v = edge.getEnd().getKey();
            double weight = edge.getWeight();

            if (shortestPaths.containsKey(u)) {
                double currentDistU = shortestPaths.get(u);
                if (!shortestPaths.containsKey(v) || currentDistU + weight < shortestPaths.get(v)) {
                    return;
                }
            }
        }
    }

    public Map<String, Double> getShortestPaths() {
        return shortestPaths;
    }
}
