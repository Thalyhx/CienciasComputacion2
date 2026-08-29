package pseudoaleatorio;

/**
 *
 * @author julia
 */
public class PseudoAleatorio {
      public static void main(String[] args) {

        
        // creamos el arreglo original
        // 

        int[] arreglo = {1, 2, 3, 4};


        
        // Generamos las 24 permutaciones
        

        Permutacion permutacion = new Permutacion();

        permutacion.generar(arreglo, 0);


        
        // creamos contador de apariciones
        

        int[] cantidad = new int[24];


        // aqui estan las iteraciones

        for (int paso = 0; paso < 96; paso++) {

            // Obtener índice pseudoaleatorio
            int indice = Congruencia.siguiente(paso);

            // Contar la aparición
            cantidad[indice]++;

            // Obtener la permutación correspondiente
            int[] resultado =
                    permutacion.obtenerPermutacion(indice);


            
            // identificamos el ciclo
            

            int ciclo = paso / 24;


            System.out.print(
                    "Ciclo " + (ciclo + 1)
                    + " | Paso " + paso
                    + " | Indice " + indice
                    + " | Permutacion: "
            );


            
            // imprimimos la permutación
            

            for (int i = 0; i < resultado.length; i++) {

                System.out.print(resultado[i] + " ");
            }

            System.out.println();
        }


        
        // mostramos la cantidad de apariciones
        

        System.out.println();
        System.out.println(
                "----- CANTIDAD DE APARICIONES -----"
        );


        for (int i = 0; i < cantidad.length; i++) {

            System.out.println(
                    "Indice " + i
                    + ": "
                    + cantidad[i]
                    + " veces"
            );
        }
    }
}
