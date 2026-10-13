/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class IncidenceListGraph extends Graph{
    
    private List<Edge> edgeList;
    
    public IncidenceListGraph() {
        super();
        this.edgeList = new ArrayList<>();
    }

    public IncidenceListGraph(boolean isDirected) {
        super(isDirected);
        this.edgeList = new ArrayList<>();
    }
    
    @Override
    public boolean addNode(String key) {
        if (key == null) return false;
        
        if (!nodes.containsKey(key)) {
            nodes.put(key, new Node(key));
            return true;
        }
        return false;
    }
    
    @Override
    public boolean deleteNode(String key) {
        if (!nodes.containsKey(key)) {
            System.out.println("Error: the node does not exists");
            return false;
        }

        edgeList.removeIf(edge -> 
            (edge.getStart() != null && edge.getStart().getKey().equals(key)) ||
            (edge.getEnd() != null && edge.getEnd().getKey().equals(key))
        );

        nodes.remove(key);
        System.out.println("> Vertice '" + key + "' y sus aristas eliminadas");
        return true;
    }
    
    @Override
    public boolean addEdge(String start, String end, double weight) {

        if (hasNode(start) && hasNode(end)) {

            if (weight > 0) {
                Node origenNode = nodes.get(start);
                Node destinoNode = nodes.get(end);         

                edgeList.add(new Edge(origenNode, destinoNode, weight));
                
                if (!isDirected && !start.equals(end)) {
                    edgeList.add(new Edge(destinoNode, origenNode, weight));
                }
                
                return true;
            } else {
                System.out.println("Error: the weight must be greater than 0");
                return false;
            }
        } else {
            System.out.println("Error: One or both nodes does not exists");
            return false;
        }
    }
    
    public boolean addEdge(String start, String end) {
        return addEdge(start, end, 0); 
    }
    
    @Override
    public boolean deleteEdge(String start, String end) {
        boolean removed = edgeList.removeIf(edge -> 
            edge.getStart() != null && edge.getStart().getKey().equals(start) &&
            edge.getEnd() != null && edge.getEnd().getKey().equals(end)
        );

        if (!isDirected) {
            edgeList.removeIf(edge -> 
                edge.getStart() != null && edge.getStart().getKey().equals(end) &&
                edge.getEnd() != null && edge.getEnd().getKey().equals(start)
            );
        }

        if (!removed) {
            System.out.println("Error: La arista especificada no existe.");
            return false;
        }
        return true;
    }
    
    @Override
    public boolean updateWeight(String start, String end, double newWeight) {
        if (!hasNode(start) || !hasNode(end)) {
            return false;
        }
        if (newWeight <= 0) {
            System.out.println("Error: the weight must be greater than 0");
            return false;
        }

        boolean updated = false;
        for (Edge edge : edgeList) {
            if (edge.getStart() != null && edge.getStart().getKey().equals(start) &&
                edge.getEnd() != null && edge.getEnd().getKey().equals(end)) {
                edge.setWeight(newWeight);
                updated = true;
            }
        }

        if (updated) {
            System.out.println("> Peso actualizado ");
        } else {
            System.out.println("Error: La arista no existe");
        }
        return updated;
    }
    
    public List<Edge> getWeightedNeighbors(String nodeKey) {
        List<Edge> weightedNeighbors = new ArrayList<>();
        if (!hasNode(nodeKey)) {
            System.out.println("Error: the node does not exists");
            return weightedNeighbors;
        }

        for (Edge edge : edgeList) {
            if (edge.getStart() != null && edge.getStart().getKey().equals(nodeKey)) {
                weightedNeighbors.add(edge);
            }
        }
        return weightedNeighbors;
    }
    
    @Override
    public List<Node> getNeighbors(String nodeKey) {
        List<Node> neighbors = new ArrayList<>();
        
        if (!hasNode(nodeKey)) {
            System.out.println("Error: the node does not exists");
            return neighbors;
        }

        for (Edge edge : edgeList) {
            if (edge.getStart() != null && edge.getStart().getKey().equals(nodeKey)) {
                neighbors.add(edge.getEnd());
            } else if (edge.getEnd() != null && edge.getEnd().getKey().equals(nodeKey) && edge.getStart() != null) {
                
                neighbors.add(edge.getStart());
            }
        }
        
        return neighbors;
    }
    
    @Override
    public void showGraph() {
        System.out.println("\n--- INCIDENCE LIST GRAPH -------");
        System.out.println("All nodes (" + nodes.size() + "): " + nodes.keySet());
        System.out.println("All weighted edges (" + edgeList.size() + "):");
        for (Edge edge : edgeList) {
            System.out.println("  " + edge);
        }
        System.out.println("----------------------------------------------------------------");
    }
    
    
}
