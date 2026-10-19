# Program 5 - Integracion de la distribucion t con Simpson

## Requisitos

- Java 17 o mayor
- Maven

## Como ejecutarlo

```bat
mvn -q clean compile
java -cp target/classes App 2
```

Sin numero corre las tres pruebas. Cada una deja su resultado en `data/out_testN.txt`.

## Pruebas y cobertura

```bat
mvn test
mvn verify
```