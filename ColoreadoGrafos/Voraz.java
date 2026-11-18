
public class Voraz {

    // creamos el metodo para colorear nuestro grafo
    public static int[] colorear(int[][] matriz) {

        // obtenemos la cantidad de vertices
        int cantidad = matriz.length;

        // creamos un arreglo para guardar los colores
        int[] colores = new int[cantidad];

        // inicialmente todos los vertices quedan sin color
        for (int i = 0; i < cantidad; i++) {
            colores[i] = -1;
        }

        // recorremos los vertices en su orden original
        for (int i = 0; i < cantidad; i++) {

            // comenzamos probando con el primer color
            int color = 0;

            // verificamos los vecinos del vertice actual
            for (int j = 0; j < cantidad; j++) {

                // comprobamos si existe una conexion
                // y si el vecino ya tiene un color
                if (matriz[i][j] == 1 && colores[j] != -1) {

                    // verificamos si el color esta ocupado
                    if (colores[j] == color) {

                        // si esta ocupado, probamos el siguiente color
                        color++;

                        // volvemos a revisar los vecinos desde el inicio
                        j = -1;
                    }
                }
            }

            // asignamos el primer color disponible
            colores[i] = color;
        }

        // retornamos los colores de todos los vertices
        return colores;
    }
}
