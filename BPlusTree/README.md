# B+ Tree Implementation 

Esta es una implementación sencilla de un Árbol B+ en Java, desarrollada como una práctica para comprender cómo esta estructura de datos maneja internamente las inserciones, la división de nodos (splits), las eliminaciones y las búsquedas secuenciales.

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

* **`src/Node.java`**: 
- Define los nodos del árbol. 
- Controla las claves, los punteros a los hijos, los enlaces entre hojas (para acceso secuencial) y distingue entre nodos internos y hojas.
* **`src/Tree.java`**: 
- Contiene la lógica central del Árbol B+. Incluye los métodos principales como `insert()`, `split()`, `delete()` y utilidades para imprimir la estructura del árbol por niveles.
* **`src/Main.java`**: 
- Es el punto de entrada principal que ejecuta los diferentes escenarios de prueba.
* **`test/`**: 
- Contiene tres niveles de prueba (`SimpleCase`, `MediumCase` y `HardCase`) para validar el comportamiento del árbol bajo diferentes volúmenes de datos.

## Cómo ejecutar las pruebas

1. **Compilar el proyecto**:
   Abre una terminal en la raíz del proyecto y compila los archivos fuente y de prueba en una carpeta `bin`:
   ```bash
   mkdir -p bin
   javac -d bin src/*.java test/*.java
   ```
2. **Ejecutar el Programa**:
    Corre la clase principal para ver los resultados de los casos de prueba (Simple, Medium y Hard) directamente en la consola:

    ```bash
    java -cp bin Main
    ```

> *Última Fecha de Actualización: 17/08/2026*