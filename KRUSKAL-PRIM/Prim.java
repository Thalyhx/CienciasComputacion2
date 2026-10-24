import java.util.List;
import java.util.ArrayList;

public class Prim {

    List<Nodo> visitados;
    List<Arista> aristasSeleccionadas;

    public Prim() {

        // creo las listas donde guardo los nodos y las aristas elegidas
        visitados = new ArrayList<>();
        aristasSeleccionadas = new ArrayList<>();
    }

    public Arista buscarMenorArista(Grafo grafo) {

        // comienzo sin haber encontrado ninguna arista
        Arista menor = null;

        // recorro todas las aristas del grafo
        for (Arista arista : grafo.getArista()) {

            // obtengo los dos nodos que conecta la arista actual
            Nodo nodoA = arista.getNodos().get(0);
            Nodo nodoB = arista.getNodos().get(1);

            // verifico si cada nodo ya fue visitado
            boolean aVisitado = visitados.contains(nodoA);
            boolean bVisitado = visitados.contains(nodoB);

            // considero la arista si conecta un nodo visitado
            // con otro que todavía no he visitado
            if (aVisitado != bVisitado) {

                // guardo la arista si es la primera valida
                // o si tiene un peso menor que la anterior
                if (menor == null || arista.getPeso() < menor.getPeso()) {
                    menor = arista;
                }
            }
        }

        // devuelvo la arista mas barata que encontre
        return menor;
    }

    public List<Arista> ejecutar(Grafo grafo, Nodo inicio) {

        // verifico que el grafo no sea dirigido
        if (grafo.esDirigido()) {
            throw new IllegalArgumentException(
                    "prim necesita un grafo no dirigido"
            );
        }

        // verifico que el nodo inicial pertenezca al grafo
        if (!grafo.getNodos().contains(inicio)) {
            throw new IllegalArgumentException(
                    "el nodo inicial no pertenece al grafo"
            );
        }

        // limpio las listas para poder ejecutar el algoritmo nuevamente
        visitados.clear();
        aristasSeleccionadas.clear();

        // comienzo el arbol con el nodo inicial
        visitados.add(inicio);

        // repito el proceso mientras queden nodos sin visitar
        while (visitados.size() < grafo.getNodos().size()) {

            // busco la arista mas barata que conecte el arbol con otro nodo
            Arista menor = buscarMenorArista(grafo);

            // verifico si existen aristas para continuar
            if (menor == null) {
                throw new IllegalStateException(
                        "el grafo no es conexo: no puedo conectar todos los nodos"
                );
            }

            // guardo la arista seleccionada en el arbol
            aristasSeleccionadas.add(menor);

            // obtengo los dos nodos de la arista elegida
            Nodo nodoA = menor.getNodos().get(0);
            Nodo nodoB = menor.getNodos().get(1);

            // agrego a los visitados el nodo que todavia no pertenece al arbol
            if (visitados.contains(nodoA)) {
                visitados.add(nodoB);
            } else {
                visitados.add(nodoA);
            }
        }

        // devuelvo todas las aristas que forman el arbol de expansion minima
        return aristasSeleccionadas;
    }

    public double calcularPesoTotal() {

        // comienzo el calculo sin haber sumado ningun peso
        double pesoTotal = 0;

        // recorro las aristas que seleccione
        for (Arista arista : aristasSeleccionadas) {

            // acumulo el peso de cada arista
            pesoTotal += arista.getPeso();
        }

        // devuelvo el peso total del arbol
        return pesoTotal;
    }

    public List<Nodo> getVisitados() {
        return visitados;
    }

    public List<Arista> getAristasSeleccionadas() {
        return aristasSeleccionadas;
    }
}