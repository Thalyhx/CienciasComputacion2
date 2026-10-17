/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.algorithms;

import grafos.representaciones.AdjacencyMatrixGraph;
import java.util.List;

/**
 *
 * @author natha
 */
public class FloydWarshall {
    
    public double[][] shortestPaths(AdjacencyMatrixGraph graph) {
        List<List<String>> matrix = graph.getMatrix();
        int matrixSize = matrix.size();
        double[][] dist = new double[matrixSize][matrixSize];

        // se llena la matriz de distancias inicial
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                String weight = matrix.get(i).get(j);
                
                //si es el mismo valor la distancia es 0 siempre
                if (i == j) {
                    dist[i][j] = 0.0;
                    
                //si el peso inicial es 0 quiere decir que no tiene camino entonces distancia infinita
                } else if (weight == null || weight.equals("0") || weight.trim().isEmpty()) {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                    
                //Parsea los demas numeros    
                } else {
                    try {
                        dist[i][j] = Double.parseDouble(weight);
                    } catch (NumberFormatException e) {
                        dist[i][j] = 1.0;
                    }
                }
            }
        }

        // 
        for (int k = 0; k < matrixSize; k++) {  //nodos intermedios
            for (int i = 0; i < matrixSize; i++) { //nodos origen
                for (int j = 0; j < matrixSize; j++) {  //nodos destino
                    
                    //Si la distancia del camino existe y la distancia de la suma es menor a la directa entonces:
                    if (dist[i][k] != Double.POSITIVE_INFINITY && 
                        dist[k][j] != Double.POSITIVE_INFINITY && 
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        
                        //Se establece como nueva distancia
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }
    
}
