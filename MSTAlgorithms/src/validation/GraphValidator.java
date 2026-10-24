package validation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Edge;
import model.Graph;

// Revisa que el grafo sirva para calcular un MST: conexo, sin bucles y sin aristas repetidas.
public class GraphValidator {

    public List<String> validate(Graph graph) {
        List<String> problems = new ArrayList<>();

        if (graph.edgeCount() == 0) {
            problems.add("El grafo no tiene aristas.");
            return problems;
        }

        checkLoopsAndDuplicates(graph, problems);
        checkConnected(graph, problems);
        return problems;
    }

    private void checkLoopsAndDuplicates(Graph graph, List<String> problems) {
        Set<List<String>> seenPairs = new HashSet<>();

        for (Edge edge : graph.getEdges()) {
            if (edge.getSource().equals(edge.getTarget())) {
                problems.add("Hay un bucle en el vértice " + edge.getSource() + ".");
                continue;
            }

            // Se ordena el par para que A-B y B-A den la misma clave.
            String first = edge.getSource();
            String second = edge.getTarget();
            if (first.compareTo(second) > 0) {
                first = edge.getTarget();
                second = edge.getSource();
            }

            if (!seenPairs.add(Arrays.asList(first, second))) {
                problems.add("La arista " + first + "-" + second + " está repetida.");
            }
        }
    }

    private void checkConnected(Graph graph, List<String> problems) {
        String start = graph.getVertices().iterator().next();
        Set<String> reached = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();

        reached.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (Edge edge : graph.getIncidentEdges(current)) {
                String neighbor = edge.getOpposite(current);
                if (reached.add(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        if (reached.size() != graph.vertexCount()) {
            Set<String> unreachable = new HashSet<>(graph.getVertices());
            unreachable.removeAll(reached);
            problems.add("El grafo no es conexo: desde " + start + " no se llega a " + unreachable + ".");
        }
    }
}