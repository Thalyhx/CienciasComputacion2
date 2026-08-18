Nodo
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolb;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    List<Integer> claves;
    List<Nodo> hijos;
    boolean esHoja;

    public Nodo(boolean esHoja) {
        this.esHoja = esHoja;
        this.claves = new ArrayList<>();
        this.hijos = new ArrayList<>();
    }
}
