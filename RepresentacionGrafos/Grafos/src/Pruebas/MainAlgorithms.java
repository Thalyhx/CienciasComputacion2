/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import grafos.algorithms.FloydWarshall;
import grafos.algoritmos.BellmanFord;
import grafos.algoritmos.Dijkstra;
import grafos.representaciones.AdjacencyListGraph;
import grafos.representaciones.AdjacencyMatrixGraph;
import java.util.Map;

/**
 *
 * @author natha
 */
public class MainAlgorithms {
    
    public static void main(String[] args) {

        AdjacencyMatrixGraph grafoMatriz = new AdjacencyMatrixGraph(true);
        
        //Crear nodos en orden
        
        grafoMatriz.addNode("A");
        grafoMatriz.addNode("B");
        grafoMatriz.addNode("C");
        grafoMatriz.addNode("D");
        grafoMatriz.addNode("E");
        
        // Inserción de aristas 
        grafoMatriz.addEdge("A", "B", 4.0);
        grafoMatriz.addEdge("B", "A", 4.0); 
        grafoMatriz.addEdge("A", "C", 8.0); 
        grafoMatriz.addEdge("C", "A", 8.0);
        grafoMatriz.addEdge("B", "C", 1.0);
        grafoMatriz.addEdge("B", "D", 2.0);
        grafoMatriz.addEdge("D", "B", 2.0);
        grafoMatriz.addEdge("C", "D", 4.0);
        grafoMatriz.addEdge("C", "E", 2.0);
        grafoMatriz.addEdge("E", "C", 2.0);
        grafoMatriz.addEdge("E", "D", 7.0);

        System.out.println("=== MATRIZ ORIGINAL DEL GRAFO ===");
        grafoMatriz.showGraph();

        // Floyd-Warshall
        FloydWarshall fw = new FloydWarshall();
        double[][] resultadoFW = fw.shortestPaths(grafoMatriz);

        //  resultados de Floyd-Warshall
        int V = resultadoFW.length;
        System.out.println("\n=== FLOYD-WARSHALL ===");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (resultadoFW[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(resultadoFW[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("=================================================");


        AdjacencyListGraph grafoLista = new AdjacencyListGraph(true);

        // Mismo grafo de prueba
        grafoLista.addEdge("A", "B", 4.0);
        grafoLista.addEdge("A", "C", 2.0);
        grafoLista.addEdge("B", "C", 1.0);
        grafoLista.addEdge("B", "D", 5.0);
        grafoLista.addEdge("C", "D", 8.0);
        grafoLista.addEdge("C", "E", 10.0);
        grafoLista.addEdge("D", "E", 2.0);

        String nodoOrigen = "A";
        System.out.println("\n=== GRAFO DE PRUEBA LISTA (Origen: " + nodoOrigen + ") ===");
        grafoLista.showGraph();

        //l algoritmo de Dijkstra
        System.out.println("\n--------------------------------------------------");
        System.out.println("RESULTADOS: Algoritmo de Dijkstra");
        System.out.println("--------------------------------------------------");
        Dijkstra dijkstra = new Dijkstra(grafoLista, nodoOrigen);
        for (Map.Entry<String, Double> entry : dijkstra.getShortestPaths().entrySet()) {
            System.out.println("Distancia minima de " + nodoOrigen + " a " + entry.getKey() + " = " + entry.getValue());
        }

        // algoritmo de Bellman-Ford
        System.out.println("\n--------------------------------------------------");
        System.out.println("RESULTADOS: Algoritmo de Bellman-Ford");
        System.out.println("--------------------------------------------------");
        BellmanFord bellmanFord = new BellmanFord(grafoLista, nodoOrigen);
        for (Map.Entry<String, Double> entry : bellmanFord.getShortestPaths().entrySet()) {
            System.out.println("Distancia minima de " + nodoOrigen + " a " + entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("--------------------------------------------------");
    }
    
}
