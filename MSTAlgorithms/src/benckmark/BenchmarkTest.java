package benckmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import algorithms.KruskalAlgorithm;
import algorithms.MSTAlgorithm;
import algorithms.MSTResult;
import algorithms.PrimAlgorithm;
import analysis.GraphAnalyzer;
import analysis.GraphProfile;
import analysis.Recommendation;
import analysis.RecommendationEngine;
import metrics.ExecutionMetrics;
import metrics.MetricsCollector;
import model.Graph;

// Experimentos por lotes: genera grafos de distintos tamaños y densidades, corre Prim y Kruskal
// sobre cada uno y muestra cuál fue mejor. Se ejecuta aparte del Main (que es de entrada manual).
public class BenchmarkTest {

    private static final long SEED = 42L;
    private static final int[] VERTEX_COUNTS = {50, 100, 200, 400};
    private static final double[] DENSITIES = {0.05, 0.10, 0.25, 0.50, 0.75, 1.00};
    private static final int MAX_WEIGHT = 1000;
    private static final int RUNS = 30;

    public static void main(String[] args) {
        List<MSTAlgorithm> algorithms = Arrays.asList(new PrimAlgorithm(), new KruskalAlgorithm());
        RandomGraphGenerator generator = new RandomGraphGenerator(SEED);
        MetricsCollector collector = new MetricsCollector();
        GraphAnalyzer analyzer = new GraphAnalyzer();
        RecommendationEngine engine = new RecommendationEngine();

        System.out.println("Ejecutando experimentos (puede tardar unos segundos)...");
        System.out.println();
        System.out.println(String.format(Locale.ROOT, "%5s %7s %9s %-9s %12s %12s %10s %10s %-9s %-9s %-9s",
                "V", "E", "Densidad", "Tipo", "Prim (µs)", "Kruskal (µs)", "Prim ops", "Krus. ops",
                "Más veloz", "Menos ops", "Teoría"));

        int primTimeWins = 0, kruskalTimeWins = 0, primOpsWins = 0, kruskalOpsWins = 0, theoryMatches = 0, total = 0;
        boolean allWeightsEqual = true;

        for (int vertexCount : VERTEX_COUNTS) {
            for (double density : DENSITIES) {
                Graph graph = generator.generate(vertexCount, density, MAX_WEIGHT);

                // Se mide cada algoritmo sobre el MISMO grafo.
                List<ExecutionMetrics> metrics = new ArrayList<>();
                for (MSTAlgorithm algorithm : algorithms) {
                    metrics.add(collector.measure(algorithm, graph, RUNS));
                }

                // Prueba de corrección: mismo peso total y V-1 aristas en ambos.
                MSTResult prim = metrics.get(0).getResult();
                MSTResult kruskal = metrics.get(1).getResult();
                boolean consistent = prim.sameTotalWeightAs(kruskal)
                        && prim.hasExpectedEdgeCount() && kruskal.hasExpectedEdgeCount();
                allWeightsEqual = allWeightsEqual && consistent;

                GraphProfile profile = analyzer.analyze(graph);
                Recommendation recommendation = engine.recommend(profile, metrics);

                System.out.println(String.format(Locale.ROOT, "%5d %7d %9.3f %-9s %12.1f %12.1f %10d %10d %-9s %-9s %-9s%s",
                        profile.getVertexCount(), profile.getEdgeCount(), profile.getDensity(), profile.getTypeLabel(),
                        metrics.get(0).getAverageTimeMicros(), metrics.get(1).getAverageTimeMicros(),
                        metrics.get(0).getOperations().totalOperations(),
                        metrics.get(1).getOperations().totalOperations(),
                        recommendation.getTimeWinner(), recommendation.getOperationsWinner(),
                        recommendation.getTheoreticalChoice(),
                        consistent ? "" : "  <-- ¡PESOS DISTINTOS!"));

                total++;
                if (recommendation.getTimeWinner().equals(PrimAlgorithm.NAME)) primTimeWins++; else kruskalTimeWins++;
                if (recommendation.getOperationsWinner().equals(PrimAlgorithm.NAME)) primOpsWins++;
                if (recommendation.getOperationsWinner().equals(KruskalAlgorithm.NAME)) kruskalOpsWins++;
                if (recommendation.timeMatchesTheory()) theoryMatches++;
            }
        }

        System.out.println();
        System.out.println("RESUMEN (" + total + " grafos)");
        System.out.println("  Más rápido: Prim " + primTimeWins + " veces, Kruskal " + kruskalTimeWins + " veces");
        System.out.println("  Menos operaciones: Prim " + primOpsWins + " veces, Kruskal " + kruskalOpsWins + " veces");
        System.out.println("  La teoría (Prim=denso, Kruskal=disperso) acertó en " + theoryMatches + " de " + total);
        System.out.println("  Nota: el tiempo varía un poco entre corridas; las operaciones siempre dan lo mismo.");

        if (!allWeightsEqual) {
            System.out.println("PRUEBA FALLIDA: algún algoritmo no dio el mismo peso total del MST.");
            System.exit(1);
        }
        System.out.println("PRUEBA OK: ambos algoritmos dieron el mismo peso total en todos los grafos.");
    }
}
