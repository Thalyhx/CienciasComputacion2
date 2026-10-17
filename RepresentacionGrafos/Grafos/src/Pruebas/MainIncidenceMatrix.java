/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import grafos.representaciones.IncidenceMatrixGraph;
import java.util.Scanner;

/**
 *
 * @author natha
 */
public class MainIncidenceMatrix {
    
    public static void main(String[] args) {
        IncidenceMatrixGraph grafo = new IncidenceMatrixGraph(false);
        Scanner scanner = new Scanner(System.in);

        // Prueba inicial basada en el script de referencia
        System.out.println("Cargando grafo inicial...");
        grafo.addEdge("A", "B", 1.0);
        grafo.addEdge("B", "C", 1.0);
        grafo.addEdge("C", "D", 1.0);
        
        grafo.showGraph();

        int opcion = 0;
        do {
            System.out.println("\n------- MENU MATRIZ DE INCIDENCIA -------");
            System.out.println("1. Mostrar Matriz y Estructura");
            System.out.println("2. Insertar Arista");
            System.out.println("3. Actualizar Peso de Arista");
            System.out.println("4. Consultar Vecinos de un Nodo");
            System.out.println("5. Eliminar Arista");
            System.out.println("6. Salir");
            System.out.println("-----------------------------------------");
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
                    System.out.print("Nodo 1: ");
                    String n1 = scanner.nextLine();
                    System.out.print("Nodo 2: ");
                    String n2 = scanner.nextLine();
                    System.out.print("Peso: ");
                    double w = Double.parseDouble(scanner.nextLine());
                    grafo.addEdge(n1, n2, w);
                    break;
                case 3:
                    System.out.print("Nodo Origen: ");
                    String s = scanner.nextLine();
                    System.out.print("Nodo Destino: ");
                    String e = scanner.nextLine();
                    System.out.print("Nuevo Peso: ");
                    double nw = Double.parseDouble(scanner.nextLine());
                    grafo.updateWeight(s, e, nw);
                    break;
                case 4:
                    System.out.print("Nodo a consultar: ");
                    String node = scanner.nextLine();
                    System.out.println("Vecinos: " + grafo.getNeighbors(node));
                    break;
                case 5:
                    System.out.print("Nodo Origen a borrar: ");
                    String os = scanner.nextLine();
                    System.out.print("Nodo Destino a borrar: ");
                    String ed = scanner.nextLine();
                    grafo.deleteEdge(os, ed);
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
