<!-- Práctica 2 - Ejercicio 7 -->

# Comparación de Implementaciones de Pila: Java vs. C

## Introducción

Las **pilas (o stacks)** son estructuras de datos fundamentales en la ciencia de la computación que siguen el principio **LIFO (Last In, First Out)**. En este informe, se compararán dos implementaciones de pilas, una en Java y otra en C, con el objetivo de resaltar las diferencias y similitudes en función de las características y paradigmas propios de cada lenguaje.

## Comparación

### 1. Paradigma de programación

- **Java**:
  - Enfoque **Orientado a Objetos**.
  - Implementación dentro de una clase `PilaSobreListasEnlazadas`.
  - Uso de herencia e interfaces (por ejemplo, `Pila`).
- **C**:
  - Enfoque **Procedural**.
  - Funciones globales actuando sobre una estructura de datos `Pila`.

### 2. Gestión de memoria

- **Java**:

  - **Automática** gracias al recolector de basura.
  - Creación de objetos mediante `new`.

- **C**:
  - **Manual** utilizando funciones como `malloc` y `free`.
  - Mayor responsabilidad en el programador para evitar fugas de memoria.

### 3. Tratamiento de errores

- **Java**:
  - Uso de **excepciones** para manejar errores (e.g., `ExcepcionPila`).
- **C**:
  - Utiliza **códigos de retorno** y salidas en consola (e.g., `printf`) para indicar situaciones anómalas.

### 4. Polimorfismo

- **Java**:
  - Diseño para almacenar cualquier objeto (`Object`).
  - Posibilidad de uso de genéricos (aunque no se muestra en el código proporcionado).
- **C**:
  - Uso de **punteros a `void`** (`void*`) para almacenar cualquier tipo.
  - Requiere casteo explícito y correcta gestión de tipos por parte del usuario.

### 5. Representación de la pila

- Ambos, **Java** y **C**:
  - Uso de **nodos enlazados** para representar la pila.
  - El "tope" (o cima) de la pila representado por un nodo (`tope`).

### 6. Misceláneo

- **Java**:
  - Método `toString` para obtener una representación en cadena de la pila.
- **C**:
  - Función `mostrarPila` para imprimir la representación de la pila en consola.

## Conclusión

Ambas implementaciones, aunque escritas en lenguajes con diferentes paradigmas y características, logran el objetivo de representar y manipular una pila. Las diferencias subrayadas reflejan las particularidades y la naturaleza de cada lenguaje, así como las diferentes maneras de abordar problemas y gestionar recursos.
