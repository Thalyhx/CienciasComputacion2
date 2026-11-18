
public class WelshPowell {

    // creamos el metodo para colorear nuestro grafo
    public static int[] colorear(int[][] matriz) {

        // obtenemos la cantidad de vertices
        int cantidad = matriz.length;

        // creamos un arreglo para guardar los grados
        int[] grados = new int[cantidad];

        // creamos un arreglo para guardar el orden de los vertices
        int[] orden = new int[cantidad];

        // creamos un arreglo para guardar los colores
        int[] colores = new int[cantidad];

        // calculamos el grado de cada vertice
        for (int i = 0; i < cantidad; i++) {

            // comenzamos el grado en cero
            grados[i] = 0;

            // contamos las conexiones del vertice actual
            for (int j = 0; j < cantidad; j++) {

                if (matriz[i][j] == 1) {
                    grados[i]++;
                }
            }

            // guardamos la posicion original de cada vertice
            orden[i] = i;

            // inicialmente dejamos todos los vertices sin color
            colores[i] = -1;
        }

        // ordenamos los vertices de mayor a menor grado
        // utilizamos el metodo burbuja para hacerlo
        for (int i = 0; i < cantidad - 1; i++) {

            for (int j = 0; j < cantidad - 1 - i; j++) {

                // comparamos los grados de dos vertices
                if (grados[orden[j]] < grados[orden[j + 1]]) {

                    // intercambiamos sus posiciones
                    int temporal = orden[j];

                    orden[j] = orden[j + 1];

                    orden[j + 1] = temporal;
                }
            }
        }

        // mostramos el orden en el que vamos a colorear
        System.out.println("\nORDEN DE WELSH-POWELL");

        for (int i = 0; i < cantidad; i++) {

            System.out.println(
                    "Vertice " + Grafo.vertices[orden[i]] +
                            " - Grado: " + grados[orden[i]]
            );
        }

        // comenzamos con el primer color
        int color = 0;

        // recorremos los vertices segun el orden obtenido
        for (int i = 0; i < cantidad; i++) {

            // seleccionamos el vertice actual
            int vertice = orden[i];

            // verificamos si todavia no tiene color
            if (colores[vertice] == -1) {

                // asignamos el color actual
                colores[vertice] = color;

                // revisamos los demas vertices
                for (int j = 0; j < cantidad; j++) {

                    // seleccionamos otro vertice
                    int otroVertice = orden[j];

                    // verificamos si todavia no tiene color
                    if (colores[otroVertice] == -1) {

                        // inicialmente permitimos utilizar el color
                        boolean puedeColorear = true;

                        // revisamos los vecinos del vertice
                        for (int k = 0; k < cantidad; k++) {

                            // comprobamos si existe una conexion
                            if (matriz[otroVertice][k] == 1) {

                                // verificamos si el vecino tiene el color actual
                                if (colores[k] == color) {

                                    // si encontramos una conexion,
                                    // no podemos utilizar el mismo color
                                    puedeColorear = false;
                                }
                            }
                        }

                        // si no hay conflictos, asignamos el color
                        if (puedeColorear) {
                            colores[otroVertice] = color;
                        }
                    }
                }

                // pasamos al siguiente color
                color++;
            }
        }

        // retornamos el arreglo con los colores asignados
        return colores;
    }
}
