package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Grafo representado con lista de adyacencia.
public class Graph {
    private final Map<String, List<Edge>> adjacency = new LinkedHashMap<>();
    private final List<Edge> edges = new ArrayList<>();
    
    public void addEdge(Edge edge) {
        edges.add(edge);
        adjacency.computeIfAbsent(edge.getSource(), vertex -> new ArrayList<>()).add(edge);
        if (!edge.getSource().equals(edge.getTarget())) {
            adjacency.computeIfAbsent(edge.getTarget(), vertex -> new ArrayList<>()).add(edge);
        }
    }

    public Set<String> getVertices() {
        return Collections.unmodifiableSet(adjacency.keySet());
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public List<Edge> getIncidentEdges(String vertex) {
        List<Edge> incident = adjacency.get(vertex);
        if (incident == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(incident);
    }

    public int vertexCount() {
        return adjacency.size();
    }

    public int edgeCount() {
        return edges.size();
    }
}