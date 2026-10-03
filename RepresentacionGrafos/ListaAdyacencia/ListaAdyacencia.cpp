#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

struct Arista {
    char destino;
    double peso;
};

class Grafo {
private:
    unordered_map<char, vector<Arista>> adyacencias;

public:

    // --- CREATE node method ---
    bool crearVertice(char v) {
        // if v dont already exist
        if (adyacencias.find(v) == adyacencias.end()) {
            adyacencias[v] = vector<Arista>();
            return true;
        }
        return false;
    }

    // --- UPDATE relation method ---
    bool actualizarPeso(char u, char v, double nuevoPeso) {

        // if u and v dont exist
        if (adyacencias.find(u) == adyacencias.end() || adyacencias.find(v) == adyacencias.end()) {
            return false;
        }

        bool aristaEncontrada = false;

        // Update  (u -> v)
        for (auto& aristaU : adyacencias[u]) {
            if (aristaU.destino == v) {
                aristaU.peso = nuevoPeso;
                aristaEncontrada = true;
                break;
            }
        }

        // if relation dont exist
        if (!aristaEncontrada) return false;

        // if relation isnt a ribbon
        if (u != v) {
            // Update (v -> u)
            for (auto& aristaV : adyacencias[v]) {
                if (aristaV.destino == u) {
                    aristaV.peso = nuevoPeso;
                    break;
                }
            }
        }

        cout << "> Peso actualizado \n";
        return true;
    }

    // --- ADD relation method ---
    void agregarArista(char u, char v, double peso) {

        // if u or v dont exist
        crearVertice(u);
        crearVertice(v);

        // if arista already exist
        for (auto& aristaU : adyacencias[u]) {
            if (aristaU.destino == v) {
                cout << "\n[!] La arista entre " << u << " y " << v
                     << " ya existe con un peso de " << aristaU.peso << ".\n";
                cout << "¿actualizar el peso a " << peso << "? (s/n): ";

                char respuesta;
                cin >> respuesta;

                if (respuesta == 's' || respuesta == 'S') {
                    // update
                    actualizarPeso(u, v, peso);
                    cout << "> Peso actualizado exitosamente \n";
                } else {
                    cout << "> Se mantuvo el peso original \n";
                }

                return;
            }
        }

        // if relation dont exist
        adyacencias[u].push_back({v, peso});

        // if relation isnt a ribbon
        if (u != v) {
            adyacencias[v].push_back({u, peso});
        }

        cout << "\n> Arista entre " << u << " y " << v << " agregada \n";
    }

    // --- DELETE relation method ---
    bool eliminarArista(char u, char v) {
        // if u or v dont exist
        if (adyacencias.find(u) == adyacencias.end() || adyacencias.find(v) == adyacencias.end()) {
            return false;
        }

        bool eliminada = false;

        // Delete (u -> v)
        for (auto it = adyacencias[u].begin(); it != adyacencias[u].end(); ) {
            if (it->destino == v) {
                it = adyacencias[u].erase(it);
                eliminada = true;
                break;
            } else {
                ++it;
            }
        }

        // if the relation dont exist
        if (!eliminada) return false;

        // if relation isnt a ribbon
        if (u != v) {
            // Delete (v -> u)
            for (auto it = adyacencias[v].begin(); it != adyacencias[v].end(); ) {
                if (it->destino == u) {
                    adyacencias[v].erase(it);
                    break;
                } else {
                    ++it;
                }
            }
        }

        cout << "> Arista entre " << u << " y " << v << " eliminada \n";
        return true;
    }

    // --- DELETE node method ---
    bool eliminarVertice(char u) {
        // if node u dont exist
        if (adyacencias.find(u) == adyacencias.end()) {
            cout << "[!] El vertice " << u << " no existe \n";
            return false;
        }

        // Clean relations
        for (const auto& arista : adyacencias[u]) {
            char vecino = arista.destino;

            // if the relation isnt a ribbon
            if (vecino != u) {
                // search u in destination node and erase it
                for (auto it = adyacencias[vecino].begin(); it != adyacencias[vecino].end(); ) {
                    if (it->destino == u) {
                        it = adyacencias[vecino].erase(it);
                        break;
                    } else {
                        ++it;
                    }
                }
            }
        }

        // delete u of adyacencias
        adyacencias.erase(u);

        cout << "> Vertice " << u << " y relaciones eliminadas \n";
        return true;
    }

    void mostrarGrafo() {
        cout << "\n=== LISTA DE ADYACENCIAS ===\n";
        for (const auto& par : adyacencias) {
            cout << "Vertice [" << par.first << "]";

            if (par.second.empty()) {
                cout << " ---> (Sin relaciones)";
            } else {
                for (const auto& arista : par.second) {
                    cout << " ---> [Dest: " << arista.destino << " | Peso: " << arista.peso << "]";
                }
            }
            cout << "\n";
        }
        cout << "=========================================\n";
    }
};

int main() {
Grafo grafo;

    // initial example
    cout << "datos ejemplo iniciales...\n";
    grafo.agregarArista('A', 'B', 3.0);
    grafo.agregarArista('A', 'C', 5.0);
    grafo.crearVertice('Z');
    cout << "Datos ejemplo cargados ...\n\n";

    int opcion;
    char u, v;
    double peso;

    // Menu loop
    do {
        cout << "\n------- MENU GRAFO -------";
        cout << "\n1. Ver Lista de Adyacencias";
        cout << "\n2. Crear un Vertice ";
        cout << "\n3. Agregar/Actualizar Arista";
        cout << "\n4. Eliminar una Arista";
        cout << "\n5. Eliminar un Vertice Completo";
        cout << "\n6. Salir";
        cout << "\n--------------------------";
        cout << "\nSeleccione una opcion: ";
        cin >> opcion;

        switch (opcion) {
            case 1:
                grafo.mostrarGrafo();
                break;

            case 2:
                cout << "Ingrese el caracter del nuevo vertice: ";
                cin >> u;
                if (grafo.crearVertice(u)) {
                    cout << "> Vertice '" << u << "' creado exitosamente.\n";
                } else {
                    cout << "[!] El vertice '" << u << "' ya existe en el grafo.\n";
                }
                break;

            case 3:
                cout << "Ingrese el vertice de ORIGEN: ";
                cin >> u;
                cout << "Ingrese el vertice de DESTINO: ";
                cin >> v;
                cout << "Ingrese el PESO de la arista: ";
                cin >> peso;
                grafo.agregarArista(u, v, peso);
                break;

            case 4:
                cout << "Ingrese el vertice de ORIGEN: ";
                cin >> u;
                cout << "Ingrese el vertice de DESTINO: ";
                cin >> v;
                if (!grafo.eliminarArista(u, v)) {
                    cout << "[!] No se pudo eliminar. Verifique que los vertices y la arista existan.\n";
                }
                break;

            case 5:
                cout << "Ingrese el vertice a ELIMINAR por completo: ";
                cin >> u;
                grafo.eliminarVertice(u);
                break;

            case 6:
                cout << "Saliendo del programa. ¡Hasta luego!\n";
                break;

            default:
                cout << "[!] Opcion invalida. Intente de nuevo.\n";
        }
    } while (opcion != 6);

    return 0;
}