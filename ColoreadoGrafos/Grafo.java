
public class Grafo {

    // creamos los nombres de nuestros vertices
    static String[] vertices = {"A", "B", "C", "D", "E"};

    // construimos nuestra matriz de adyacencia
    static int[][] matriz = {
            {0, 0, 0, 0, 1},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {1, 0, 0, 1, 0}
    };

    // mostramos las conexiones de nuestro grafo
    public static void mostrarGrafo() {

        System.out.println("\n");
        System.out.println("          GRAFO ORIGINAL");
        System.out.println("");

        // recorremos todos los vertices
        for (int i = 0; i < vertices.length; i++) {

            System.out.print(vertices[i] + " --> ");

            // buscamos los vecinos del vertice actual
            for (int j = 0; j < vertices.length; j++) {

                if (matriz[i][j] == 1) {
                    System.out.print(vertices[j] + " ");
                }
            }

            System.out.println();
        }
    }

    // mostramos la matriz de adyacencia
    public static void mostrarMatriz() {

        System.out.println("\n");
        System.out.println("       MATRIZ DE ADYACENCIA");
        System.out.println("");

        // mostramos los nombres de las columnas
        System.out.print("   ");

        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + " ");
        }

        System.out.println();

        // recorremos las filas y columnas de la matriz
        for (int i = 0; i < matriz.length; i++) {

            System.out.print(vertices[i] + "  ");

            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }

            System.out.println();
        }
    }

    // mostramos los resultados de cada algoritmo
    public static void mostrarColoreado(String nombre, int[] colores) {

        System.out.println("\n");
        System.out.println(nombre);
        System.out.println("");

        // recorremos los vertices y mostramos sus colores
        for (int i = 0; i < vertices.length; i++) {

            System.out.println(
                    "Vertice " + vertices[i] +
                            " -> Color " + (colores[i] + 1)
            );
        }

        // mostramos la cantidad de colores utilizados
        System.out.println(
                "Cantidad de colores utilizados: " +
                        contarColores(colores)
        );
    }

    // contamos cuantos colores diferentes se utilizaron
    public static int contarColores(int[] colores) {

        int mayor = -1;

        for (int i = 0; i < colores.length; i++) {

            if (colores[i] > mayor) {
                mayor = colores[i];
            }
        }

        return mayor + 1;
    }

    // iniciamos nuestro programa principal
    public static void main(String[] args) {

        // mostramos nuestro grafo y su matriz
        mostrarGrafo();
        mostrarMatriz();

        // ejecutamos el algoritmo voraz
        int[] resultadoVoraz = Voraz.colorear(matriz);

        // ejecutamos el algoritmo welsh-powell
        int[] resultadoWelsh = WelshPowell.colorear(matriz);

        // mostramos los resultados de ambos algoritmos
        mostrarColoreado(
                "RESULTADO DEL ALGORITMO VORAZ",
                resultadoVoraz
        );

        mostrarColoreado(
                "RESULTADO DEL ALGORITMO WELSH-POWELL",
                resultadoWelsh
        );

        // comparamos la cantidad de colores utilizados
        int coloresVoraz = contarColores(resultadoVoraz);
        int coloresWelsh = contarColores(resultadoWelsh);

        System.out.println("\n");
        System.out.println("          COMPARACION");
        System.out.println("");

        System.out.println("Voraz utilizo: " + coloresVoraz + " colores");
        System.out.println("Welsh-Powell utilizo: " + coloresWelsh + " colores");

        if (coloresVoraz < coloresWelsh) {

            System.out.println("Voraz utilizo menos colores.");

        } else if (coloresWelsh < coloresVoraz) {

            System.out.println("Welsh-Powell utilizo menos colores.");

        } else {

            System.out.println("Ambos utilizaron la misma cantidad de colores.");
        }

        System.out.println("\n");
        System.out.println("        FIN DEL PROGRAMA");
        System.out.println("");
    }
}
