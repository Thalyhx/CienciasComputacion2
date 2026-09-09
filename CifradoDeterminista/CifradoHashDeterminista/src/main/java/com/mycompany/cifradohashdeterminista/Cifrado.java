/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cifradohashdeterminista;

/**
 *
 * @author natha
 */

public class Cifrado {
    
    /**
     * 
     * @param charL         Carácter de la mitad izquierda
     * @param charR         Carácter de la mitad derecha
     * @param hashAcumulado Estado del hash de la iteración anterior
     * @return              Nuevo estado del hash intermedio
     */
    public static int procesarBloque(char charL, char charR, int hashAcumulado) {
        
        int asciiL = (int) charL;
        int asciiR = (int) charR;
        
        // desplazando la izquierda 8 bits y sumando la derecha con un OR bit a bit
        int entradaOriginal = (asciiL << 8) | asciiR;
        System.out.printf("  -> 1. ASCII Original    : %04X%n", entradaOriginal);
         
        int subclave = 0x4B; 
        int feistelL = asciiR;
        //  RED DE FEISTEL
        // se cifra la mitad derecha y la izq pasa  a ser la derecha original
        int feistelR = asciiL ^ ((asciiR + subclave) & 0xFF); 
        
        int salidaFeistel = (feistelL << 8) | feistelR;
        System.out.printf("  -> 2. Salida Feistel    : %04X%n", salidaFeistel);
        
        // Se desplazan 5 posiciones a la izquierda y se reinsertan los bits desbordados a la derecha
        int rotado = ((salidaFeistel << 5) | (salidaFeistel >>> 11)) & 0xFFFF;
        System.out.printf("  -> 3. Tras Rotacion     : %04X%n", rotado);
        
        // xor del texto procesado contra el texto original
        int bloqueIrreversible = rotado ^ entradaOriginal;
        System.out.printf("  -> 4. XOR Irreversible  : %04X%n", bloqueIrreversible);
        

        // Desplaza el estado anterior y le aplica XOR con el bloque actual
        int nuevoHash = (hashAcumulado << 3) ^ bloqueIrreversible;
        System.out.printf("  -> Hash Intermedio      : %08X%n", nuevoHash);
        System.out.println("------------------------------------------------");
        
        return nuevoHash;
    }
}
