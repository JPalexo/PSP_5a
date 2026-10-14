# Program 5 - Integracion de la distribucion t con Simpson

Programa 5 de PSP (`5a`): integra la distribucion t de 0 a x con la regla de Simpson.
El diseno sigue el diagrama de clases del profesor (`docs/diagrama_profesor.png`).

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

- `mvn test`: 25 pruebas JUnit (Tabla 1, Tabla 2 paso a paso, gamma y el programa completo).
- `mvn verify`: ademas exige 100 % de cobertura de lineas y ramas (JaCoCo).

El workflow de GitHub Actions (`.github/workflows/maven.yml`) corre `mvn verify` y las tres
pruebas en cada `push` a `main`.

## Resultados (Tabla 1 del 5a)

| Test | x | dof | p esperado | p del programa |
|---|---|---|---|---|
| 1 | 1.1 | 9 | 0.35006 | 0.35006 |
| 2 | 1.1812 | 10 | 0.36757 | 0.36757 |
| 3 | 2.750 | 30 | 0.49500 | 0.49500 |
