import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.File;

// pruebas del programa 5; los valores esperados son los de las Tablas 1 y 2
// del 5a
public class A5Test {

    private double integrar(double x, double dof) {
        DistribucionT distribucion = new DistribucionT(dof);
        IntegracionSimpson integracion = new IntegracionSimpson(distribucion);
        return integracion.integrar(x);
    }

    // Tabla 1

    @Test
    void test1() {
        assertEquals(0.35006, integrar(1.1, 9), 0.00001);
    }

    @Test
    void test2() {
        assertEquals(0.36757, integrar(1.1812, 10), 0.00001);
    }

    @Test
    void test3() {
        assertEquals(0.49500, integrar(2.750, 30), 0.00001);
    }

    // gamma

    @Test
    void gammaDeUno() {
        assertEquals(1, new DistribucionT(9).gamma(1), 0.00001);
    }

    @Test
    void gammaDeUnMedio() {
        assertEquals(Math.sqrt(Math.PI), new DistribucionT(9).gamma(0.5),
                0.00001);
    }

    @Test
    void gammaDeCinco() {
        assertEquals(24, new DistribucionT(9).gamma(5), 0.00001);
    }

    @Test
    void gammaDeNueveMedios() {
        assertEquals(11.63173, new DistribucionT(9).gamma(4.5), 0.00001);
    }

    // funcion t

    @Test
    void funcionTEnCero() {
        assertEquals(0.38803, new DistribucionT(9).calcularF(0), 0.00001);
    }

    @Test
    void funcionTEnUnoPuntoUno() {
        assertEquals(0.20652, new DistribucionT(9).calcularF(1.1), 0.00001);
    }

    // Simpson

    @Test
    void unaPasadaConDiezSegmentos() {
        IntegracionSimpson integracion =
                new IntegracionSimpson(new DistribucionT(9));
        assertEquals(0.3500589, integracion.simpson(1.1, 10), 0.0000001);
    }

    @Test
    void unaPasadaConVeinteSegmentos() {
        IntegracionSimpson integracion =
                new IntegracionSimpson(new DistribucionT(9));
        assertEquals(0.35005864, integracion.simpson(1.1, 20), 0.0000001);
    }

    @Test
    void integrarDeCeroACeroEsCero() {
        assertEquals(0, integrar(0, 9), 0.00001);
    }

    @Test
    void integrarConMuchosGrados() {
        assertEquals(0.5, integrar(6, 30), 0.00001);
    }

    // con 1 grado de libertad la integral exacta es arctan(x) / pi;
    // aqui Simpson necesita duplicar los segmentos varias veces
    @Test
    void integrarConUnGradoNecesitaVariasPasadas() {
        assertEquals(Math.atan(10) / Math.PI, integrar(10, 1), 0.00001);
    }

    // entrada y salida

    @Test
    void inputConArchivoInexistenteDevuelveVacio() {
        Input input = new Input();
        assertEquals("", input.readData("data/no_existe.txt"));
    }

    @Test
    void dataSeparaPorRenglon() {
        Data data = new Data();
        String[] datos = data.saveData("1.1\n1.1812\n2.750\n");
        assertEquals(3, datos.length);
        assertEquals("1.1812", datos[1]);
    }

    @Test
    void outputEscribeElArchivo() {
        Output output = new Output();
        Input input = new Input();
        output.writeData("data/prueba_out.txt", "hola\n");
        assertEquals("hola\n", input.readData("data/prueba_out.txt"));
    }

    @Test
    void outputConRutaInvalidaNoTruena() {
        Output output = new Output();
        output.writeData("carpeta_que_no_existe/prueba.txt", "hola\n");
        assertTrue(new File("carpeta_que_no_existe").exists() == false);
    }

    // programa completo

    @Test
    void appConUnaPruebaEscribeSuArchivo() {
        new File("data/out_test2.txt").delete();
        App.main(new String[] {"2"});
        Input input = new Input();
        assertTrue(input.readData("data/out_test2.txt").startsWith("Test 2"));
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

    // cubre el constructor vacio que Java le agrega a App
    @Test
    void appSePuedeCrear() {
        assertTrue(new App() != null);
    }
}
