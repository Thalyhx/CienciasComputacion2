package algorithms;

import java.util.HashMap;
import java.util.Map;

import metrics.OperationCounter;

// Estrucutra de datos que usa Kruskal's alghoritm para la detección de posibles ciclos.
public class DisjointSet {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();
    private final OperationCounter operationCounter;

    public DisjointSet(OperationCounter operationCounter) {
        this.operationCounter = operationCounter;
    }

    // Por cada vertice se crea un 'grupo'
    public void makeSet(String vertex) {
        parent.put(vertex, vertex);
        rank.put(vertex, 0);
    }

    // Encontrar la raíz de cada conjunto
    public String find(String vertex) {
        operationCounter.countFind();
        return findRoot(vertex);
    }

    private String findRoot(String vertex) {
        String parentOfVertex = parent.get(vertex);
        if (!parentOfVertex.equals(vertex)) {
            String root = findRoot(parentOfVertex);
            parent.put(vertex, root);
            return root;
        }
        return vertex;
    }

    // Verificación de unión de grupos
    public boolean union(String a, String b) {
        String rootA = findRoot(a);
        String rootB = findRoot(b);

        if (rootA.equals(rootB)) {
            return false;
        }

        int rankA = rank.get(rootA);
        int rankB = rank.get(rootB);
        if (rankA < rankB) {
            parent.put(rootA, rootB);
        } else if (rankA > rankB) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rankA + 1);
        }

        operationCounter.countUnion();
        return true;
    }
}