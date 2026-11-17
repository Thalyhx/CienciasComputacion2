import java.util.*;

/**
 * Coloreado de grafos con el algoritmo DSatur (Brélaz, 1979).
 * Grafo no dirigido representado con listas de adyacencia.
 */
public class DSatur {

    private final int n;                       // número de vértices
    private final List<Set<Integer>> adj;      // listas de adyacencia

    public DSatur(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
    }

    public void agregarArista(int u, int v) {
        if (u == v) return;                    // sin lazos
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public int[] colorear() {
        int[] color = new int[n];
        Arrays.fill(color, -1);                // -1 = sin colorear

        // Colores distintos presentes en los vecinos de cada vértice (saturación = tamaño)
        List<Set<Integer>> coloresVecinos = new ArrayList<>();
        // Grado considerando solo vecinos aún sin colorear (criterio de desempate)
        int[] gradoSinColorear = new int[n];

        for (int v = 0; v < n; v++) {
            coloresVecinos.add(new HashSet<>());
            gradoSinColorear[v] = adj.get(v).size();
        }

        for (int paso = 0; paso < n; paso++) {
            // 1. Elegir el vértice sin colorear con mayor saturación (desempate: mayor grado)
            int v = seleccionarVertice(color, coloresVecinos, gradoSinColorear);

            // 2. Paso VORAZ: menor color no usado por sus vecinos
            int c = 0;
            while (coloresVecinos.get(v).contains(c)) {
                c++;
            }
            color[v] = c;

            // 3. Actualizar saturación y grado de los vecinos
            for (int u : adj.get(v)) {
                coloresVecinos.get(u).add(c);
                gradoSinColorear[u]--;
            }
        }
        return color;
    }

    private int seleccionarVertice(int[] color, List<Set<Integer>> coloresVecinos, int[] gradoSinColorear) {
        int mejor = -1;
        for (int v = 0; v < n; v++) {
            if (color[v] != -1) continue;
            if (mejor == -1) {
                mejor = v;
                continue;
            }
            int satV = coloresVecinos.get(v).size();
            int satMejor = coloresVecinos.get(mejor).size();
            if (satV > satMejor
                    || (satV == satMejor && gradoSinColorear[v] > gradoSinColorear[mejor])) {
                mejor = v;
            }
        }
        return mejor;
    }

    /** Número de colores usados: cota superior del número cromático. */
    public static int contarColores(int[] color) {
        int max = -1;
        for (int c : color) max = Math.max(max, c);
        return max + 1;
    }

    /** Verifica que ningún par de vértices adyacentes comparta color. */
    public boolean esColoreadoValido(int[] color) {
        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) {
                if (color[u] == color[v]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Ejemplo: ciclo C5 (0-1-2-3-4-0) más el vértice 5 conectado a 0, 1 y 2
        DSatur g = new DSatur(6);
        g.agregarArista(0, 1);
        g.agregarArista(1, 2);
        g.agregarArista(2, 3);
        g.agregarArista(3, 4);
        g.agregarArista(4, 0);
        g.agregarArista(5, 0);
        g.agregarArista(5, 1);
        g.agregarArista(5, 2);

        int[] color = g.colorear();

        for (int v = 0; v < color.length; v++) {
            System.out.println("Vértice " + v + " -> color " + color[v]);
        }
        System.out.println("Colores usados: " + contarColores(color));
        System.out.println("Coloreado válido: " + g.esColoreadoValido(color));
    }
}