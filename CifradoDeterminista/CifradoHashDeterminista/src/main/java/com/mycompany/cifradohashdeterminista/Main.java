/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cifradohashdeterminista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el texto a cifrar: ");
        String texto = scanner.nextLine();
        
        if (texto.length() % 2 != 0) {
            texto += " ";
        }
        
        System.out.println("\n=== DIVISION DE BLOQUES ANTES DEL CIFRADO ===");
        System.out.println("Texto normalizado: \"" + texto + "\"");
        
        // Muestra la division
        for (int i = 0; i < texto.length(); i += 2) {
            System.out.printf("Bloque %d: [%c%c]%n", (i / 2) + 1, texto.charAt(i), texto.charAt(i + 1));
        }
        
        System.out.println("\n=== PROCESAMIENTO MATEMATICO PASO A PASO ===");
        int hashAcumulado = 0;
        
        // Inyecta cada bloque secuencialmente a la clase Cifrado
        for (int i = 0; i < texto.length(); i += 2) {
            char L = texto.charAt(i);
            char R = texto.charAt(i + 1);
            
            System.out.printf("Procesando Bloque %d [%c%c]:%n", (i / 2) + 1, L, R);
            
            // Llama al algoritmo
            hashAcumulado = Cifrado.procesarBloque(L, R, hashAcumulado);
        }
        
        System.out.println("=== RESULTADO FINAL ===");
        System.out.printf("Hash Definitivo: %08X%n", hashAcumulado);
        
        scanner.close();
    }
}
 

