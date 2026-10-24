package input;

import java.util.Scanner;

import model.Edge;
import model.Graph;

public class GraphInputReader {

    public static final String END_KEYWORD = "fin";

    private final Scanner scanner;

    public GraphInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Graph readGraph() {
        Graph graph = new Graph();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase(END_KEYWORD)) {
                break;
            }

            Edge edge = parseEdge(line);
            if (edge != null) {
                graph.addEdge(edge);
            }
        }
        return graph;
    }

    private Edge parseEdge(String line) {
        String[] tokens = line.split("\\s+");

        if (tokens.length != 3) {
            System.out.println("  [!] Formato inválido \"" + line + "\". Usa: vértice vértice peso (ej: A B 19). Línea ignorada.");
            return null;
        }

        try {
            double weight = Double.parseDouble(tokens[2]);
            if (Double.isNaN(weight) || Double.isInfinite(weight)) {
                System.out.println("  [!] El peso \"" + tokens[2] + "\" no es válido. Línea ignorada.");
                return null;
            }
            return new Edge(tokens[0], tokens[1], weight);
        } catch (NumberFormatException e) {
            System.out.println("  [!] El peso \"" + tokens[2] + "\" no es un número. Línea ignorada.");
            return null;
        }
    }
}