public class Arista {

    private Nodo nodoA;
    private Nodo nodoB;
    private double peso;

    public Arista(Nodo nodoA, Nodo nodoB, double peso) {
        this.nodoA = nodoA;
        this.nodoB = nodoB;
        this.peso = peso;
    }

    public Nodo getNodoA() {
        return nodoA;
    }

    public Nodo getNodoB() {
        return nodoB;
    }

    public double getPeso() {
        return peso;
    }

    public boolean conecta(Nodo nodo1, Nodo nodo2, boolean dirigido) {

        // verifico el orden de los nodos si el grafo es dirigido
        if (dirigido) {
            return nodoA.equals(nodo1) && nodoB.equals(nodo2);
        }

        // compruebo ambas orientaciones si el grafo no es dirigido
        return (nodoA.equals(nodo1) && nodoB.equals(nodo2))
                || (nodoA.equals(nodo2) && nodoB.equals(nodo1));
    }

    @Override
    public String toString() {

        // muestro los nodos conectados y el peso de la arista
        return nodoA + " -- " + nodoB + " (peso: " + peso + ")";
    }
}