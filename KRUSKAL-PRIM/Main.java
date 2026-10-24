import java.util.List;

public class Main {

    public static void main(String[] args) {

        // creo un grafo no dirigido
        Grafo grafo = new Grafo(false);

        // creo los cinco nodos
        Nodo a = new Nodo("A");
        Nodo b = new Nodo("B");
        Nodo c = new Nodo("C");
        Nodo d = new Nodo("D");
        Nodo e = new Nodo("E");

        // agrego todos los nodos al grafo
        grafo.agregarNodo(a);
        grafo.agregarNodo(b);
        grafo.agregarNodo(c);
        grafo.agregarNodo(d);
        grafo.agregarNodo(e);

        // agrego las aristas con sus respectivos pesos
        grafo.agregarArista(a, b, 2);
        grafo.agregarArista(a, c, 6);
        grafo.agregarArista(a, d, 5);
        grafo.agregarArista(b, c, 4);
        grafo.agregarArista(b, d, 3);
        grafo.agregarArista(c, d, 1);
        grafo.agregarArista(d, e, 4);

        // creo una instancia del algoritmo de prim
        Prim prim = new Prim();

        // ejecuto el algoritmo comenzando desde el nodo a
        List<Arista> resultado = prim.ejecutar(grafo, a);

        // muestro las aristas seleccionadas
        System.out.println("aristas del arbol de expansion minima:");

        for (Arista arista : resultado) {
            System.out.println(arista);
        }

        // muestro los nodos que visite
        System.out.println("\nnodos visitados:");
        System.out.println(prim.getVisitados());

        // calculo y muestro el peso total
        System.out.println("\npeso total:");
        System.out.println(prim.calcularPesoTotal());
    }
}