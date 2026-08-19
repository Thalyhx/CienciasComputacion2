Main
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolb;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or


public class Main {

    public static void main(String[] args) {
        // Crear árbol B con orden 4 (máximo 3 claves por nodo)
        Arbol arbol = new Arbol(4);
        
        System.out.println("=== PRUEBA DE INSERCIÓN ===");
        System.out.println("Insertando: 10, 20, 5, 6, 12, 30, 7, 17, 1, 15, 19");
        
        int[] valores = {10, 20, 5, 6, 12, 30, 7, 17, 1, 15, 19};
        for (int v : valores) {
            arbol.insertar(v);
            System.out.println("Después de insertar " + v + ":");
            arbol.imprimir();
            System.out.println("---");
        }

        System.out.println("\n=== ÁRBOL FINAL DESPUÉS DE INSERCIONES ===");
        arbol.imprimir();

        System.out.println("\n=== PRUEBA DE ELIMINACIÓN ===");
        
        System.out.println("Eliminando 6:");
        arbol.eliminar(6);
        arbol.imprimir();
        System.out.println("---");
        
        System.out.println("Eliminando 12:");
        arbol.eliminar(12);
        arbol.imprimir();
        System.out.println("---");
        
        System.out.println("Eliminando 7:");
        arbol.eliminar(7);
        arbol.imprimir();
        System.out.println("---");
        
        System.out.println("Eliminando 20:");
        arbol.eliminar(20);
        arbol.imprimir();
        System.out.println("---");
        
        System.out.println("\n=== ÁRBOL FINAL ===");
        arbol.imprimir();
    }
}
