/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import grafos.representaciones.AdjacencyListGraph;
import java.util.Scanner;

/**
 *
 * @author natha
 */
public class MainAdjacencyList {
    
        public static void main(String[] args) {
        AdjacencyListGraph grafo = new AdjacencyListGraph(false);
        Scanner scanner = new Scanner(System.in);

        // Datos de ejemplo
        System.out.println("Cargando datos de ejemplo iniciales...");
        grafo.addEdge("A", "B", 3.0);
        grafo.addEdge("A", "C", 5.0);
        grafo.addNode("Z");
        System.out.println("Datos de ejemplo cargados.\n");

        int opcion = 0;
        String u, v;
        double peso;

        do {
            System.out.println("\n------- MENU LISTA DE ADYACENCIA -------");
            System.out.println("1. Ver Lista de Adyacencias");
            System.out.println("2. Crear un Vertice");
            System.out.println("3. Agregar/Actualizar Arista");
            System.out.println("4. Eliminar una Arista");
            System.out.println("5. Eliminar un Vertice Completo");
            System.out.println("6. Salir");
            System.out.println("----------------------------------------");
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
                    System.out.print("Ingrese la clave del nuevo vertice: ");
                    u = scanner.nextLine();
                    if (grafo.addNode(u)) {
                        System.out.println("> Vertice '" + u + "' creado exitosamente.");
                    } else {
                        System.out.println("[!] El vertice '" + u + "' ya existe o es invalido.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el vertice de ORIGEN: ");
                    u = scanner.nextLine();
                    System.out.print("Ingrese el vertice de DESTINO: ");
                    v = scanner.nextLine();
                    System.out.print("Ingrese el PESO de la arista: ");
                    try {
                        peso = Double.parseDouble(scanner.nextLine());
                        grafo.addEdge(u, v, peso);
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Peso invalido.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el vertice de ORIGEN: ");
                    u = scanner.nextLine();
                    System.out.print("Ingrese el vertice de DESTINO: ");
                    v = scanner.nextLine();
                    if (!grafo.deleteEdge(u, v)) {
                        System.out.println("[!] No se pudo eliminar. Verifique que existan.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el vertice a ELIMINAR por completo: ");
                    u = scanner.nextLine();
                    grafo.deleteNode(u);
                    break;

                case 6:
                    System.out.println("Saliendo ");
                    break;

                default:
                    System.out.println(" Opcion invalida");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
