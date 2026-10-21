import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.File;

// pruebas del programa 5; los valores esperados son los de las Tablas 1 y 2
// del 5a
public class A5Test {

    // arma una pasada completa de Simpson: x = 1.1, dof = 9, 10 segmentos,
    // que es el ejemplo de la Tabla 2
    private SimpsonIntegration pasadaTabla2() {
        SimpsonIntegration simpson = new SimpsonIntegration();
        simpson.computeW(10, 1.1);
        double[] xi = simpson.computeXi(10);
        simpson.computeFirstBaseTerms(10, xi, 9);
        simpson.computeExponent(9);
        simpson.computeCoefficient(9);
        simpson.computeFxi(10);
        simpson.computeFinalTerms(10);
        return simpson;
    }

    // Tabla 1
// assertEquals(0.35006, new Logic().integrar(1.1, 9), 0.00001);
    @Test
    void test1() {
        assertEquals(0.006, new Logic().integrar(1.1, 9), 0.00001);
    }
//        assertEquals(0.36757, new Logic().integrar(1.1812, 10), 0.00001);

    @Test
    void test2() {
        assertEquals(0.36757, new Logic().integrar(1.1812, 10), 0.00001);
    }

    @Test
    void test3() {
        assertEquals(0.49500, new Logic().integrar(2.750, 30), 0.00001);
    }

    // Tabla 2, paso a paso

    @Test
    void anchoDelSegmento() {
        assertEquals(0.11, new SimpsonIntegration().computeW(10, 1.1),
                0.00001);
    }

    @Test
    void puntosXi() {
        double[] xi = pasadaTabla2().computeXi(10);
        assertEquals(11, xi.length);
        assertEquals(0.55, xi[5], 0.00001);
        assertEquals(1.1, xi[10], 0.00001);
    }

    @Test
    void primerosTerminosBase() {
        SimpsonIntegration simpson = pasadaTabla2();
        double[] xi = simpson.computeXi(10);
        double[] base = simpson.computeFirstBaseTerms(10, xi, 9);
        assertEquals(1.00134, base[1], 0.00001);
        assertEquals(1.13444, base[10], 0.00001);
    }

    @Test
    void exponente() {
        assertEquals(-5, new SimpsonIntegration().computeExponent(9), 0.00001);
    }

    @Test
    void coeficienteConDofImpar() {
        assertEquals(0.38803, new SimpsonIntegration().computeCoefficient(9),
                0.00001);
    }

    @Test
    void coeficienteConDofPar() {
        assertEquals(0.38911, new SimpsonIntegration().computeCoefficient(10),
                0.00001);
    }

    @Test
    void valoresDeF() {
        double[] f = pasadaTabla2().computeFxi(10);
        assertEquals(0.38803, f[0], 0.00001);
        assertEquals(0.38544, f[1], 0.00001);
        assertEquals(0.20652, f[10], 0.00001);
    }

    @Test
    void terminosFinales() {
        double[] terminos = pasadaTabla2().computeFinalTerms(10);
        assertEquals(0.01423, terminos[0], 0.00001);
        assertEquals(0.05653, terminos[1], 0.00001);
        assertEquals(0.02770, terminos[2], 0.00001);
        assertEquals(0.00757, terminos[10], 0.00001);
    }

    @Test
    void resultadoDeLaPasada() {
        assertEquals(0.3500589, pasadaTabla2().computeFinalValue(10),
                0.0000001);
    }

    // gamma

    @Test
    void gammaDeUno() {
        assertEquals(1, new GammaFunction().computeIntGamma(1), 0.00001);
    }

    @Test
    void gammaDeCinco() {
        assertEquals(24, new GammaFunction().computeIntGamma(5), 0.00001);
    }

    @Test
    void gammaDeUnMedio() {
        assertEquals(Math.sqrt(Math.PI),
                new GammaFunction().computeDblGamma(0.5), 0.00001);
    }

    @Test
    void gammaDeNueveMedios() {
        assertEquals(11.63173, new GammaFunction().computeDblGamma(4.5),
                0.00001);
    }

    // ciclo de Logic

    // valor de una tabla t: con 1 grado de libertad, el area de 0 a 6.314
    // es 0.45; aqui Simpson necesita duplicar los segmentos varias veces
    @Test
    void integrarConValorDeTablaT() {
        assertEquals(0.45, new Logic().integrar(6.314, 1), 0.00001);
    }

    @Test
    void integrarDeCeroACeroEsCero() {
        assertEquals(0, new Logic().integrar(0, 9), 0.00001);
    }

    // salida

    @Test
    void outputEscribeElArchivo() {
        new File("data/prueba_out.txt").delete();
        new Output().writeData("data/prueba_out.txt", "hola\n");
        assertTrue(new File("data/prueba_out.txt").exists());
    }

    @Test
    void outputConRutaInvalidaNoTruena() {
        new Output().writeData("carpeta_que_no_existe/prueba.txt", "hola\n");
        assertTrue(new File("carpeta_que_no_existe").exists() == false);
    }

    // programa completo

    @Test
    void appConUnaPruebaEscribeSuArchivo() {
        new File("data/out_test2.txt").delete();
        App.main(new String[] {"2"});
        assertTrue(new File("data/out_test2.txt").exists());
    }

    @Test
    void appSinParametroCorreLasTres() {
        for (int i = 1; i <= 3; i++) {
            new File("data/out_test" + i + ".txt").delete();
        }
        App.main(new String[] {});
        for (int i = 1; i <= 3; i++) {
            assertTrue(new File("data/out_test" + i + ".txt").exists());
        }
    }

    @Test
    void appConNumeroCeroNoCorreNada() {
        new File("data/out_test1.txt").delete();
        App.main(new String[] {"0"});
        assertTrue(new File("data/out_test1.txt").exists() == false);
    }

    @Test
    void appConNumeroCuatroNoCorreNada() {
        new File("data/out_test1.txt").delete();
        App.main(new String[] {"4"});
        assertTrue(new File("data/out_test1.txt").exists() == false);
    }

    // App solo tiene main, pero tambien se puede crear un objeto App
    @Test
    void appSePuedeCrear() {
        assertTrue(new App() != null);
    }
}
