- Ingresar dato (relación)

CREAR:

- Revisar si los nodos de dicha relación existen en la matriz.
    - Si existe, guardar relación en la matriz e imprimirla en consola.
    - Si no existe: Crear nodos, y guardar relación en la matriz e imprimirla en consola.

ELIMINAR:

- Verificar existencia de la relación (posición en matriz)
    - Si la relación existe eliminarla: poner 0
    - Si la relación no existe imprimir en consola: La relación indicada no existe

ACTUALIZAR:

- Verificar existencia de la relación
    - Si la relación existe actualizar
    - Si la relación no existe imprimir en consola: La relación indicada no existe

 

```jsx
INICIO

  Mostrar instrucciones:
    create -> Para crear relación
    update -> Para actualizar relación
    delete -> Para eliminar relación
    exit   -> Para terminar el programa

  Estructuras:
    nodos  = lista de nodos (un carácter cada uno)
    matriz = matriz de adyacencia (celda "0" = sin relación)

  Estructura de la entrada:
    create NodoNodoPeso   Ejemplo: create ABr
    update NodoNodoPeso   Ejemplo: update AB5
    delete NodoNodo       Ejemplo: delete AB

  REPETIR
    Leer línea del usuario
    SI la línea es "exit" -> TERMINAR

    Separar en operación y relación
    nodoA = relación[0]
    nodoB = relación[1]
    peso  = resto de la relación (después de los dos nodos)

    SI relación tiene menos de 2 caracteres
      Imprimir "Formato incorrecto" y volver a leer

    SEGÚN operación:

      CASO create:
        SI peso está vacío -> peso = "1"
        SI la relación ya existe (celda distinta de "0")
          Imprimir "La relación ya existe, usa update"
        SINO
          SI nodoA no existe -> crearlo (agregar fila y columna de "0")
          SI nodoB no existe -> crearlo (agregar fila y columna de "0")
          Guardar peso en matriz[A][B] y matriz[B][A]
          Imprimir matriz

      CASO update:
        SI peso está vacío
          Imprimir "update requiere un peso" y volver a leer
        SI la relación existe (ambos nodos existen y celda distinta de "0")
          Guardar peso en matriz[A][B] y matriz[B][A]
          Imprimir matriz
        SINO
          Imprimir "La relación indicada no existe"

      CASO delete:
        SI peso NO está vacío
          Imprimir "delete no lleva peso" y volver a leer
        SI la relación existe (ambos nodos existen y celda distinta de "0")
          Poner "0" en matriz[A][B] y matriz[B][A]
          (los nodos permanecen en la matriz)
          Imprimir matriz
        SINO
          Imprimir "La relación indicada no existe"

      OTRO CASO:
        Imprimir "Comando desconocido"

  HASTA que el usuario escriba "exit"

FIN
```