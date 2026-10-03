matriz = []

# guardamos los nodos
nodos = []

# guardamos las aristas
aristas = []


def insertar_arista(nodo1, nodo2):

    # agregamos el nodo si no existe
    if nodo1 not in nodos:
        nodos.append(nodo1)
        matriz.append([0] * len(aristas))

    # agregamos el nodo si no existe
    if nodo2 not in nodos:
        nodos.append(nodo2)
        matriz.append([0] * len(aristas))

    # agregamos la arista
    aristas.append((nodo1, nodo2))

    # agregamos una columna
    for fila in matriz:
        fila.append(0)

    # buscamos los indices
    indice_a = nodos.index(nodo1)
    indice_b = nodos.index(nodo2)

    # marcamos la conexion
    matriz[indice_a][-1] = 1
    matriz[indice_b][-1] = 1


def eliminar_arista(indice):

    # eliminamos la columna
    for fila in matriz:
        del fila[indice]

    # eliminamos la arista
    del aristas[indice]


def modificar_arista(indice, nodo1, nodo2):

    # eliminamos la arista anterior
    eliminar_arista(indice)

    # agregamos la nueva arista
    insertar_arista(nodo1, nodo2)


def mostrar_grafo():

    print("\nNodos:")
    print(nodos)

    print("\nAristas:")
    print(aristas)

    print("\nMatriz:")

    # mostramos cada nodo con su fila
    for i in range(len(matriz)):
        print(nodos[i], matriz[i])


# probamos el programa

insertar_arista("A", "B")
insertar_arista("B", "C")
insertar_arista("C", "D")

print("\nGrafo inicial")
mostrar_grafo()


# modificamos la arista 1
modificar_arista(1, "A", "C")

print("\nDespues de modificar")
mostrar_grafo()


# eliminamos la arista 0
eliminar_arista(0)

print("\nDespues de eliminar")
mostrar_grafo()
