/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.representaciones;

import grafos.Graph;
import grafos.Node;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleja
 */
public class AdjacencyMatrixGraph extends Graph {
    
    private List<List<String>> matrix;
    private List<String> orderedKeys;
    
    public AdjacencyMatrixGraph() {
        super();
        this.matrix = new ArrayList<>();
        this.orderedKeys = new ArrayList<>();
    }
    
    public AdjacencyMatrixGraph(boolean isDirected) {
        super(isDirected);
        this.matrix = new ArrayList<>();
        this.orderedKeys = new ArrayList<>();
    }
    
    private int searchIndex(String key) {
        for (int i = 0; i < orderedKeys.size(); i++) {
            if (orderedKeys.get(i).equals(key)) {
                return i;
            }
        }
        return -1;
    }
    
    private int getIndex(String key) {
        int index = searchIndex(key);
        if (index != -1) return index;
        orderedKeys.add(key);
        nodes.put(key, new Node(key));

        for (List<String> fila : matrix) {
            fila.add("0");
        }

        List<String> nuevaFila = new ArrayList<>();
        for (int i = 0; i < orderedKeys.size(); i++) {
            nuevaFila.add("0");
        }
        matrix.add(nuevaFila);

        return orderedKeys.size() - 1;
    }
    
    @Override
    public boolean addNode(String key) {
        if (key == null || hasNode(key)) {
            return false;
        }
        getIndex(key); 
        return true;
    }
    
    @Override
    public boolean deleteNode(String key) {
        int index = searchIndex(key);
        if (index == -1) {
            System.out.println("the node:  " + key + " dosnt exist");
            return false;
        }
        orderedKeys.remove(index);
        nodes.remove(key);
        matrix.remove(index);

        for (List<String> fila : matrix) {
            fila.remove(index);
        }

        System.out.println("> Node'" + key + "' and Edges end");
        return true;
    }
    
    @Override
    public boolean addEdge(String start, String end, double weight) {
        String wStr = String.valueOf(weight);
        
        int i = searchIndex(start);
        int j = searchIndex(end);

        if (i != -1 && j != -1 && !matrix.get(i).get(j).equals("0")) {
            System.out.println("-> The relation already exist. Use: update");
            return false;
        }

        i = getIndex(start);
        j = getIndex(end);
        
        matrix.get(i).set(j, wStr);
        
        if (!isDirected) {
            matrix.get(j).set(i, wStr);
        }

        System.out.println("> Arista entre " + start + " y " + end + " agregada con peso " + weight);
        return true;
    }
    
    @Override
    public boolean deleteEdge(String start, String end) {
        int i = searchIndex(start);
        int j = searchIndex(end);

        if (i == -1 || j == -1 || matrix.get(i).get(j).equals("0")) {
            System.out.println("-> The relation doesn't exist.");
            return false;
        }

        matrix.get(i).set(j, "0");
        
        if (!isDirected) {
            matrix.get(j).set(i, "0");
        }

        System.out.println("> Arista entre " + start + " y " + end + " eliminada");
        return true;
    }
    
    @Override
    public boolean updateWeight(String start, String end, double weight) {
        String wStr = String.valueOf(weight);
        
        int i = searchIndex(start);
        int j = searchIndex(end);

        if (i == -1 || j == -1 || matrix.get(i).get(j).equals("0")) {
            System.out.println("-> The relation doesn't exist. Use: addEdge");
            return false;
        }

        matrix.get(i).set(j, wStr);
        
        if (!isDirected) {
            matrix.get(j).set(i, wStr);
        }

        System.out.println("> Peso actualizado ");
        return true;
    }
    
    @Override
    public List<Node> getNeighbors(String nodeKey) {
        List<Node> vecinos = new ArrayList<>();
        int i = searchIndex(nodeKey);
        
        if (i == -1) return vecinos;

        List<String> fila = matrix.get(i);
        for (int j = 0; j < fila.size(); j++) {
            if (!fila.get(j).equals("0")) {
                String vecinoKey = orderedKeys.get(j);

                vecinos.add(nodes.get(vecinoKey));
            }
        }
        return vecinos;
    }
    
    @Override
    public void showGraph() {
        System.out.println("\n--- ADJACENT MATRIX ---");
        System.out.print("   ");
        for (String n : orderedKeys) {
            System.out.print(n + " ");
        }
        System.out.println();

        for (int i = 0; i < orderedKeys.size(); i++) {
            System.out.print(orderedKeys.get(i) + "  ");
            for (int j = 0; j < orderedKeys.size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
    }
    
}
