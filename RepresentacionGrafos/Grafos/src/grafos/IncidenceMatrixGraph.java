/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juli
 */
public class IncidenceMatrixGraph extends Graph{
    
    private List<List<Integer>> matriz;
    private List<String> nodosOrdenados;
    private List<Edge> aristas;
    
    public IncidenceMatrixGraph() {
        super();
        this.matriz = new ArrayList<>();
        this.nodosOrdenados = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public IncidenceMatrixGraph(boolean isDirected) {
        super(isDirected);
        this.matriz = new ArrayList<>();
        this.nodosOrdenados = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }
    
    @Override
    public boolean addNode(String key) {
        if (key == null || hasNode(key)) {
            return false;
        }
        
        nodosOrdenados.add(key);
        nodes.put(key, new Node(key));
        
        List<Integer> nuevaFila = new ArrayList<>();
        int numAristas = aristas.size();
        for (int i = 0; i < numAristas; i++) {
            nuevaFila.add(0);
        }
        matriz.add(nuevaFila);
        return true;
    }

    @Override
    public boolean deleteNode(String key) {
        int index = nodosOrdenados.indexOf(key);
        if (index == -1) {
            return false;
        }

        List<Edge> aristasAEliminar = new ArrayList<>();
        for (Edge arista : aristas) {
            if ((arista.getStart() != null && arista.getStart().getKey().equals(key)) ||
                (arista.getEnd() != null && arista.getEnd().getKey().equals(key))) {
                aristasAEliminar.add(arista);
            }
        }

        for (Edge e : aristasAEliminar) {
            int edgeIndex = aristas.indexOf(e);
            if (edgeIndex != -1) {
                deleteEdgeByIndex(edgeIndex);
            }
        }

        nodosOrdenados.remove(index);
        nodes.remove(key);
        matriz.remove(index);
        return true;
    }


    private void deleteEdgeByIndex(int indice) {
        if (indice < 0 || indice >= aristas.size()) return;

        // Eliminar la columna correspondiente en cada fila de la matriz
        for (List<Integer> fila : matriz) {
            if (indice < fila.size()) {
                fila.remove(indice);
            }
        }
        // Eliminar la arista de la lista
        aristas.remove(indice);
    }

    @Override
    public boolean addEdge(String nodo1, String nodo2, double peso) {
        
        // Agregamos el nodo1 si no existe
        if (!nodosOrdenados.contains(nodo1)) {
            addNode(nodo1);
        }

        // Agregamos el nodo2 si no existe
        if (!nodosOrdenados.contains(nodo2)) {
            addNode(nodo2);
        }

        Node n1 = nodes.get(nodo1);
        Node n2 = nodes.get(nodo2);

        // Agregamos la arista con su peso
        aristas.add(new Edge(n1, n2, peso));

        // Agregamos una nueva columna (0) 
        for (List<Integer> fila : matriz) {
            fila.add(0);
        }

        // Buscamos los índices actuales
        int indiceA = nodosOrdenados.indexOf(nodo1);
        int indiceB = nodosOrdenados.indexOf(nodo2);
        int ultimaColumna = matriz.get(0).size() - 1;

        // Marcamos la conexión 
        matriz.get(indiceA).set(ultimaColumna, 1);
        matriz.get(indiceB).set(ultimaColumna, 1);

        return true;
    }


    @Override
    public boolean deleteEdge(String start, String end) {

        int indice = -1;
        for (int i = 0; i < aristas.size(); i++) {
            Edge e = aristas.get(i);
            if (e.getStart() != null && e.getEnd() != null) {
                if ((e.getStart().getKey().equals(start) && e.getEnd().getKey().equals(end)) ||
                    (!isDirected && e.getStart().getKey().equals(end) && e.getEnd().getKey().equals(start))) {
                    indice = i;
                    break;
                }
            }
        }

        if (indice == -1) {
            return false;
        }

        deleteEdgeByIndex(indice);
        return true;
    }


    public boolean modificarArista(int indice, String nodo1, String nodo2, double peso) {
        deleteEdgeByIndex(indice);
        return addEdge(nodo1, nodo2, peso);
    }

    @Override
    public boolean updateWeight(String start, String end, double newWeight) {
        for (Edge e : aristas) {
            if (e.getStart() != null && e.getEnd() != null) {
                if ((e.getStart().getKey().equals(start) && e.getEnd().getKey().equals(end)) ||
                    (!isDirected && e.getStart().getKey().equals(end) && e.getEnd().getKey().equals(start))) {
                    e.setWeight(newWeight);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Node> getNeighbors(String nodeKey) {
        List<Node> vecinos = new ArrayList<>();
        int nodeIndex = nodosOrdenados.indexOf(nodeKey);
        
        if (nodeIndex == -1) {
            System.out.println("Error: the node does not exists");
            return vecinos;
        }


        List<Integer> fila = matriz.get(nodeIndex);
        for (int col = 0; col < fila.size(); col++) {
            if (fila.get(col) == 1) {

                Edge arista = aristas.get(col);
                if (arista.getStart() != null && arista.getEnd() != null) {

                    if (arista.getStart().getKey().equals(nodeKey)) {
                        vecinos.add(arista.getEnd());
                    } else if (arista.getEnd().getKey().equals(nodeKey)) {
                        vecinos.add(arista.getStart());
                    }
                }
            }
        }
        return vecinos;
    }

    @Override
    public void showGraph() {
        System.out.println("\n=== Nodos ===");
        System.out.println(nodosOrdenados);

        System.out.println("\n=== Aristas ===");
        for (Edge e : aristas) {
            System.out.println("From: " + (e.getStart() != null ? e.getStart().getKey() : "?") + 
                               ", To: " + (e.getEnd() != null ? e.getEnd().getKey() : "?") + 
                               ", Weight: " + e.getWeight());
        }

        System.out.println("\n=== Matriz de Incidencia ===");
        for (int i = 0; i < matriz.size(); i++) {
            System.out.println(nodosOrdenados.get(i) + " " + matriz.get(i));
        }
        System.out.println("=========================================");
    }
    
}
