// integra la distribucion t de 0 a x con la regla de Simpson
public class IntegracionSimpson {

    private DistribucionT distribucion;
    private int numSeg = 10;
    private double error = 0.00001;

    public IntegracionSimpson(DistribucionT distribucion) {
        this.distribucion = distribucion;
    }

    // una pasada de Simpson con un numero par de segmentos
    public double simpson(double x, int segmentos) {
        double w = x / segmentos;
        double suma = distribucion.calcularF(0) + distribucion.calcularF(x);

        for (int i = 1; i < segmentos; i++) {
            if (i % 2 == 1) {
                suma = suma + (4 * distribucion.calcularF(i * w));
            } else {
                suma = suma + (2 * distribucion.calcularF(i * w));
            }
        }

        return (w / 3) * suma;
    }

    // duplica los segmentos hasta que dos resultados seguidos difieran
    // menos que el error aceptable
    public double integrar(double x) {
        int segmentos = numSeg;
        double anterior = simpson(x, segmentos);
        segmentos = segmentos * 2;
        double actual = simpson(x, segmentos);

        while (Math.abs(actual - anterior) > error) {
            anterior = actual;
            segmentos = segmentos * 2;
            actual = simpson(x, segmentos);
        }

        return actual;
    }
}
