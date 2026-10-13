/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Nath
 */
public class AdjacencyListGraph extends Graph{
    
    private Map<String, List<Edge>> adyacencias;
    
    public AdjacencyListGraph() {
        super();
        this.adyacencias = new HashMap<>();
    }

    public AdjacencyListGraph(boolean isDirected) {
        super(isDirected);
        this.adyacencias = new HashMap<>();
    }
    
    @Override
    public boolean addNode(String key) {
        if (key == null) return false;
        

        if (!adyacencias.containsKey(key)) {
            adyacencias.put(key, new ArrayList<>());
            nodes.put(key, new Node(key));
            return true;
        }
        return false;
    }
    
    @Override
    public boolean updateWeight(String u, String v, double nuevoPeso) {
        // Si u o v no existen
        if (!adyacencias.containsKey(u) || !adyacencias.containsKey(v)) {
            return false;
        }

        boolean aristaEncontrada = false;

        // Actualizar (u -> v)
        for (Edge aristaU : adyacencias.get(u)) {
            if (aristaU.getEnd().getKey().equals(v)) {
                aristaU.setWeight(nuevoPeso);
                aristaEncontrada = true;
                break;
            }
        }

        // Si la relación no existe
        if (!aristaEncontrada) return false;

        // Si no es un lazo (bucle) y el grafo no es dirigido
        if (!isDirected && !u.equals(v)) {
            for (Edge aristaV : adyacencias.get(v)) {
                if (aristaV.getEnd().getKey().equals(u)) {
                    aristaV.setWeight(nuevoPeso);
                    break;
                }
            }
        }

        System.out.println("> Peso actualizado");
        return true;
    }
    
    @Override
    public boolean addEdge(String u, String v, double peso) {
        addNode(u);
        addNode(v);

        Node nodoV = nodes.get(v);

        // Verificar si la arista ya existe en u
        for (Edge aristaU : adyacencias.get(u)) {
            if (aristaU.getEnd().getKey().equals(v)) {
                System.out.println("\n[!] La arista entre " + u + " y " + v
                        + " ya existe con un peso de " + aristaU.getWeight() + ".");
                System.out.print("¿Actualizar el peso a " + peso + "? (s/n): ");

                Scanner scanner = new Scanner(System.in);
                String respuestaStr = scanner.nextLine();
                char respuesta = respuestaStr.isEmpty() ? 'n' : respuestaStr.charAt(0);

                if (respuesta == 's' || respuesta == 'S') {
                    updateWeight(u, v, peso);
                    System.out.println("> Peso actualizado exitosamente");
                } else {
                    System.out.println("> Se mantuvo el peso original");
                }

                return true;
            }
        }

        adyacencias.get(u).add(new Edge(nodes.get(u), nodoV, peso));

        if (!isDirected && !u.equals(v)) {
            adyacencias.get(v).add(new Edge(nodes.get(v), nodes.get(u), peso));
        }

        System.out.println("\n> Arista entre " + u + " y " + v + " agregada");
        return true;
    }
    
    @Override
    public boolean deleteEdge(String u, String v) {
        // Si u o v no existen
        if (!adyacencias.containsKey(u) || !adyacencias.containsKey(v)) {
            return false;
        }

        boolean eliminada = false;

        // Eliminar (u -> v)
        List<Edge> listaU = adyacencias.get(u);
        for (int i = 0; i < listaU.size(); i++) {
            if (listaU.get(i).getEnd().getKey().equals(v)) {
                listaU.remove(i);
                eliminada = true;
                break;
            }
        }

        if (!eliminada) return false;

        // Si no es un lazo y no es dirigido
        if (!isDirected && !u.equals(v)) {
            List<Edge> listaV = adyacencias.get(v);
            for (int i = 0; i < listaV.size(); i++) {
                if (listaV.get(i).getEnd().getKey().equals(u)) {
                    listaV.remove(i);
                    break;
                }
            }
        }

        System.out.println("> Arista entre " + u + " y " + v + " eliminada");
        return true;
    }
    
    @Override
    public boolean deleteNode(String u) {
        // Si el nodo u no existe
        if (!adyacencias.containsKey(u)) {
            System.out.println("[!] El vertice " + u + " no existe");
            return false;
        }

        for (Edge arista : adyacencias.get(u)) {
            String vecino = arista.getEnd().getKey();

            if (!vecino.equals(u)) {
                List<Edge> listaVecino = adyacencias.get(vecino);
                if (listaVecino != null) {
                    listaVecino.removeIf(e -> e.getEnd().getKey().equals(u));
                }
            }
        }

        adyacencias.remove(u);
        nodes.remove(u);

        System.out.println("> Vertice " + u + " y relaciones eliminadas");
        return true;
    }
    
    @Override
    public List<Node> getNeighbors(String nodeKey) {
        List<Node> vecinos = new ArrayList<>();
        if (!adyacencias.containsKey(nodeKey)) {
            return vecinos;
        }

        for (Edge arista : adyacencias.get(nodeKey)) {
            vecinos.add(arista.getEnd());
        }
        return vecinos;
    }
    
    @Override
    public void showGraph() {
        System.out.println("\n--- LISTA DE ADYACENCIAS ---");
        for (Map.Entry<String, List<Edge>> entry : adyacencias.entrySet()) {
            String vertexKey = entry.getKey();
            List<Edge> edges = entry.getValue();

            System.out.print("Vertice [" + vertexKey + "]");

            if (edges.isEmpty()) {
                System.out.print(" ---> (Sin relaciones)");
            } else {
                for (Edge arista : edges) {
                    System.out.print(" ---> [Dest: " + arista.getEnd().getKey() + " | Peso: " + arista.getWeight() + "]");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------");
    }
    
}
