/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import grafos.representaciones.IncidenceListGraph;
import java.util.Scanner;

/**
 *
 * @author natha
 */
public class MainIncidenceList {
    
    public static void main(String[] args) {
        IncidenceListGraph grafo = new IncidenceListGraph(false);
        Scanner scanner = new Scanner(System.in);

        // datos de prueba
        String[] nodosPrueba = {"A", "B", "C", "D", "E", "F", "G"};
        for (String n : nodosPrueba) {
            grafo.addNode(n);
        }

        grafo.addEdge("A", "B", 1.0);
        grafo.addEdge("A", "C", 1.0);
        grafo.addEdge("B", "D", 1.0);
        grafo.addEdge("C", "E", 1.0);
        grafo.addEdge("D", "E", 1.0);
        grafo.addEdge("A", "D", 10.0);
        grafo.addEdge("A", "F", 15.0);
        grafo.addEdge("E", "F", 8.0);

        int opcion = 0;
        do {
            System.out.println("\n------- MENU LISTA DE ARISTAS (EDGE LIST) -------");
            System.out.println("1. Mostrar Grafo Completo");
            System.out.println("2. Agregar Arista con Peso");
            System.out.println("3. Consultar Vecinos de un Nodo");
            System.out.println("4. Eliminar Arista");
            System.out.println("5. Eliminar Nodo");
            System.out.println("6. Salir");
            System.out.println("------------------------------------------------");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    grafo.showGraph();
                    break;
                case 2:
                    System.out.print("Origen: ");
                    String u = scanner.nextLine();
                    System.out.print("Destino: ");
                    String v = scanner.nextLine();
                    System.out.print("Peso (>0): ");
                    try {
                        double w = Double.parseDouble(scanner.nextLine());
                        grafo.addEdge(u, v, w);
                    } catch (NumberFormatException e) {
                        System.out.println("Peso invalido.");
                    }
                    break;
                case 3:
                    System.out.print("Nodo a consultar vecinos: ");
                    String nodeKey = scanner.nextLine();
                    System.out.println("Vecinos de " + nodeKey + ": " + grafo.getNeighbors(nodeKey));
                    break;
                case 4:
                    System.out.print("Origen de arista a borrar: ");
                    String o = scanner.nextLine();
                    System.out.print("Destino de arista a borrar: ");
                    String d = scanner.nextLine();
                    grafo.deleteEdge(o, d);
                    break;
                case 5:
                    System.out.print("Nodo a eliminar: ");
                    String nodeToDel = scanner.nextLine();
                    grafo.deleteNode(nodeToDel);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
    
}
