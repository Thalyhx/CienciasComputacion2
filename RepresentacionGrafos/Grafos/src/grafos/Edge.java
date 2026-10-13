/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

/**
 *
 * @author natha
 */
public class Edge {
        
        private Node start;
        private Node end;
        private double weight;
        
        //Constructores

        public Edge(Node end, double weight) {
            this.start = null;
            this.end = end;
            this.weight = weight;
        }

        public Edge(Node start, Node end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        
        //Getters

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }
        
        //Setters

    public void setStart(Node start) {
        this.start = start;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    //toString

    @Override
    public String toString() {
        String startString = (start != null) ? start.getKey() : "N/A";
        return "Edge{ " + "start: " + startString + ", end: " + end.getKey() + ", weight: " + weight + "}";
    }  
        
}
