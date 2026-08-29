package pseudoaleatorio;
public class Permutacion {

    private int[][] permutaciones;
    private int contador;

    public Permutacion() {
        permutaciones = new int[24][4];
        contador = 0;
    }

    public void generar(int[] arreglo, int posicion) {

        // aqui puse el caso base donde ya se completo una permutacion
        if (posicion == arreglo.length) {

            for (int i = 0; i < arreglo.length; i++) {
                permutaciones[contador][i] = arreglo[i];
            }

            contador++;
            return;
        }

        // aqui probamos diferentes elementos en la posición actual
        for (int i = posicion; i < arreglo.length; i++) {

            
            int temporal = arreglo[posicion];
            arreglo[posicion] = arreglo[i];
            arreglo[i] = temporal;

            // continuamo con la siguiente posición
            generar(arreglo, posicion + 1);

            // aqui es importante deshacer el cambio
            temporal = arreglo[posicion];
            arreglo[posicion] = arreglo[i];
            arreglo[i] = temporal;
        }
    }

    public int[] obtenerPermutacion(int indice) {
        return permutaciones[indice];
    }
}
