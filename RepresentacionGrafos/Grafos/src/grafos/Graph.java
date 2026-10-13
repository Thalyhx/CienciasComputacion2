/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grafos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author natha
 */
public abstract class  Graph {
    
    protected boolean isDirected;
    protected Map<String, Node> nodes;
    
    //Constructores

    public Graph() {
        this.nodes = new HashMap<>();
        this.isDirected = false;
    }
    
    public Graph(boolean isDirected) {
        this.nodes = new HashMap<>();
        this.isDirected = isDirected;
    }
    
    public Graph(boolean isDirected, Map<String, Node> nodes) {
        this.isDirected = isDirected;
        this.nodes = nodes;
    }
    
    //Metodos compartidos
    
    public boolean hasNode(String key){
        if (key == null) return false;
        return nodes.containsKey(key);
    }
    
    public boolean hasEdge(String start,String end){
        
        if (!hasNode(start) || !hasNode(end)) {
            return false;
        }
        
        List<Node> vecinos = getNeighbors(start);
        
        for (Node vecino : vecinos) {
            if (vecino.getKey().equals(end)) {
                return true;
            }
        }
        
        return false;
    }
    
    public int getNodeCount(){
        return this.nodes.size();
    }
    
    public boolean isLoop(String origen, String destino) {
        if (origen == null || destino == null) return false;
        return origen.equals(destino); 
    }
    
    public boolean validateWeight(double peso){
        return peso>0;
    }
    
    //Metodos Abstractos
        
    public abstract boolean addNode(String key);
    public abstract boolean deleteNode(String key);
    
    public abstract boolean addEdge(String start,String end, double weight);
    public abstract boolean deleteEdge(String start,String end);
    public abstract boolean updateWeight(String start,String end, double weight);
    
    public abstract List<Node> getNeighbors(String node);
    public abstract void showGraph();
    
    //Getters

    public Map<String, Node> getNodes() {
        return nodes;
    }
    
    //Setters

    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }
    
    
}
