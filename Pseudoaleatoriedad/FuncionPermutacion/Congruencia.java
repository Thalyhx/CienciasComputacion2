public class Congruencia {

    public static int siguiente(int paso) {

        // aqui identifico el  ciclo actual
        int ciclo = paso / 24;

        // posición dentro del ciclo
        int posicion = paso % 24;

        // congruencia lineal modificada para nuestro ejercicio
        return (17 * posicion + 5 + 7 * ciclo) % 24;
    }
}
