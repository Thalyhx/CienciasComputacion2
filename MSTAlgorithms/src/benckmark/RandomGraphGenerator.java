package benckmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Edge;
import model.Graph;

// Genera grafos aleatorios conexos con un número de vértices y una densidad dados.
public class RandomGraphGenerator {

    private final Random random;

    // Con la misma semilla se generan siempre los mismos grafos (experimentos repetibles).
    public RandomGraphGenerator(long seed) {
        this.random = new Random(seed);
    }

    // vertexCount >= 2; density entre 0 y 1; los pesos son enteros entre 1 y maxWeight.
    public Graph generate(int vertexCount, double density, int maxWeight) {
        long maxEdges = (long) vertexCount * (vertexCount - 1) / 2;
        long targetEdges = Math.min(maxEdges, Math.max(vertexCount - 1, Math.round(density * maxEdges)));

        Graph graph = new Graph();
        Set<Long> usedPairs = new HashSet<>();

        // PASO 1: un árbol aleatorio que une todos los vértices, así el grafo siempre es conexo.
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            order.add(i);
        }
        Collections.shuffle(order, random);

        for (int i = 1; i < vertexCount; i++) {
            int a = order.get(i);
            int b = order.get(random.nextInt(i));
            addEdge(graph, a, b, maxWeight);
            usedPairs.add(pairKey(a, b, vertexCount));
        }

        // PASO 2: aristas extra al azar, entre los pares que todavía no están conectados.
        long extraEdges = targetEdges - (vertexCount - 1);
        if (extraEdges > 0) {
            List<Long> candidates = new ArrayList<>();
            for (int a = 0; a < vertexCount; a++) {
                for (int b = a + 1; b < vertexCount; b++) {
                    long key = pairKey(a, b, vertexCount);
                    if (!usedPairs.contains(key)) {
                        candidates.add(key);
                    }
                }
            }
            Collections.shuffle(candidates, random);

            for (int i = 0; i < extraEdges; i++) {
                long key = candidates.get(i);
                addEdge(graph, (int) (key / vertexCount), (int) (key % vertexCount), maxWeight);
            }
        }
        return graph;
    }

    private void addEdge(Graph graph, int a, int b, int maxWeight) {
        int weight = 1 + random.nextInt(maxWeight);
        graph.addEdge(new Edge("V" + a, "V" + b, weight));
    }

    // Identifica un par de vértices sin importar el orden (a,b es lo mismo que b,a).
    private long pairKey(int a, int b, int vertexCount) {
        long low = Math.min(a, b);
        long high = Math.max(a, b);
        return low * vertexCount + high;
    }
}
