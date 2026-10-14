# Program 5 - como ejecutarlo

Siempre parado en la carpeta `PSP\Program5`.

## Compilar

```bat
mvn -q clean compile
```

## Una prueba a la vez (1, 2 o 3)

```bat
java -cp target/classes App 2
```

Sale:

```
Test 2  x=1.1812  dof=10  p=0.36757
```

Sin numero corre las tres: `java -cp target/classes App`.
Cada prueba deja su resultado en `data/out_testN.txt`.

## Todas las pruebas JUnit

```bat
mvn test
```

Debe decir `Tests run: 25, Failures: 0, Errors: 0`.

El mensaje "Ocurrio un error al escribir el archivo" que sale en medio NO es una falla:
es una prueba que provoca el error a proposito para probar el `catch` de `Output`.

## Cobertura al 100 %

```bat
mvn verify
```

Debe decir `All coverage checks have been met` y `BUILD SUCCESS`.
El reporte queda en `target/site/jacoco/index.html`.

## Clases (segun `docs/diagrama_profesor.png`)

| Clase | Que hace |
|---|---|
| `App` | `main`: corre la prueba 1, 2 o 3, o las tres |
| `Logic` | Tiene los 3 casos; duplica los segmentos hasta que el error sea menor a 0.00001 |
| `SimpsonIntegration` | Una pasada de Simpson, paso a paso como la Tabla 2 |
| `GammaFunction` | Gamma para enteros y para mitades |
| `Output` | Escribe el resultado en un archivo |

## Los 3 tests (Tabla 1 del 5a)

| Test | Integral de 0 a x | dof | p esperado | p del programa |
|---|---|---|---|---|
| 1 | 1.1 | 9 | 0.35006 | 0.35006 |
| 2 | 1.1812 | 10 | 0.36757 | 0.36757 |
| 3 | 2.750 | 30 | 0.49500 | 0.49500 |
