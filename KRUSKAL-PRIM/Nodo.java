import java.util.Objects;

public class Nodo {

    private String nombre;

    public Nodo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object objeto) {

        // verifico si estoy comparando el mismo objeto
        if (this == objeto) {
            return true;
        }

        // verifico si el objeto es nulo o de otra clase
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }

        // obtengo el otro nodo para comparar sus nombres
        Nodo otroNodo = (Nodo) objeto;

        return Objects.equals(nombre, otroNodo.nombre);
    }

    @Override
    public int hashCode() {

        // genero un codigo basado en el nombre del nodo
        return Objects.hash(nombre);
    }
}