import java.util.List;
import java.util.ArrayList;

public class Grafo {

    private List<Nodo> nodos;
    private List<Arista> aristas;
    private boolean dirigido;

    public Grafo(boolean dirigido) {

        this.dirigido = dirigido;
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {

        // agrego el nodo si no existe otro con el mismo nombre
        if (nodo != null && !nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    public void agregarArista(Nodo nodoA, Nodo nodoB, double peso) {

        // verifico que ambos nodos pertenezcan al grafo
        if (!nodos.contains(nodoA) || !nodos.contains(nodoB)) {
            throw new IllegalArgumentException(
                    "los dos nodos deben existir en el grafo"
            );
        }

        // evito conectar un nodo consigo mismo
        if (nodoA.equals(nodoB)) {
            throw new IllegalArgumentException(
                    "no puedo conectar un nodo consigo mismo"
            );
        }

        // verifico que el peso sea un numero finito
        if (!Double.isFinite(peso)) {
            throw new IllegalArgumentException(
                    "el peso debe ser un numero finito"
            );
        }

        // creo y guardo la arista
        Arista arista = new Arista(nodoA, nodoB, peso);
        aristas.add(arista);
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public boolean esDirigido() {
        return dirigido;
    }
}
