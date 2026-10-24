import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import algorithms.KruskalAlgorithm;
import algorithms.MSTAlgorithm;
import algorithms.MSTResult;
import algorithms.PrimAlgorithm;
import analysis.GraphAnalyzer;
import analysis.GraphProfile;
import analysis.RecommendationEngine;
import input.GraphInputReader;
import metrics.ExecutionMetrics;
import metrics.MetricsCollector;
import metrics.MetricsTable;
import metrics.OperationCounter;
import model.Graph;
import validation.GraphValidator;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            if (!scanner.hasNextLine()) {
                break;
            }
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    compareManually(scanner);
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println(" -> Opción inválida. Elige 1 o 2.");
            }
        }
        System.out.println(" Saliendo ...");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("*** Comparador de algoritmos MST (Minimum Spanning Tree): Prim vs Kruskal ***");
        System.out.println(" 1. Ingresar un grafo y comparar los algoritmos");
        System.out.println(" 2. Salir");
        System.out.print("Opción: ");
    }

    private static void compareManually(Scanner scanner) {

        System.out.println();
        System.out.println("- Ingresa una arista por línea con el formato: vértice vértice peso   (ej: A B 19)");
        System.out.println("- Escribe 'fin' para terminar.");
        Graph graph = new GraphInputReader(scanner).readGraph();

        List<String> problems = new GraphValidator().validate(graph);
        if (!problems.isEmpty()) {
            System.out.println();
            System.out.println("- El grafo no es válido para calcular un MST:");
            for (String problem : problems) {
                System.out.println("  - " + problem);
            }
            return;
        }



        GraphProfile profile = new GraphAnalyzer().analyze(graph);



        List<MSTAlgorithm> algorithms = Arrays.asList(new PrimAlgorithm(), new KruskalAlgorithm());
        List<MSTResult> results = new ArrayList<>();
        for (MSTAlgorithm algorithm : algorithms) {
            MSTResult result = algorithm.run(graph, new OperationCounter());
            System.out.println();
            System.out.println("*** " + algorithm.getName() + " ***");
            System.out.println(result.describe());
            results.add(result);
        }


        System.out.println();
        if (!results.get(0).sameTotalWeightAs(results.get(1))) {
            System.out.println("-> ¡ERROR! Los algoritmos dieron pesos distintos; hay un fallo en la implementación.");
            return;
        }
        System.out.println("-> Verificación: ambos algoritmos dieron el mismo peso total del MST.");


        List<ExecutionMetrics> metrics = new ArrayList<>();
        MetricsCollector collector = new MetricsCollector();
        for (MSTAlgorithm algorithm : algorithms) {
            metrics.add(collector.measure(algorithm, graph));
        }

        System.out.println();
        System.out.println("* Métricas (tiempo promedio de " + MetricsCollector.DEFAULT_RUNS + " repeticiones):");
        System.out.print(new MetricsTable().format(metrics));

        System.out.println();
        System.out.println("* Recomendación:");
        System.out.println(new RecommendationEngine().recommend(profile, metrics).describe());
    }
}
