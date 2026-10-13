/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

/**
 *
 * @author natha
 */
public class Node {
    
    private String key;
    private boolean visited;
        
    //Costructor
    
    

    public Node(String key) {
        this.key = key;
        this.visited = false;
    }
        
   //Getters

    public String getKey() {
        return key;
    }

    public boolean isVisited() {
        return visited;
    }
    
    //Setters

    public void setKey(String key) {
        this.key = key;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    //toString

    @Override
    public String toString() {
        return "Node: " + key;
    }
    
    
}
