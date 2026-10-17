/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import grafos.representaciones.AdjacencyMatrixGraph;
import java.util.Scanner;

/**
 *
 * @author natha
 */
public class MainAdjacencyMatrix {
        
    public static void main(String[] args) {
        AdjacencyMatrixGraph grafo = new AdjacencyMatrixGraph(false); // No dirigido por defecto
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** Adjacency Matrix Interactive Test ***");
        System.out.println("Comandos disponibles: create, update, delete, exit.");
        System.out.println("Ejemplo de formato: create AB 5.0 (Crea o conecta A con B con peso 5.0)");
        
        while (true) {
            System.out.print("\n> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;

            String[] parts = line.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Formato incorrecto.  comando relacion [peso]");
                continue;
            }

            String command = parts[0].toLowerCase();
            String relation = parts[1];

            if (relation.length() != 2) {
                System.out.println("La relacion entre nodos debe ser de 2 caracteres (Ej. AB).");
                continue;
            }

            char a = relation.charAt(0);
            char b = relation.charAt(1);
            double weight = 1.0;

            if (parts.length >= 3) {
                try {
                    weight = Double.parseDouble(parts[2]);
                } catch (NumberFormatException e) {
                    weight = 1.0;
                }
            }

            if (command.equals("create")) {
                grafo.addEdge(String.valueOf(a), String.valueOf(b), weight);
            } else if (command.equals("update")) {
                grafo.updateWeight(String.valueOf(a), String.valueOf(b), weight);
            } else if (command.equals("delete")) {
                grafo.deleteEdge(String.valueOf(a), String.valueOf(b));
            } else {
                System.out.println("Comando desconocido. Use: create, update, delete o exit.");
                continue;
            }

            grafo.showGraph();
        }
        
        scanner.close();
        System.out.println("Saliendo");
    }
    
}
