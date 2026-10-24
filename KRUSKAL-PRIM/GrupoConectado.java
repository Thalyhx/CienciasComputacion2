import java.util.List;
import java.util.ArrayList;

public class GrupoConectado {

    private List<Nodo> integrantes;

    public GrupoConectado(Nodo nodoInicial) {

        // creo un grupo que comienza con un solo nodo
        integrantes = new ArrayList<>();
        integrantes.add(nodoInicial);
    }

    public boolean contiene(Nodo nodo) {

        // verifico si el nodo pertenece a este grupo
        return integrantes.contains(nodo);
    }

    public void agregar(Nodo nodo) {

        // agrego el nodo si todavia no pertenece al grupo
        if (!integrantes.contains(nodo)) {
            integrantes.add(nodo);
        }
    }

    public void unir(GrupoConectado otroGrupo) {

        // incorporo los integrantes del otro grupo al mio
        for (Nodo nodo : otroGrupo.integrantes) {
            agregar(nodo);
        }
    }

    public List<Nodo> getIntegrantes() {
        return integrantes;
    }

    @Override
    public String toString() {

        // muestro todos los integrantes del grupo
        return integrantes.toString();
    }
}