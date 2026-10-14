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

Debe decir `Tests run: 23, Failures: 0, Errors: 0`.

Los mensajes "Ocurrio un error al leer/escribir el archivo" que salen en medio NO son fallas:
son dos pruebas que provocan el error a proposito para probar los `catch`.

## Cobertura al 100 %

```bat
mvn verify
```

Debe decir `All coverage checks have been met` y `BUILD SUCCESS`.
El reporte queda en `target/site/jacoco/index.html`.

## Los 3 tests (Tabla 1 del 5a)

| Test | Integral de 0 a x | dof | p esperado | p del programa |
|---|---|---|---|---|
| 1 | 1.1 | 9 | 0.35006 | 0.35006 |
| 2 | 1.1812 | 10 | 0.36757 | 0.36757 |
| 3 | 2.750 | 30 | 0.49500 | 0.49500 |

Simpson empieza con 10 segmentos y los duplica hasta que dos resultados seguidos difieren
menos de 0.00001. Los tres tests terminan en la segunda pasada (20 segmentos).
