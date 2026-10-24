import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Kruskal {

    private List<Arista> aristasOrdenadas;
    private List<Arista> aristasAceptadas;
    private List<Arista> aristasRechazadas;
    private List<GrupoConectado> grupos;
    private List<String> historial;

    public Kruskal() {

        // creo las listas donde guardo las decisiones y los resultados
        aristasOrdenadas = new ArrayList<>();
        aristasAceptadas = new ArrayList<>();
        aristasRechazadas = new ArrayList<>();
        grupos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public List<Arista> ejecutar(Grafo grafo) {

        // verifico que el grafo no sea dirigido
        if (grafo.esDirigido()) {
            throw new IllegalArgumentException(
                    "kruskal necesita un grafo no dirigido"
            );
        }

        // limpio los resultados anteriores
        aristasOrdenadas.clear();
        aristasAceptadas.clear();
        aristasRechazadas.clear();
        grupos.clear();
        historial.clear();

        // copio las aristas para no cambiar el orden original del grafo
        aristasOrdenadas.addAll(grafo.getAristas());

        // ordeno las aristas de menor a mayor peso
        aristasOrdenadas.sort(
                Comparator.comparingDouble(Arista::getPeso)
        );

        historial.add(
                "ordene las aristas de menor a mayor peso"
        );

        // creo un grupo independiente para cada nodo
        for (Nodo nodo : grafo.getNodos()) {

            grupos.add(new GrupoConectado(nodo));

            historial.add(
                    "creo un grupo independiente para " + nodo
            );
        }

        // calculo cuantas aristas necesito para conectar todos los nodos
        int cantidadNecesaria = grafo.getNodos().size() - 1;

        // examino las aristas mientras necesite conexiones
        for (Arista arista : aristasOrdenadas) {

            // termino si ya conecte todos los nodos
            if (aristasAceptadas.size() == cantidadNecesaria) {
                break;
            }

            // obtengo los extremos de la arista actual
            Nodo nodoA = arista.getNodoA();
            Nodo nodoB = arista.getNodoB();

            // busco el grupo al que pertenece cada extremo
            GrupoConectado grupoA = buscarGrupo(nodoA);
            GrupoConectado grupoB = buscarGrupo(nodoB);

            // verifico que los extremos pertenezcan a grupos diferentes
            if (grupoA != grupoB) {

                // acepto la arista porque no forma un ciclo
                aristasAceptadas.add(arista);

                historial.add(
                        "acepto " + arista
                                + " porque conecta dos grupos diferentes"
                );

                // uno los grupos en el primero
                grupoA.unir(grupoB);

                // elimino el grupo que ya incorpore al otro
                grupos.remove(grupoB);

                historial.add(
                        "uno los grupos y ahora tengo "
                                + grupos.size() + " grupos"
                );

            } else {

                // rechazo la arista porque sus extremos ya estan conectados
                aristasRechazadas.add(arista);

                historial.add(
                        "rechazo " + arista
                                + " porque formaria un ciclo"
                );
            }
        }

        // verifico si consegui conectar todos los nodos
        if (grafo.getNodos().isEmpty()) {

            historial.add("el grafo esta vacio");

        } else if (aristasAceptadas.size() == cantidadNecesaria) {

            historial.add(
                    "termine: todos los nodos estan conectados"
            );

        } else {

            historial.add(
                    "termine: el grafo esta desconectado"
            );
        }

        return aristasAceptadas;
    }

    private GrupoConectado buscarGrupo(Nodo nodo) {

        // recorro los grupos hasta encontrar el que contiene el nodo
        for (GrupoConectado grupo : grupos) {

            if (grupo.contiene(nodo)) {
                return grupo;
            }
        }

        // devuelvo null si no encuentro el nodo
        return null;
    }

    public double calcularPesoTotal() {

        // comienzo la suma desde cero
        double total = 0;

        // sumo los pesos de las aristas aceptadas
        for (Arista arista : aristasAceptadas) {
            total += arista.getPeso();
        }

        return total;
    }

    public boolean esArbolExpansionCompleto(Grafo grafo) {

        // verifico si todas las conexiones necesarias fueron seleccionadas
        return !grafo.getNodos().isEmpty()
                && aristasAceptadas.size()
                == grafo.getNodos().size() - 1;
    }

    public List<Arista> getAristasOrdenadas() {
        return aristasOrdenadas;
    }

    public List<Arista> getAristasAceptadas() {
        return aristasAceptadas;
    }

    public List<Arista> getAristasRechazadas() {
        return aristasRechazadas;
    }

    public List<GrupoConectado> getGrupos() {
        return grupos;
    }

    public List<String> getHistorial() {
        return historial;
    }
}