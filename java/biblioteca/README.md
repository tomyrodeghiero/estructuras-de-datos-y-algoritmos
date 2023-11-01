<!-- Ejercicio 2 - d) -->

# Comparación entre el operador `==` y el método `.equals()`

En Java, hay dos formas principales de comparar objetos: el operador `==` y el método `.equals()`. Cada uno tiene un propósito y un comportamiento específico:

## Comparación con `==`

El operador `==` compara las referencias de los objetos para determinar si apuntan al mismo lugar en la memoria. No verifica si los contenidos de los objetos son iguales.

Por ejemplo, consideremos las siguientes líneas de código imprimirán false por pantalla:

```java
    Libro libro1 = new Libro("Isaac Asimov", "The Caves of Steel", 42);
    Libro libro2 = new Libro("Isaac Asimov", "The Caves of Steel", 42);
    System.out.println(libro1 == libro2);
    System.out.println(libro1 == libro3);
```

Mientras que por otro lado, las siguientes líneas de código imprimirán false por pantalla:

```java
    Libro libro1 = new Libro("Isaac Asimov", "The Caves of Steel", 42);
    Libro libro2 = new Libro("Isaac Asimov", "The Caves of Steel", 42);
    Libro libro3 = new Libro("Isaac Asimov", "The Naked Sun", 47);
    System.out.println(libro1 == libro2);
    System.out.println(libro1.equals(libro3));
    System.out.println(libro1 == libro3);
```

Cabe mencionar que, aunque `libro1` y `libro2` tienen los mismos atributos (mismo autor, mismo título y mismo número de páginas), la comparación con `==` resulta en `false` porque son dos instancias distintas alamancenadas en diferentes ubicaciones de memoria.

## Comparación con `.equals()`

El método `.equals()` se utiliza para comparar el contenido de dos objetos. En otras palabras, `.equals()` puede (y frecuentemente debe) ser sobrescrito para definir qué significa que dos instancias de una clase sean iguales.

Por ejemplo:

```java
    System.out.println(libro1.equals(libro2)); // en principio, imprimirá true por pantalla, pero el resultado dependerá de cómo se haya implementado el método .equals en la clase Libro
    System.out.println(libro1.equals(libro3)); // esto imprime false como resultado
```

Si el método `.equals()` en la clase `Libro` ha sido sobrescrito para comparar el título, el autor y el número de páginas, entonces `libro1.equals(libro2)` devolverá `true` porque ambos libros tienen los mismos atributos.

## Ejemplos específicos

En el código proporcionado, tenemos líneas como las siguientes:

```java
    System.out.println(libro1 + " y " + libro2 + "\nSon iguales usando equals? " + (libro1.equals(libro2))); // resultado dependiente de la implementación de .equals()
    System.out.println(libro1 + " y " + libro2 + "\nSon iguales usando ==? " + (libro1 == libro2)); // Esto imprimirá false como resultado
```

Esto ilustra la diferencia, entre los métodos de comparación:

- `libro1 == libro2` devolverá `false` porque son diferentes objetos en la memoria.
- `libro1.equals(libro2)` devolverá `true` o `false` dependiendo si se ha sobrescrito el método `.equals()` para comparar el contenido de los libros.

## Conclusión

En resumen, `==` se usa para comparar si dos referencias apuntan al mismo objeto en memoria, mientras que `.equals()` se usa para comparar si los contenidos de dos objetos son iguales según la implementación específica del método en la clase del objeto.
