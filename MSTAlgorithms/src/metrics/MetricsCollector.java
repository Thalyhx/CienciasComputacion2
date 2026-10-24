package metrics;

import algorithms.MSTAlgorithm;
import algorithms.MSTResult;
import model.Graph;

// Mide un algoritmo sobre un grafo: lo ejecuta varias veces y promedia el tiempo.
public class MetricsCollector {

    public static final int DEFAULT_RUNS = 10;

    public ExecutionMetrics measure(MSTAlgorithm algorithm, Graph graph) {
        return measure(algorithm, graph, DEFAULT_RUNS);
    }

    public ExecutionMetrics measure(MSTAlgorithm algorithm, Graph graph, int runs) {
       
        algorithm.run(graph, new OperationCounter());

        long totalNanos = 0;
        OperationCounter counter = null;
        MSTResult result = null;

        for (int i = 0; i < runs; i++) {
            counter = new OperationCounter();
            long start = System.nanoTime();
            result = algorithm.run(graph, counter);
            totalNanos += System.nanoTime() - start;
        }

        double averageNanos = (double) totalNanos / runs;
        return new ExecutionMetrics(algorithm.getName(), averageNanos, counter, result);
    }
}