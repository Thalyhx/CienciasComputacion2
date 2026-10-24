package analysis;

import java.util.List;

import algorithms.KruskalAlgorithm;
import algorithms.PrimAlgorithm;
import metrics.ExecutionMetrics;

// Compara las métricas de los algoritmos y las contrasta con la teoría: Prim conviene en grafos densos y Kruskal en grafos dispersos.
public class RecommendationEngine {

    public Recommendation recommend(GraphProfile profile, List<ExecutionMetrics> metrics) {
        String theoretical = profile.isDense() ? PrimAlgorithm.NAME : KruskalAlgorithm.NAME;

        ExecutionMetrics fastest = metrics.get(0);
        ExecutionMetrics fewestOperations = metrics.get(0);
        boolean operationsTie = false;

        for (int i = 1; i < metrics.size(); i++) {
            ExecutionMetrics current = metrics.get(i);

            // Ganador por tiempo
            if (current.getAverageTimeNanos() < fastest.getAverageTimeNanos()) {
                fastest = current;
            }

            // Ganador por operaciones
            long currentOps = current.getOperations().totalOperations();
            long bestOps = fewestOperations.getOperations().totalOperations();
            if (currentOps < bestOps) {
                fewestOperations = current;
                operationsTie = false;
            } else if (currentOps == bestOps) {
                operationsTie = true;
            }
        }

        String operationsWinner = operationsTie ? "Empate" : fewestOperations.getAlgorithmName();
        return new Recommendation(profile, theoretical, fastest.getAlgorithmName(), operationsWinner);
    }
}