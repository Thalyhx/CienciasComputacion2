Arbol /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolb;

public class Arbol {
    int orden;
    Nodo raiz;

    public Arbol(int orden) {
        if (orden < 3) {
            throw new IllegalArgumentException("El orden debe ser >= 3");
        }
        this.orden = orden;
        this.raiz = new Nodo(true);
    }

    private int maxClaves() {
        return orden - 1;
    }

    private int minClaves() {
        return (orden - 1) / 2;
    }

    public int buscarClave(Nodo nodo, int clave) {
        int valorMedio = nodo.claves.size() / 2;
        if (nodo.esHoja) {
            // Caso 1: Solo hay dos claves (búsqueda iterativa O(n))
            if (nodo.claves.size() == 2) {
                return buscarIndice(nodo, clave);
            } else {
            // Caso 2: Hay 3 o más claves (búsqueda binaria O(log n))
                if (clave == nodo.claves.get(valorMedio)) {
                    return valorMedio;
                } else if (clave > nodo.claves.get(valorMedio)) {
                    for (int i = valorMedio; i < nodo.claves.size(); i++) {
                        if (clave == nodo.claves.get(i)) {
                            return i;
                        }
                    } // NO estoy seguro de si así funciona la búsqueda binaria.
                } else {
                    for (int i = 0; i < valorMedio; i++) {
                        if (clave == nodo.claves.get(i)) {
                            return i;
                        }
                    }
                }
            }
        } else {
            for (int j = 0; j < nodo.claves.size(); j++) {
                if (clave == nodo.claves.get(j)) {
                    return j;
                } else if(clave < nodo.claves.get(j)){
                    return buscarClave(nodo.hijos.get(j), clave);
                }
            }
        }
        return 0;
    }

    public void insertar(int clave) {
        boolean raizSobrepasada = insertarNoLleno(raiz, clave);
        if (raizSobrepasada) {
            Nodo nuevaRaiz = new Nodo(false);
            nuevaRaiz.hijos.add(raiz);
            promoverClave(nuevaRaiz, 0);
            raiz = nuevaRaiz;
        }
    }

    private boolean insertarNoLleno(Nodo nodo, int clave){
        int comparador = nodo.claves.size() - 1;
        //Tiene ramificaciones?
        if(nodo.esHoja){
            nodo.claves.add(0); //Agrega un valor temporal donde irá la clave (es como un placeholder)
            while(comparador >= 0 && clave < nodo.claves.get(comparador)){
                nodo.claves.set(comparador + 1, nodo.claves.get(comparador)); //Se corre una clave, a la derecha
                comparador --;
            }
            nodo.claves.set(comparador + 1, clave); //Se agrega la clave en la posición que le corresponde
            return nodo.claves.size() > maxClaves();    
        } else {
            while (comparador >= 0 && clave < nodo.claves.get(comparador)) {
                comparador--;
            }
            comparador++; //Índice por el que se baja

            boolean hijoSobrepasado = insertarNoLleno(nodo.hijos.get(comparador), clave);
            if (hijoSobrepasado) {
                promoverClave(nodo, comparador);
            }
            return nodo.claves.size() > maxClaves();
        }
    }

    private void promoverClave(Nodo padre, int indice) {
        Nodo hijoLleno = padre.hijos.get(indice); //representa el nodo que se llenó y que se va a dividir
        int clavesNodoLleno = hijoLleno.claves.size();
        int mitad = (clavesNodoLleno % 2 == 0) ? (clavesNodoLleno / 2) - 1: clavesNodoLleno / 2;
        int claveMedia = hijoLleno.claves.get(mitad);

        // Nodo hermano derecho
        Nodo nuevoNodoDerecho = new Nodo(hijoLleno.esHoja);
        nuevoNodoDerecho.claves.addAll(hijoLleno.claves.subList(mitad + 1, hijoLleno.claves.size()));
        if (!hijoLleno.esHoja) {
            nuevoNodoDerecho.hijos.addAll(hijoLleno.hijos.subList(mitad + 1, hijoLleno.hijos.size()));
        }

        // Nodo hermano izquierdo
        hijoLleno.claves.subList(mitad, hijoLleno.claves.size()).clear();
        if (!hijoLleno.esHoja) {
            hijoLleno.hijos.subList(mitad + 1, hijoLleno.hijos.size()).clear();
        }

        // La clave media sube al padre, en la posición "indice"
        padre.claves.add(indice, claveMedia);
        // El nuevo hijo (mitad derecha) se ubica justo después: "indice + 1"
        padre.hijos.add(indice + 1, nuevoNodoDerecho);
    }

    public void eliminar(int clave) {
        algoritmoEliminacion(raiz, clave);
        if (raiz.claves.isEmpty() && !raiz.esHoja) {
            raiz = raiz.hijos.getFirst();
        }
    }

    private void algoritmoEliminacion(Nodo nodo, int clave) {
        int idx = buscarIndice(nodo, clave);

        // Caso 1: la clave está en ESTE nodo
        if (idx < nodo.claves.size() && nodo.claves.get(idx) == clave) {
            if (nodo.esHoja) {
                nodo.claves.remove(idx);
            } else {
                eliminarDeNodoInterno(nodo, idx);
            }
        } else {
            // Caso 2: la clave no está en el nodo, hay que bajar a un hijo
            if (nodo.esHoja) {
                return; // la clave no existe en el árbol
            }

            boolean esUltimoHijo = (idx == nodo.claves.size());
            Nodo hijo = nodo.hijos.get(idx);

            if (hijo.claves.size() == minClaves()) {
                prestamoNodosHermanos(nodo, idx);
            }

            if (esUltimoHijo && idx > nodo.claves.size()) {
                algoritmoEliminacion(nodo.hijos.get(idx - 1), clave);
            } else {
                algoritmoEliminacion(nodo.hijos.get(idx), clave);
            }
        }
    }

    // Devuelve el primer índice i tal que claves[i] >= clave
    private int buscarIndice(Nodo nodo, int clave) {
        int indice = 0;
        while (indice < nodo.claves.size() && nodo.claves.get(indice) < clave) {
            indice++;
        }
        return indice;
    }

    private void eliminarDeNodoInterno(Nodo nodo, int idx) {
        int clave = nodo.claves.get(idx);

        // Sub-caso A: el hijo IZQUIERDO de la clave tiene claves de sobra.
        // Reemplaza la clave por su PREDECESOR (el máximo del subárbol
        // izquierdo) y elimina ese predecesor de ese subárbol.
        if (nodo.hijos.get(idx).claves.size() > minClaves()) {
            int pred = obtenerPredecesor(nodo, idx);
            nodo.claves.set(idx, pred);
            algoritmoEliminacion(nodo.hijos.get(idx), pred);

            // Sub-caso B: el hijo DERECHO tiene claves de sobra.
            // igual, pero con el SUCESOR (el mínimo del subárbol derecho).
        } else if (nodo.hijos.get(idx + 1).claves.size() > minClaves()) {
            int suc = obtenerSucesor(nodo, idx);
            nodo.claves.set(idx, suc);
            algoritmoEliminacion(nodo.hijos.get(idx + 1), suc);

            // Sub-caso C: ninguno de los dos hijos tiene de sobra.
            // Se fusionan ambos hijos en uno solo (con la clave en el medio)
            // y se elimina la clave de ese nodo fusionado.
        } else {
            fusionar(nodo, idx);
            algoritmoEliminacion(nodo.hijos.get(idx), clave);
        }
    }

    private int obtenerPredecesor(Nodo nodo, int idx) {
        Nodo nodoActual = nodo.hijos.get(idx);
        while (!nodoActual.esHoja) {
            nodoActual = nodoActual.hijos.get(nodoActual.hijos.size() - 1);
        }
        return nodoActual.claves.get(nodoActual.claves.size() - 1);
    }

    private int obtenerSucesor(Nodo nodo, int idx) {
        Nodo nodoActual = nodo.hijos.get(idx + 1);
        while (!nodoActual.esHoja) {
            nodoActual = nodoActual.hijos.getFirst();
        }
        return nodoActual.claves.getFirst();
    }

    // Se asegura de que nodo.hijos.get(idx) tenga al menos minClaves()+1
    // claves, pidiendo prestado a un hermano o fusionando con uno.
    private void prestamoNodosHermanos(Nodo nodo, int idx) {
        if (idx != 0 && nodo.hijos.get(idx - 1).claves.size() > minClaves()) {
            tomarPrestadoDeAnterior(nodo, idx);
        } else if (idx != nodo.claves.size() && nodo.hijos.get(idx + 1).claves.size() > minClaves()) {
            tomarPrestadoDeSiguiente(nodo, idx);
        } else {
            if (idx != nodo.claves.size()) {
                fusionar(nodo, idx);
            } else {
                fusionar(nodo, idx - 1);
            }
        }
    }

    // El hijo[idx] pide prestada una clave al hermano izquierdo hijo[idx-1],
    // "rotando" a través del padre: la clave del padre baja al hijo, y la
    // última clave del hermano sube a ocupar el lugar del padre.
    private void tomarPrestadoDeAnterior(Nodo nodo, int idx) {
        Nodo hijo = nodo.hijos.get(idx);
        Nodo hermano = nodo.hijos.get(idx - 1);

        hijo.claves.add(0, nodo.claves.get(idx - 1));
        if (!hijo.esHoja) {
            hijo.hijos.add(0, hermano.hijos.remove(hermano.hijos.size() - 1));
        }

        nodo.claves.set(idx - 1, hermano.claves.remove(hermano.claves.size() - 1));
    }

    // igual al anterior, pero pidiendo prestado al hermano derecho.
    private void tomarPrestadoDeSiguiente(Nodo nodo, int idx) {
        Nodo hijo = nodo.hijos.get(idx);
        Nodo hermano = nodo.hijos.get(idx + 1);

        hijo.claves.add(nodo.claves.get(idx));
        if (!hijo.esHoja) {
            hijo.hijos.add(hermano.hijos.removeFirst());
        }

        nodo.claves.set(idx, hermano.claves.removeFirst());
    }

    // Fusiona nodo.hijos.get(idx), la clave nodo.claves.get(idx), y
    // nodo.hijos.get(idx+1) en un solo nodo. Este nodo fusionado reemplaza
    // a ambos hijos y absorbe la clave que estaba entre ellos en el padre.
    private void fusionar(Nodo nodo, int idx) {
        Nodo hijo = nodo.hijos.get(idx);
        Nodo hermano = nodo.hijos.get(idx + 1);

        hijo.claves.add(nodo.claves.get(idx));
        hijo.claves.addAll(hermano.claves);
        if (!hijo.esHoja) {
            hijo.hijos.addAll(hermano.hijos);
        }

        nodo.claves.remove(idx);
        nodo.hijos.remove(idx + 1);
    }

    public void imprimir() {
    System.out.println("=== ARBOL B ===");
    imprimirSimple(raiz, 0);
    System.out.println("=== FIN ===");
}

    private void imprimirSimple(Nodo nodo, int nivel) {
        // Mostrar el nodo con su nivel
        String espacios = "";
        for (int i = 0; i < nivel; i++) {
            espacios = espacios + "    ";
        }
        System.out.println(espacios + "-- " + nodo.claves);

        // Recorrer hijos
        for (Nodo hijo : nodo.hijos) {
            imprimirSimple(hijo, nivel + 1);
        }
    }
}
